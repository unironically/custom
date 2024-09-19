grammar custom:host:abstractsyntax;


nonterminal LHS with location;

abstract production refLHS
top::LHS ::= id::String
{}

abstract production fieldAccessLHS
top::LHS ::= lhs::LHS id::String
{}