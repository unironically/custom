grammar custom:host:abstractsyntax;


synthesized attribute ty::Maybe<Type>;

nonterminal Expr with location, ty, tyEnvInh, attrTyEnvInh;
propagate attrTyEnvInh on Expr;

abstract production appendListsExpr
top::Expr ::= e1::Expr e2::Expr
{
  top.ty = case e1.ty, e2.ty of
           | just(listType(t1)), just(listType(t2)) when t1 == t2 -> 
               e1.ty
           | _, _ -> nothing()
           end;
  e1.tyEnvInh = top.tyEnvInh;
  e2.tyEnvInh = top.tyEnvInh;
}

abstract production plusExpr
top::Expr ::= e1::Expr e2::Expr
{
  top.ty = case e1.ty, e2.ty of
           | just(intType()), just(intType()) -> 
               just(intType(location=top.location))
           | _, _ -> nothing()
           end;
  e1.tyEnvInh = top.tyEnvInh;
  e2.tyEnvInh = top.tyEnvInh;
}

abstract production numExpr
top::Expr ::= i::Integer
{
  top.ty = just(intType(location=top.location));
}

abstract production boolExpr
top::Expr ::= b::Boolean
{
  top.ty = just(boolType(location=top.location));
}

abstract production callExpr
top::Expr ::= id::String args::Exprs
{
  top.ty = nothing(); -- todo
  args.tyEnvInh = top.tyEnvInh;
}

abstract production listExpr
top::Expr ::= elements::Exprs
{
  top.ty = case elements.ty of 
           | just(t) -> just(listType(t, location=top.location))
           | _ -> nothing()
           end;
  elements.tyEnvInh = top.tyEnvInh;
}

abstract production refExpr
top::Expr ::= r::Ref
{
  top.ty = r.ty;
  r.tyEnvInh = top.tyEnvInh;
}

abstract production pairExpr
top::Expr ::= e1::Expr e2::Expr
{
  top.ty = case e1.ty, e2.ty of
           | just(t1), just(t2) -> just(pairType(t1, t2, location=top.location))
           | _, _ -> nothing()
           end;
  e1.tyEnvInh = top.tyEnvInh;
  e2.tyEnvInh = top.tyEnvInh;
}

abstract production ifExpr
top::Expr ::= c::Expr e1::Expr e2::Expr
{
  top.ty = case c.ty, e1.ty, e2.ty of
           | just(boolType()), just(t1), just(t2) when t1 == t2 -> e1.ty
           | _, _, _ -> nothing()
           end;
  c.tyEnvInh = top.tyEnvInh;
  e1.tyEnvInh = top.tyEnvInh;
  e2.tyEnvInh = top.tyEnvInh;
}

abstract production gtExpr
top::Expr ::= e1::Expr e2::Expr
{
  top.ty = case e1.ty, e2.ty of
           | just(t1), just(t2) when t1 == t2 -> 
               just(boolType(location=top.location))
           | _, _ -> nothing()
           end;
  e1.tyEnvInh = top.tyEnvInh;
  e2.tyEnvInh = top.tyEnvInh;
}

abstract production ltExpr
top::Expr ::= e1::Expr e2::Expr
{
  top.ty = case e1.ty, e2.ty of
           | just(t1), just(t2) when t1 == t2 -> 
               just(boolType(location=top.location))
           | _, _ -> nothing()
           end;
  e1.tyEnvInh = top.tyEnvInh;
  e2.tyEnvInh = top.tyEnvInh;
}



nonterminal Exprs with location, ty, tyEnvInh, attrTyEnvInh;
propagate attrTyEnvInh on Exprs;

abstract production exprsCons
top::Exprs ::= e::Expr es::Exprs
{
  top.ty = 
    case e.ty, es.ty, es of
    | just(t1), nothing(), exprsNil() -> just(t1)
    | just(t1), just(t2), _ when t1 == t2 -> just(t1)
    | _, _, _ -> nothing()
    end;
  e.tyEnvInh = top.tyEnvInh;
  es.tyEnvInh = top.tyEnvInh;
}

abstract production exprsNil
top::Exprs ::=
{
  top.ty = nothing();
}


nonterminal Ref with location, ty, tyEnvInh, attrTyEnvInh;
propagate attrTyEnvInh on Ref;

abstract production fieldAccessRef
top::Ref ::= e::Expr id::String
{
  top.ty =
    case e.ty of
    | just(nonterminalType(s)) -> 
        case lookupTyEnv(id, top.attrTyEnvInh) of
        | just((t, b)) -> just(t)
        | _ -> nothing()
        end
    | _ -> nothing()
    end;

  e.tyEnvInh = top.tyEnvInh;
}

abstract production nameRef
top::Ref ::= id::String
{
  top.ty = case lookupTyEnv(id, top.tyEnvInh) of
           | just((t, b)) -> just(t)
           | _ -> nothing()
           end;
}