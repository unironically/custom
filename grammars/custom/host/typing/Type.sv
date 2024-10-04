grammar custom:host:typing;

synthesized attribute type::Maybe<Type>;



-- Type

aspect production intType
top::Type ::= 
{}

aspect production boolType
top::Type ::=
{}

aspect production stringType
top::Type ::=
{}

aspect production listType
top::Type ::= content::Type
{}


aspect production pairType
top::Type ::= t1::Type t2::Type
{}


aspect production nonterminalType
top::Type ::= id::String
{}


aspect production funType
top::Type ::= ret::Type params::Types
{}


aspect production errType
top::Type ::=
{}




-- Types

aspect production typesCons
top::Types ::= t::Type ts::Types
{
}

aspect production typesNil
top::Types ::=
{
}