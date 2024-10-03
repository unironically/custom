grammar custom:host:translation;

synthesized attribute defaultTranslation::String;

attribute 
  translationStr, defaultTranslation
occurs on Type;

propagate parentProdName on Type;

aspect production intType
top::Type ::= 
{
  top.translationStr = "Integer";
  top.defaultTranslation = "null";
}

aspect production boolType
top::Type ::=
{
  top.translationStr = "Boolean";
  top.defaultTranslation = "null";
}

aspect production stringType
top::Type ::=
{
  top.translationStr = "String";
  top.defaultTranslation = "null";
}

aspect production listType
top::Type ::= content::Type
{
  top.translationStr = "ArrayList<" ++ content.translationStr ++ ">";
  top.defaultTranslation = "null";
}

aspect production pairType
top::Type ::= t1::Type t2::Type
{
  top.translationStr = 
    "Pair<" ++ t1.translationStr ++ ", " ++ t2.translationStr ++ ">";
  top.defaultTranslation = "null";
}

aspect production nonterminalType
top::Type ::= id::String
{
  top.translationStr = id ++ "<? extends hasChild_" ++ id ++ "<?>>";
  top.defaultTranslation = "null";
}

aspect production funType
top::Type ::= ret::Type params::Types
{
  top.translationStr = "ERROR (Type.funType)";
  top.defaultTranslation = "null";
}

aspect production errType
top::Type ::=
{
  top.translationStr = "ERROR (Type.errType)";
  top.defaultTranslation = "null";
}



attribute 
  translationLst
occurs on Types;

aspect production typesCons
top::Types ::= t::Type ts::Types
{ top.translationLst = t.translationStr :: ts.translationLst; }
-- todo: how to handle function-type parameters?
--       not needed so far

aspect production typesNil
top::Types ::=
{ top.translationLst = []; }