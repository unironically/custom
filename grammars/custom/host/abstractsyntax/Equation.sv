grammar custom:host:abstractsyntax;


nonterminal Equation with location, tyEnvSyn, tyEnvInh,
attrTyEnvInh;
propagate attrTyEnvInh on Equation;


abstract production localDeclEquation
top::Equation ::= id::String ty::Type e::Expr
{
  top.tyEnvSyn = [(id, ty)];
  e.tyEnvInh = top.tyEnvInh;
}

abstract production assignEquation
top::Equation ::= lhs::LHS e::Expr
{
  top.tyEnvSyn = [];
  e.tyEnvInh = top.tyEnvInh;
}


nonterminal Equations with location, tyEnvSyn, tyEnvInh,
attrTyEnvInh;
propagate attrTyEnvInh on Equations;

abstract production equationsCons
top::Equations ::= e::Equation es::Equations
{
  e.tyEnvInh = top.tyEnvInh;
  es.tyEnvInh = top.tyEnvInh;
  top.tyEnvSyn = e.tyEnvSyn ++ es.tyEnvSyn;
}

abstract production equationsNil
top::Equations ::=
{
  top.tyEnvSyn = [];
}