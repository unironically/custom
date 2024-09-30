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

abstract production funType
top::Type ::= ret::Type params::Types
{}

abstract production errType
top::Type ::=
{}


instance Eq Type {
  eq = tyEq;
  neq = tyNeq;
}

fun tyEq Boolean ::= t1::Type t2::Type =
  case t1, t2 of
  | intType(), intType() -> true
  | boolType(), boolType() -> true
  | stringType(), stringType() -> true
  | listType(lt1), listType(lt2) -> lt1 == lt2
  | nonterminalType(s1), nonterminalType(s2) -> s1 == s2
  | errType(), _ -> true
  | _, errType() -> true
  | _, _ -> false
  end;

fun tyNeq Boolean ::= t1::Type t2::Type = !tyEq(t1, t2);



nonterminal Types with location;

abstract production typesCons
top::Types ::= t::Type ts::Types
{

}

abstract production typesNil
top::Types ::=
{

}