grammar custom:host:abstractsyntax;


nonterminal TopDecl with location;

abstract production nonterminalDecl
top::TopDecl ::= id::String
{}

abstract production synAttributeDecl
top::TopDecl ::= id::String ty::Type
{}

abstract production inhAttributeDecl
top::TopDecl ::= id::String ty::Type
{}

abstract production occursDecl
top::TopDecl ::= ids::[String] ty::Type
{}

abstract production productionDecl
top::TopDecl ::= id::String ty::Type ps::Params eq::Equations
{}


nonterminal TopDecls with location;

abstract production topDeclsCons
top::TopDecls ::= d::TopDecl ds::TopDecls
{}

abstract production topDeclsNil
top::TopDecls ::=
{}