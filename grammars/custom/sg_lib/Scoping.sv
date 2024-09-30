grammar custom:sg_lib;

inherited attribute scope::Decorated SGScope;

synthesized attribute vars::[Decorated SGDecl];
synthesized attribute nonts::[Decorated SGDecl];
synthesized attribute attrs::[Decorated SGDecl];
synthesized attribute occs::[Decorated SGDecl];
synthesized attribute prods::[Decorated SGDecl];

synthesized attribute allScopes::[Decorated SGScope];

monoid attribute errs::[ErrMessage] with [], ++;

--------------------------------------------------

attribute allScopes, errs occurs on File;

propagate errs on File;

aspect production file
top::File ::= ds::TopDecls
{
  local glob::SGScope = mkScope(location=top.location);
  glob.lex = [];
  glob.var = ds.vars;
  glob.nont = ds.nonts;
  glob.attr = ds.attrs;
  glob.occ = ds.occs;
  glob.prod = ds.prods;
  glob.param = [];

  ds.scope = glob;

  top.allScopes = glob :: ds.allScopes;
}

--------------------------------------------------

attribute scope, vars, nonts, attrs, occs, prods, allScopes, errs 
occurs on TopDecls;

propagate errs on TopDecls;

aspect production topDeclsCons
top::TopDecls ::= d::TopDecl ds::TopDecls
{
  d.scope = top.scope;
  ds.scope = top.scope;

  top.vars = d.vars ++ ds.vars;
  top.nonts = d.nonts ++ ds.nonts;
  top.attrs = d.attrs ++ ds.attrs;
  top.occs = d.occs ++ ds.occs;
  top.prods = d.prods ++ ds.prods;

  top.allScopes = d.allScopes ++ ds.allScopes;
}

aspect production topDeclsNil
top::TopDecls ::=
{
  top.vars = [];
  top.nonts = [];
  top.attrs = [];
  top.occs = [];
  top.prods = [];

  top.allScopes = [];
}

--------------------------------------------------

attribute scope, vars, nonts, attrs, occs, prods, allScopes, errs 
occurs on TopDecl;

propagate errs on TopDecl;

aspect production nonterminalDecl
top::TopDecl ::= id::String
{
  local ntDecl::SGDecl = mkNontDecl(id, location=top.location);
  ntDecl.lex  = [top.scope];
  ntDecl.var  = [];
  ntDecl.nont = [];
  ntDecl.attr = attrs;
  ntDecl.occ = [];
  ntDecl.prod = [];
  ntDecl.param = [];

  -- looking up all occurence decls for this nonterminal
  local occRef::SGRef = mkRef(id, lexOccDFA(), location=top.location);
  occRef.lex = [top.scope];
  local occResult::[Decorated SGDecl] = occRef.res;

  -- looking up attributes occuring on this nonterminal
  local attrs::[Decorated SGDecl] = concat(map((.attr), occResult));

  top.vars = [];
  top.nonts = [ntDecl];
  top.attrs = [];
  top.occs = [];
  top.prods = [];

  top.allScopes = [ntDecl];
}

aspect production attributeDecl
top::TopDecl ::= attrType::AttrType id::String ty::Type
{
  local attrDecl::SGDecl = mkAttrDecl(id, ty, location=top.location);
  attrDecl.lex  = [top.scope];
  attrDecl.var  = [];
  attrDecl.nont = [];
  attrDecl.attr = [];
  attrDecl.occ = [];
  attrDecl.prod = [];
  attrDecl.param = [];

  top.vars = [];
  top.nonts = [];
  top.attrs = [attrDecl];
  top.occs = [];
  top.prods = [];

  top.allScopes = [attrDecl];
}

aspect production occursDecl
top::TopDecl ::= attrId::String nontId::String
{
  local occursDecl::SGDecl = mkOccursDecl(nontId, location=top.location);
  occursDecl.lex  = [top.scope];
  occursDecl.var  = [];
  occursDecl.nont = [];
  occursDecl.attr = [head(attrResult)];
  occursDecl.occ = [];
  occursDecl.prod = [];
  occursDecl.param = [];

  -- looking up the attribute type
  local attrRef::SGRef = mkRef(attrId, lexAttrDFA(), location=top.location); 
  attrRef.lex = [top.scope];
  local attrResult::[Decorated SGDecl] = attrRef.res;

  -- looking up the nonterminal type
  local ntRef::SGRef = mkRef(nontId, lexNontDFA(), location=top.location);
  ntRef.lex = [top.scope];
  local nontResult::[Decorated SGDecl] = ntRef.res;

  top.vars = [];
  top.nonts = [];
  top.attrs = [];
  top.occs = [occursDecl];
  top.prods = [];

  top.allScopes = [occursDecl];

  top.errs <-
    if null(nontResult) then 
      [errMessage("Nonterminal " ++ nontId ++ " not known", top.location)]
    else if length(nontResult) > 1 then
      [errMessage("Ambiguous nonterminal reference " ++ nontId, top.location)]
    else if null(attrResult) then 
      [errMessage("Attribute " ++ attrId ++ " not known", top.location)]
    else if length(attrResult) > 1 then
      [errMessage("Ambiguous attribute reference " ++ attrId, top.location)]
    else [];
    
}

