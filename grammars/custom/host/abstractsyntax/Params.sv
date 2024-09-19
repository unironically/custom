grammar custom:host:abstractsyntax;


nonterminal Param with location;

abstract production param
top::Param ::= id::String ty::Type
{}


nonterminal Params with location;

abstract production paramsCons
top::Params ::= p::Param ps::Params
{}

abstract production paramsNil
top::Params ::= 
{}