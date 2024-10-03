grammar custom:host:translation;

monoid attribute synAttrs::[(String, Type)] with [], ++;
inherited attribute synAttrsInh::[(String, Type)];

monoid attribute inhAttrs::[(String, Type)] with [], ++;
inherited attribute inhAttrsInh::[(String, Type)];

                             -- nt,   attr
monoid attribute inhOccurs::[(String, String)] with [], ++;
inherited attribute inhOccursInh::[(String, String)];

                             -- nt,   attr
monoid attribute synOccurs::[(String, String)] with [], ++;
inherited attribute synOccursInh::[(String, String)];


attribute 
  occurTransPairs, nonterminals, prodDeclsTrans, 
  inhAttrs, synAttrs, inhAttrsInh, synAttrsInh,
  inhOccurs, synOccurs, inhOccursInh, synOccursInh
occurs on TopDecl;

propagate occurTransPairs on TopDecl excluding occursDecl;
propagate nonterminals on TopDecl excluding nonterminalDecl;
propagate prodDeclsTrans on TopDecl excluding productionDecl;
propagate inhAttrs, synAttrs on TopDecl excluding attributeDecl;
propagate inhAttrsInh on TopDecl;
propagate synAttrsInh on TopDecl;
propagate inhOccurs, synOccurs on TopDecl excluding occursDecl;
propagate inhOccursInh, synOccursInh on TopDecl;

aspect production nonterminalDecl
top::TopDecl ::= id::String
{
  top.nonterminals := [id];
}

aspect production attributeDecl
top::TopDecl ::= attrType::AttrType id::String ty::Type
{
  top.inhAttrs := if !attrType.isSyn then [(id, ty)] else [];
  top.synAttrs := if attrType.isSyn then [(id, ty)] else [];
}

aspect production occursDecl
top::TopDecl ::= attrId::String nontId::String
{
  top.occurTransPairs := [(nontId, genOccursTrans(attrId, top.tyEnvInh))];
  
  top.synOccurs := 
    case filter((\p::(String, Type) -> p.1 == attrId), top.synAttrsInh) of
    | (a,t)::_ -> [(nontId, attrId)]
    | [] -> []
    end;

  top.inhOccurs := 
    case filter((\p::(String, Type) -> p.1 == attrId), top.inhAttrsInh) of
    | (a,t)::_ -> [(nontId, attrId)]
    | [] -> []
    end;

}