aspect production productionDecl
top::TopDecl ::= id::String nontId::String ps::Children eq::Equations
{
  top.vars = [];
  top.nonts = [];
  top.attrs = [];
  top.occs = [];
  top.prods = [prodScope];
  top.allScopes = localDecl :: prodScope :: (eq.allScopes ++ ps.allScopes);

  -- production scope
  local prodScope::SGDecl = mkProdDecl(id, prodTy, location=top.location);
  prodScope.lex = [top.scope];
  prodScope.var  = localDecl :: (eq.vars ++ ps.vars);
  prodScope.nont = [];
  prodScope.attr = [];
  prodScope.occ  = [];
  prodScope.prod = [];
  prodScope.param = ps.vars;

  -- looking up the nonterminal type
  local ntRef::SGRef = mkRef(nontId, lexNontDFA(), location=top.location);
  ntRef.lex = [top.scope];
  local nontResult::[Decorated SGDecl] = ntRef.res;

  -- production type
  local prodTy::Type = case nontResult of
                       | s::[] -> nonterminalType(nontId, location=top.location)
                       | _     -> errType(location=top.location)
                       end;

  -- looking up attributes occuring on this nonterminal
  local attrs::[Decorated SGDecl] = concat(map((.attr), nontResult));

  -- local `this` decl
  local localDecl::SGDecl = 
    mkLocalDecl("this", nonterminalType(nontId, location=top.location), 
                location=top.location);
  localDecl.lex   = [];
  localDecl.var  = [];
  localDecl.nont = nontResult;
  localDecl.attr = [];
  localDecl.occ  = [];
  localDecl.prod = [];
  localDecl.param = [];

  eq.scope = prodScope;
  ps.scope = top.scope;

  top.errs <-
    if null(nontResult) then 
      [errMessage("Nonterminal " ++ nontId ++ " not known", top.location)]
    else if length(nontResult) > 1 then
      [errMessage("Ambiguous nonterminal reference " ++ nontId, top.location)]
    else [];
}

aspect production functionDecl -- todo
top::TopDecl ::= id::String ty::Type cs::Children e::Expr
{
  top.vars = [];
  top.nonts = [];
  top.attrs = [];
  top.occs = [];
  top.prods = []; -- todo

  top.allScopes = [];

  cs.scope = top.scope;
  e.scope = top.scope;
}

--------------------------------------------------

attribute scope, vars, errs, allScopes occurs on Children;

propagate scope, errs on Children;

aspect production childrenCons
top::Children ::= c::Child cs::Children
{ top.vars = c.vars ++ cs.vars;
  top.allScopes = c.allScopes ++ cs.allScopes; }

aspect production childrenNil
top::Children ::= 
{ top.vars = [];
  top.allScopes = []; }

--------------------------------------------------

attribute scope, vars, errs, allScopes occurs on Child;

propagate scope, errs on Child;

aspect production child
top::Child ::= id::String ty::Type
{

  -- local decl
  local localDecl::SGDecl = mkLocalDecl(id, ty, location=top.location);
  localDecl.lex   = [];
  localDecl.var  = [];
  localDecl.nont = declNont.1;
  localDecl.attr = [];
  localDecl.occ  = [];
  localDecl.prod = [];
  localDecl.param = [];

  local declNont::([Decorated SGDecl], [ErrMessage]) =
    case ty of
    | nonterminalType(s) -> 
        let ref::SGRef = mkRef(s, lexNontDFA(), location=top.location) in
        let dRef::Decorated SGRef = decorate ref with {lex = [top.scope];} in
          if null(dRef.res) then 
            ([], [errMessage("Nonterminal " ++ s ++ " not known", 
                             top.location)])
          else if length(dRef.res) > 1 then
            ([], [errMessage("Ambiguous nonterminal reference " ++ s, 
                             top.location)])
          else (dRef.res, [])
        end end
    | _ -> ([], [])
    end;
  
  top.vars = [localDecl];

  top.errs <- declNont.2;
  top.allScopes = [localDecl];

}

--------------------------------------------------

aspect production attrTypeSyn
top::AttrType ::= 
{}

aspect production attrTypeInh
top::AttrType ::=
{}

--------------------------------------------------

attribute scope, vars, errs, allScopes occurs on Equations;

propagate scope, errs on Equations;

aspect production equationsCons
top::Equations ::= e::Equation es::Equations
{
  top.vars = e.vars ++ es.vars;
  top.allScopes = e.allScopes ++ es.allScopes;
}

