grammar custom:host:abstractsyntax;


nonterminal TopDecl with location;

abstract production nonterminalDecl
top::TopDecl ::= id::String
{}

abstract production attributeDecl
top::TopDecl ::= attrType::AttrType id::String ty::Type
{}

abstract production occursDecl
top::TopDecl ::= ids::[String] ty::Type
{}

abstract production productionDecl
top::TopDecl ::= id::String ty::Type ps::Children eq::Equations
{}

abstract production functionDecl
top::TopDecl ::= id::String ty::Type cs::Children e::Expr
{}


nonterminal TopDecls with location;

abstract production topDeclsCons
top::TopDecls ::= d::TopDecl ds::TopDecls
{}

abstract production topDeclsNil
top::TopDecls ::=
{}


nonterminal Children with location;

abstract production childrenCons
top::Children ::= c::Child cs::Children
{}

abstract production childrenNil
top::Children ::= 
{}

nonterminal Child with location;

abstract production child
top::Child ::= id::String ty::Type
{}


nonterminal AttrType with location;

abstract production attrTypeSyn
top::AttrType ::= 
{}

abstract production attrTypeInh
top::AttrType ::=
{}