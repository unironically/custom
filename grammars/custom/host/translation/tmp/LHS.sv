grammar custom:host:abstractsyntax;


nonterminal LHS with
  translationStr;

abstract production refLHS
top::LHS ::= id::String
{
  top.translationStr = id;
}

abstract production fieldAccessLHS
top::LHS ::= id1::String id2::String
{
  top.translationStr = id1 ++ "." ++ id2;
}