aspect production equationsNil
top::Equations ::=
{
  top.vars = [];
  top.allScopes = [];
}

--------------------------------------------------

attribute scope, vars, errs, allScopes occurs on Equation;

propagate scope, errs on Equation;

aspect production localDeclEquation
top::Equation ::= id::String ty::Type e::Expr
{
  -- local decl
  local localDecl::SGDecl = mkLocalDecl(id, ty, location=top.location);
  localDecl.lex   = [];
  localDecl.var  = [];
  localDecl.nont = declNont.1;
  localDecl.attr = [];
  localDecl.occ  = [];
  localDecl.prod = [];
  localDecl.param = [];

  local declNont::([Decorated SGDecl], [ErrMessage]) =
    case ty of
    | nonterminalType(s) -> 
        let ref::SGRef = mkRef(s, lexNontDFA(), location=top.location) in
        let dRef::Decorated SGRef = decorate ref with {lex = [top.scope];} in
          if null(dRef.res) then 
            ([], [errMessage("Nonterminal " ++ s ++ " not known", 
                             top.location)])
          else if length(dRef.res) > 1 then
            ([], [errMessage("Ambiguous nonterminal reference " ++ s, 
                             top.location)])
          else (dRef.res, [])
        end end
    | _ -> ([], [])
    end;
  
  top.vars = [localDecl];

  top.errs <- declNont.2;
  top.allScopes = [localDecl];
}

aspect production assignEquation
top::Equation ::= lhs::LHS e::Expr
{ 
  top.vars = []; 
  top.allScopes = [];
}

--------------------------------------------------

synthesized attribute ty::Maybe<Type>;

attribute errs, scope, ty occurs on Expr;
propagate errs, scope on Expr;

aspect production appendListsExpr
top::Expr ::= e1::Expr e2::Expr
{ 
  top.ty = nothing(); -- todo
}

aspect production plusExpr
top::Expr ::= e1::Expr e2::Expr
{ 
  top.ty = case e1.ty, e2.ty of
           | just(intType()), just(intType()) -> 
               just(intType(location=top.location))
           | _, _ -> nothing()
           end;
}

aspect production numExpr
top::Expr ::= i::Integer
{
  top.ty = just(intType(location=top.location));
}

aspect production boolExpr
top::Expr ::= b::Boolean
{
  top.ty = just(boolType(location=top.location));
}

aspect production callExpr
top::Expr ::= id::String args::Exprs
{
  -- finding production scope, `LEX* PROD`
  local ref::SGRef = mkRef(id, lexProdDFA(), location=top.location);
  ref.lex = [top.scope];
  local refResult::[Decorated SGDecl] = ref.res;

  -- finding parameters for production, `PARAM` from result of above
  local params::[Decorated SGDecl] = concat(map((.param), refResult));

  -- types of params
  local paramTypes::[SGDatum] = filterMap((.datum), params);

  top.errs <-
    foldr (
      (\p::(SGDatum, Maybe<Type>) msgs::(Integer, [ErrMessage]) ->
         case p of
         | (datumTy(s, t1), just(t2)) when t1 != t2 -> 
             let msg::ErrMessage = 
               errMessage("Incompatible arg types", top.location)
             in
               (msgs.1 + 1, msg::msgs.2)
             end
         | _ -> (msgs.1 + 1, [])
         end
      ),
      (1, []),
      zip(paramTypes, args.exprTys)
    ).2;

  top.errs <-
    if null(refResult) then 
      [errMessage("Production " ++ id ++ " not known", top.location)]
    else if length(refResult) > 1 then
      [errMessage("Ambiguous production reference " ++ id, top.location)]
    else [];

  top.errs <- if length(paramTypes) > length(args.exprTys)
              then [errMessage("Too few arguments to " ++ id, top.location)]
              else if length(paramTypes) < length(args.exprTys)
              then [errMessage("Too many arguments to " ++ id, top.location)]
              else [];

  top.ty = case refResult of
           | s::[] when s.datum.isJust ->
               case s.datum.fromJust of
               | datumTy(s, ty) -> just(ty)
               | _ -> nothing()
               end
           | _ -> nothing()
           end;
}

aspect production listExpr
top::Expr ::= elements::Exprs
{
  top.ty = nothing(); -- todo
}

aspect production refExpr
top::Expr ::= r::Ref
{
  top.ty = r.ty;
}

aspect production ifExpr
top::Expr ::= c::Expr e1::Expr e2::Expr
{
  top.ty = case c.ty, e1.ty, e2.ty of
           | just(boolType()), just(t1), just(t2) when t1 == t2 -> e1.ty
           | _, _, _ -> nothing()
           end;
}

