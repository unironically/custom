grammar custom:host:translation;

inherited attribute childNumberInh::Integer;
inherited attribute prodNameTrans::String;
synthesized attribute childNumberSyn::Integer;

inherited attribute childrenNums::[(String, Type, Integer)];

monoid attribute ntChildrenSyn::[(String, Type, Boolean)] with [], ++;

monoid attribute childInhEquations::[(String, String, Integer, [String], String)]
  with [], ++;
monoid attribute synEquations::[(String, [String], String)]
  with [], ++;

attribute 
  localsNamesTypes, localDeclsTrans, childNumberInh, childNumberSyn,
  prodNameTrans, childrenNums, childInhEquations, ntChildrenSyn,
  synEquations
occurs on Equation;

propagate localsNamesTypes, localDeclsTrans, prodNameTrans, childrenNums,
          ntChildrenSyn
  on Equation excluding localDeclEquation;

propagate childInhEquations, synEquations on Equation excluding assignEquation;

aspect production localDeclEquation
top::Equation ::= id::String ty::Type e::Expr
{
  top.localsNamesTypes := [(id, ty, true)];
  top.localDeclsTrans := [
    genLocalDecl(id, ty, e, top.prodNameTrans, top.childNumberInh)
  ];

  top.childNumberSyn = top.childNumberInh + 1;

  top.ntChildrenSyn := [(id, ty, false)];
}

aspect production assignEquation
top::Equation ::= lhs::LHS e::Expr
{
  top.childNumberSyn = top.childNumberInh;

  top.childInhEquations := 
    case lhs of
    | refLHS(_) -> []
    | fieldAccessLHS("this", _) -> []
    | fieldAccessLHS(childRefStr, attrStr) ->
        let childNum::Integer = 
          let filtered::[(String, Type, Integer)] =
            filter((\p::(String, Type, Integer) -> p.1 == childRefStr),
                   top.childrenNums)
          in
            if !null(filtered)
            then head(filtered).3
            else -1
          end
        in
          [(attrStr, childRefStr, childNum, e.preExprTranslation, e.translationStr)]
        end
    end;

  top.synEquations :=
    case lhs of
    | refLHS(_) -> []
    | fieldAccessLHS(ref, _) when ref != "this" -> []
    | fieldAccessLHS(_, attr) -> 
        [(attr, e.preExprTranslation, e.translationStr)]
    end;
}


attribute
  localsNamesTypes, localDeclsTrans, childNumberInh, childNumberSyn,
  prodNameTrans, childrenNums, childInhEquations, ntChildrenSyn,
  synEquations
occurs on Equations;

propagate localsNamesTypes, localDeclsTrans, prodNameTrans, childrenNums,
          childInhEquations, ntChildrenSyn, synEquations
  on Equations;

aspect production equationsCons
top::Equations ::= e::Equation es::Equations
{
  e.childNumberInh = top.childNumberInh;
  es.childNumberInh = e.childNumberSyn;
  top.childNumberSyn = es.childNumberSyn;
}

aspect production equationsNil
top::Equations ::=
{
  top.childNumberSyn = top.childNumberInh;
}


--------------------------------------------------------------------------------

function genInheritedEquation
String ::= 
  attr::String 
  attrTypeTranslation::String
  childInhEquations::[(Integer, [String], String)]
{
  return "";
}

{-function genSynthesizedEquation
String ::= attr::String attrTypeTranslation::String e::Decorated Expr
{
  local synStr::[String] = [
    "public " ++ attrTypeTranslation ++ " " ++ attr ++ "() {",
      "if (this." ++ attr ++ "_computed) return this." ++ attr ++ ";"]
      ++ e.preExprTranslation ++ 
      ["this." ++ attr ++ " = " ++ e.translationStr ++ ";",
      "this." ++ attr ++ "_computed = true;",
      "return this." ++ attr ++ ";",
    "}"
  ];
  return implode("\n", synStr);
}-}

function genLocalDecl
String ::= id::String ty::Type 
           e::Decorated Expr
           prodNameTrans::String
           childNum::Integer
{
  local setParentTranslation::[String] = 
    case ty of
    | nonterminalType(s) -> ["this." ++ id ++ 
                            ".setParent(this, " ++ toString(childNum) ++ ");"]
    | _ -> []
    end;

  local tyTransStr::String =
    case ty of
    | nonterminalType(s) -> s ++ "<" ++ prodNameTrans ++ ">"
    | t -> t.translationStr
    end;

  local localStr::[String] = [
    "private " ++ tyTransStr ++ " " ++ id ++ " = " ++ 
      ty.defaultTranslation ++ ";",
    "private Boolean " ++ id ++ "_computed = false;",
    "public " ++ tyTransStr ++ " " ++ id ++ "() {",
      "if (this." ++ id ++ "_computed) return this." ++ id ++ ";"]
      ++ e.preExprTranslation ++ 
      ["this." ++ id ++ " = " ++ e.translationStr ++ ";"] ++
      setParentTranslation ++ [
      "this." ++ id ++ "_computed = true;",
      "return this." ++ id ++ ";",
    "}"
  ];
  return implode("\n", localStr);
}

function genLocalFunctionDecl
String ::= ret::Type params::[Type]
{
  return "";
}