aspect production productionDecl
top::TopDecl ::= id::String nont::String ps::Children eqs::Equations
{
  local implementsStr::String = 
    let filteredNts::[(String, Type, Boolean)] = 
      filter((\p::(String, Type, Boolean) -> 
                case p.2 of | nonterminalType(s) -> true | _ -> false end), 
             ps.childNamesTypes ++ eqs.localsNamesTypes)
    in
    let allChildren::[String] =
      nub(map ((\p::(String, Type, Boolean) ->
              case p.2 of
              | nonterminalType(s) ->
                  "hasChild_" ++ s ++ "<" ++ id ++ "<T>>"
              | _ -> ""
              end), 
           filteredNts))
    in
      if !null(allChildren)
      then " implements " ++ implode(", ", allChildren)
      else ""
    end end;

  local childrenFieldsStr::[String] = 
    map(
      (\p::(String, Type, Boolean) ->
        case p.2 of
        | nonterminalType(s) ->
            "private " ++ s ++ "<" ++ id ++ "<T>> " ++ p.1 ++ ";"
        | t ->
            "private " ++ p.2.translationStr ++ " " ++ p.1 ++ ";"
        end
      ), 
      ps.childNamesTypes
    );

  local constructorStrNumChildren::(String, Integer) =
    let childArgs::String = 
      implode (", ", map (
        (
          \p::(String, Type, Boolean) ->
            case p.2 of
            | nonterminalType(s) ->
                s ++ "<" ++ id ++ "<T>> " ++ p.1
            | t -> p.2.translationStr ++ " " ++ p.1
            end
        ), 
        ps.childNamesTypes
      ))
    in
    let setChildrenAndNum::([String], Integer) =
      foldl (
          (\acc::([String], Integer) p::(String, Type, Boolean) ->
             case p.2 of
             | nonterminalType(s) ->
                 (acc.1 ++ [("this." ++ p.1 ++
                             ".setParent(this, " ++ toString(acc.2) ++ ");")],
                  acc.2 + 1)
             | t -> (acc.1, acc.2)
             end
          ),
          ([], 0),
          ps.childNamesTypes
        )
    in
    ("public " ++ id ++ "(" ++ childArgs ++ ") {\n" ++
      implode(
        "\n",
        map((\p::(String, Type, Boolean) -> 
              "this." ++ p.1 ++ " = " ++ p.1 ++ ";"), 
            ps.childNamesTypes) ++
        setChildrenAndNum.1
      ) ++

    "\n}", setChildrenAndNum.2)
    end end;
  
  local localsLst::[String] = eqs.localDeclsTrans;
  local inhsStrs::[String] =
    map (
      (
        \inhAttr::String -> -- attribute name
          let inhNameTy::[(String, Type)] = -- attribute name, type
            filter (
              (\p::(String, Type) -> p.1 == inhAttr),
              top.inhAttrsInh
            )
          in
          let inhTy::Type = head(inhNameTy).2 in

          if null(inhNameTy) then "ERROR productionDecl.inhsStrs" else
          ("public " ++ inhTy.translationStr ++ " " ++ inhAttr ++ 
            "(int childId) {\n" ++
              implode ("\n",
                map (
                  (
                    \eq::(String, String, Integer, [String], String) ->
                    "// " ++ eq.2 ++ "." ++ eq.1 ++ "\n" ++
                    "if (childId == " ++ toString(eq.3) ++ ") {\n" ++
                      implode ("\n", map(\s::String -> s ++ ";", eq.4)) ++ "\nreturn " ++ eq.5 ++ ";\n" ++
                    "}"
                  ),
                  filter ( -- only eqs which are this attr
                    (\eq::(String, String, Integer, [String], String) -> 
                      eq.1 == inhAttr),
                    eqs.childInhEquations
                  )
                )
              ) ++
            "return null;\n" ++
          "}")

          end end
      ),
      nub(concat (map ( -- all inh attributes for all children
        (
          \ch::(String, Type, Integer) ->
            let childNt::String = case ch.2 of
                                  | nonterminalType(s) -> s
                                  | _ -> "ERROR"
                                  end
            in
              map ( -- all inh attrs for any child
                snd,
                filter (
                  (\inh::(String, String) -> inh.1 == childNt),
                  top.inhOccursInh
                )
              )
            end
        ),
        childrenNumsLocal
      ))
    ));

  local synStrs::[String] =
    let synOccursForNont::[String] =
      map(snd, 
          filter ((\occ::(String, String) -> occ.1 == nont), top.synOccursInh))
    in
    let synAttrs::[(String, Type)] =
      filterMap (
        (
          \attrName::String ->
            case filter((\attr::(String, Type) -> attr.1 == attrName), 
                        top.synAttrsInh) 
            of
            | h::_ -> just(h)
            | [] -> nothing()
            end
        ),
        synOccursForNont
      )
    in
      map (
        (      -- attr,   attr ty
          \attr::(String, Type) ->
                    -- attr,   pre exp,  exp 
            let eqs::[(String, [String], String)] =
              filter((\eq::(String, [String], String) -> eq.1 == attr.1), 
                     eqs.synEquations)
            in
              case eqs of
              | (attrId, preExp, exp)::_ ->
                  "public " ++ attr.2.translationStr ++ " " ++ 
                    attrId ++ "() {\n" ++
                  "if (this." ++ attrId ++ "_computed) " ++ 
                    "return this." ++ attrId ++ ";" ++
                  implode("\n", map((\s::String -> s++";"), preExp)) ++ "\n" ++
                  "this." ++ attrId ++ " = " ++ exp ++ ";\n" ++
                  "this." ++ attrId ++ "_computed = true;\n" ++
                  "return this." ++ attrId ++ ";\n" ++
                  "}"
              | [] -> "ERROR (productionDecl.synStrs)"
              end
            end
        ),
        synAttrs
      )
    end end;

  local myInhStrs::[String] =
    let inhOccursForNont::[String] =
      map(snd, 
          filter ((\occ::(String, String) -> occ.1 == nont), top.inhOccursInh))
    in
    let inhAttrs::[(String, Type)] =
      filterMap (
        (
          \attrName::String ->
            case filter((\attr::(String, Type) -> attr.1 == attrName), 
                        top.inhAttrsInh) 
            of
            | h::_ -> just(h)
            | [] -> nothing()
            end
        ),
        inhOccursForNont
      )
    in
      map (
        (      -- attr,   attr ty
          \attr::(String, Type) ->
            "public " ++ attr.2.translationStr ++ " " ++ attr.1 ++ "() {\n"++
              "if (this." ++ attr.1 ++ "_computed) " ++ 
                "return this." ++ attr.1 ++ ";\n" ++
              "this." ++ attr.1 ++ " = this.parent." ++ 
                attr.1 ++ "(this.childId);\n" ++
              "this." ++ attr.1 ++ "_computed = true;\n" ++
              "return this." ++ attr.1 ++ ";\n" ++
            "}"
        ),
        inhAttrs
      )
    end end;
  
  top.prodDeclsTrans := [(nont,
    "class " ++ id ++ "<T extends hasChild_" ++ nont ++ "<T>> " ++ 
    "extends " ++ nont ++ "<T>" ++ implementsStr ++ " {\n\n" ++ 
  
      (if !null(childrenFieldsStr)
        then "/* CHILD FIELDS */\n" ++ implode("\n", childrenFieldsStr) ++ "\n"
        else "") ++
      
      "/* CONSTRUCTOR */\n" ++
      constructorStrNumChildren.1 ++ "\n" ++
      
      (if !null(localsLst)
        then "/* LOCALS */\n" ++ implode("\n", localsLst) ++ "\n"
        else "") ++
  
      (if !null(inhsStrs)
        then "/* SETTING CHILD INHS */\n" ++ implode("\n", inhsStrs) ++ "\n"
        else "") ++

      (if !null(myInhStrs)
        then "/* GETTING OWN INHS */\n" ++ implode("\n", myInhStrs) ++ "\n"
        else "") ++

      (if !null(synStrs)
        then "/* SYNTHESIZED ATTRS */\n" ++ implode("\n", synStrs) ++ "\n"
        else "") ++
      
    "\n}"
  )];

  eqs.childNumberInh = constructorStrNumChildren.2;
  eqs.prodNameTrans = id ++ "<T>";

  local childrenNumsLocal::[(String, Type, Integer)] = 
    foldl(
      \acc::[(String, Type, Integer)] p::(String, Type, Boolean)  ->
        case p.2 of
        | nonterminalType(s) ->
            if null(acc)
            then [(p.1, p.2, 0)]
            else let num::Integer = head(acc).3 + 1 in 
                 [(p.1, p.2, num)] ++ acc end
        | _ -> acc
        end
      ,
      [],
      ps.childNamesTypes ++ eqs.ntChildrenSyn
    );

  eqs.childrenNums = childrenNumsLocal;
}