aspect production gtExpr
top::Expr ::= e1::Expr e2::Expr
{
  top.ty = case e1.ty, e2.ty of
           | just(t1), just(t2) when t1 == t2 -> 
               just(boolType(location=top.location))
           | _, _ -> nothing()
           end;
}

aspect production ltExpr
top::Expr ::= e1::Expr e2::Expr
{
  top.ty = case e1.ty, e2.ty of
           | just(t1), just(t2) when t1 == t2 -> 
               just(boolType(location=top.location))
           | _, _ -> nothing()
           end;
}

--------------------------------------------------

synthesized attribute exprTys::[Maybe<Type>];

attribute errs, scope, exprTys occurs on Exprs;
propagate errs, scope on Exprs;

aspect production exprsCons
top::Exprs ::= e::Expr es::Exprs
{ top.exprTys = e.ty :: es.exprTys; }

aspect production exprsNil
top::Exprs ::=
{ top.exprTys = []; }

--------------------------------------------------

synthesized attribute decl::Maybe<Decorated SGDecl>;
synthesized attribute qualTy::String;

attribute scope, errs, decl, qualTy, ty occurs on Ref;

propagate scope, errs on Ref;

aspect production fieldAccessRef
top::Ref ::= 
e::Expr id::String
{
  -- qualified reference, `NONT ATTR`
  local ref::SGRef = mkRef(id, nontAttrDFA(), location=top.location);
  ref.lex = case r.decl of | just(s) -> [s] | _ -> [] end;
  local refResult::[Decorated SGDecl] = if r.decl.isJust then ref.res else [];

  top.errs <-
    if !r.decl.isJust then
      [errMessage("Bad qualified reference ." ++ id, top.location)]
    else if null(refResult) then 
      [errMessage("Attribute ." ++ id ++ " does not occur on " ++ r.qualTy,
                  top.location)]
    else [];

  top.decl = 
    if !null(refResult) then just(head(refResult))
    else nothing();

  top.qualTy = r.qualTy ++ "." ++ id;

  top.ty = 
    if !null(refResult) 
    then case head(refResult).datum of
         | just(datumTy(s, ty)) -> just(ty)
         | _ -> nothing()
         end
    else nothing();
}

aspect production nameRef
top::Ref ::= id::String
{
  -- local reference, `LEX* VAR`
  local ref::SGRef = mkRef(id, lexVarDFA(), location=top.location);
  ref.lex = [top.scope];
  local refResult::[Decorated SGDecl] = ref.res;

  top.errs <-
    if null(refResult) then 
      [errMessage("Reference " ++ id ++ " not known", top.location)]
    else if length(refResult) > 1 then
      [errMessage("Ambiguous reference " ++ id, top.location)]
    else [];

  top.decl = 
    if null(refResult) then nothing()
    else just(head(refResult));

  top.qualTy = 
    case top.decl of
    | just(s) when s.datum.isJust -> s.datum.fromJust.name
    | _ -> ""
    end;

  top.ty = 
    if !null(refResult) 
    then case head(refResult).datum of
         | just(datumTy(s, ty)) -> just(ty)
         | _ -> nothing()
         end
    else nothing();
}

--------------------------------------------------

attribute scope, errs, decl, qualTy occurs on LHS;

propagate scope, errs on LHS;

aspect production refLHS
top::LHS ::= id::String
{
  -- local reference, `LEX* VAR`
  local ref::SGRef = mkRef(id, lexVarDFA(), location=top.location);
  ref.lex = [top.scope];
  local refResult::[Decorated SGDecl] = ref.res;

  top.errs <-
    if null(refResult) then 
      [errMessage("Reference " ++ id ++ " not visible", top.location)]
    else if length(refResult) > 1 then
      [errMessage("Ambiguous reference " ++ id, top.location)]
    else [];

  top.decl = 
    if null(refResult) then nothing()
    else just(head(refResult));

  top.qualTy = 
    case top.decl of
    | just(s) when s.datum.isJust -> s.datum.fromJust.name
    | _ -> ""
    end;
}

aspect production fieldAccessLHS
top::LHS ::= lhs::LHS id::String
{
  -- qualified reference, `NONT ATTR`
  local ref::SGRef = mkRef(id, nontAttrDFA(), location=top.location);
  ref.lex = case lhs.decl of | just(s) -> [s] | _ -> [] end;
  local refResult::[Decorated SGDecl] = if lhs.decl.isJust then ref.res else [];

  top.errs <-
    if null(refResult) then
      [errMessage("Attribute " ++ id ++ " does not occur on " ++ lhs.qualTy, 
                  top.location)]
    else if !lhs.decl.isJust then
      [errMessage("Bad qualified reference " ++ top.qualTy, top.location)]
    else [];

  top.decl = 
    if !null(refResult) then just(head(refResult))
    else nothing();
  
  top.qualTy = lhs.qualTy ++ "." ++ id;
}