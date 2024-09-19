grammar custom:host:abstractsyntax;


nonterminal Type with location;

abstract production intType
top::Type ::= 
{}

abstract production boolType
top::Type ::=
{}

abstract production stringType
top::Type ::=
{}

abstract production listType
top::Type ::= content::Type
{}

abstract production nonterminalType
top::Type ::= id::String
{}