aspect production functionDecl
top::TopDecl ::= id::String ty::Type cs::Children e::Expr
{
}





attribute 
  nonterminals, occurTransPairs, prodDeclsTrans,
  inhAttrs, synAttrs, inhAttrsInh, synAttrsInh,
  inhOccurs, synOccurs, inhOccursInh, synOccursInh
occurs on TopDecls;

propagate 
  nonterminals, occurTransPairs, prodDeclsTrans, 
  inhAttrs, synAttrs, inhAttrsInh, synAttrsInh,
  inhOccurs, synOccurs, inhOccursInh, synOccursInh
on TopDecls;

aspect production topDeclsCons
top::TopDecls ::= d::TopDecl ds::TopDecls
{
}

aspect production topDeclsNil
top::TopDecls ::=
{
}

--------------------------------------------------------------------------------

function genOccursTrans
String ::= attrId::String tyenv::TyEnv
{
  local ty::Maybe<(Type, Boolean)> = lookupTyEnv(attrId, tyenv);

  local tyStr::String = 
    case ty of
    | just((nonterminalType(s), b)) ->
        s ++ "<? extends hasChild_" ++ s ++ "<?>>"
    | just((t, b)) ->
        t.translationStr
    | nothing() ->
        "ERROR (TopDecl.genOccursTrans)"
    end;

  return
    "\tprotected " ++ tyStr ++ " " ++ attrId ++ " = null;\n" ++
    "\tprotected Boolean " ++ attrId ++ "_computed = false;\n" ++
    "\tpublic " ++ tyStr ++ " " ++ attrId ++ "() { return null; }"; 
}