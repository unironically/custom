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
top::Expr ::= id::String args::Exprs
{}

abstract production listExpr
top::Expr ::= elements::Exprs
{}

abstract production refExpr
top::Expr ::= r::Ref
{}

abstract production ifExpr
top::Expr ::= c::Expr e1::Expr e2::Expr
{}

abstract production gtExpr
top::Expr ::= e1::Expr e2::Expr
{}

abstract production ltExpr
top::Expr ::= e1::Expr e2::Expr
{}


nonterminal Exprs with location;

abstract production exprsCons
top::Exprs ::= e::Expr es::Exprs
{}

abstract production exprsNil
top::Exprs ::=
{}


nonterminal Ref with location;

abstract production fieldAccessRef
top::Ref ::= e::Expr id::String
{}

abstract production nameRef
top::Ref ::= id::String
{}