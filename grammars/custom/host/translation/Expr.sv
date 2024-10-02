grammar custom:host:translation;

synthesized attribute preExprTranslation::[String];

attribute
  translationStr, preExprTranslation
occurs on Expr;

aspect production appendListsExpr
top::Expr ::= e1::Expr e2::Expr
{ top.translationStr = "TODO"; top.preExprTranslation = []; }

aspect production plusExpr
top::Expr ::= e1::Expr e2::Expr
{ top.translationStr = "TODO"; top.preExprTranslation = []; }

aspect production numExpr
top::Expr ::= i::Integer
{ top.translationStr = "TODO"; top.preExprTranslation = []; }

aspect production boolExpr
top::Expr ::= b::Boolean
{ top.translationStr = "TODO"; top.preExprTranslation = []; }

aspect production callExpr
top::Expr ::= id::String args::Exprs
{ top.translationStr = "TODO"; top.preExprTranslation = []; }

aspect production listExpr
top::Expr ::= elements::Exprs
{ top.translationStr = "TODO"; top.preExprTranslation = []; }

aspect production refExpr
top::Expr ::= r::Ref
{ top.translationStr = "TODO"; top.preExprTranslation = []; }

aspect production ifExpr
top::Expr ::= c::Expr e1::Expr e2::Expr
{ top.translationStr = "TODO"; top.preExprTranslation = []; }

aspect production gtExpr
top::Expr ::= e1::Expr e2::Expr
{ top.translationStr = "TODO"; top.preExprTranslation = []; }

aspect production ltExpr
top::Expr ::= e1::Expr e2::Expr
{ top.translationStr = "TODO"; top.preExprTranslation = []; }



aspect production exprsCons
top::Exprs ::= e::Expr es::Exprs
{}

aspect production exprsNil
top::Exprs ::=
{}




aspect production fieldAccessRef
top::Ref ::= e::Expr id::String
{}

aspect production nameRef
top::Ref ::= id::String
{}