grammar custom:host:abstractsyntax;


nonterminal Equation with location;

abstract production localDeclEquation
top::Equation ::= id::String ty::Type e::Expr
{}

abstract production assignEquation
top::Equation ::= lhs::LHS e::Expr
{}


nonterminal Equations with location;

abstract production equationsCons
top::Equations ::= e::Equation es::Equations
{}

abstract production equationsNil
top::Equations ::=
{}