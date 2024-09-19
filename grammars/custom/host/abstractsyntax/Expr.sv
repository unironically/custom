grammar custom:host:abstractsyntax;


nonterminal Expr with location;

abstract production appendListsExpr
top::Expr ::= e1::Expr e2::Expr
{}

abstract production plusExpr
top::Expr ::= e1::Expr e2::Expr
{}

abstract production numExpr
top::Expr ::= i::Integer
{}

abstract production boolExpr
top::Expr ::= b::Boolean
{}

abstract production callExpr
top::Expr ::= id::String args::[Expr]
{}

abstract production listExpr
top::Expr ::= elements::[Expr]
{}

abstract production refExpr
top::Expr ::= id::String
{}

abstract production fieldAccessExpr
top::Expr ::= e::Expr id::String
{}
