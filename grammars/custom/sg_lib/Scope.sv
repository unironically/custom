grammar custom:sg_lib;

imports custom:host:abstractsyntax;

--------------------------------------------------

nonterminal SGNode with location;

synthesized attribute id::Integer occurs on SGNode;
synthesized attribute datum::Maybe<SGDatum> occurs on SGNode;

inherited attribute occ::[Decorated SGNode] occurs on SGNode;
inherited attribute nont::[Decorated SGNode] occurs on SGNode;
inherited attribute attr::[Decorated SGNode] occurs on SGNode;
inherited attribute lex::[Decorated SGNode] occurs on SGNode;
inherited attribute var::[Decorated SGNode] occurs on SGNode;
inherited attribute prod::[Decorated SGNode] occurs on SGNode;
inherited attribute param::[Decorated SGNode] occurs on SGNode;

abstract production mkNode
top::SGNode ::=
  datum::Maybe<SGDatum>
{
  top.id = genInt();
  top.datum = datum;
}

--------------------------------------------------

type SGScope = SGNode;

abstract production mkScope
top::SGScope ::=
{ forwards to mkNode(nothing(), location=top.location); }

--------------------------------------------------

type SGDecl = SGNode;

abstract production mkNontDecl
top::SGDecl ::= name::String
{ forwards to mkNode(just(datumNont(name, location=top.location)), location=top.location); }

abstract production mkAttrDecl
top::SGDecl ::= name::String ty::Type
{ forwards to mkNode(just(datumTy(name, ty, location=top.location)), location=top.location); }

abstract production mkOccursDecl
top::SGDecl ::= name::String
{ forwards to mkNode(just(datumOccurs(name, location=top.location)), location=top.location); }

abstract production mkProdDecl
top::SGDecl ::= name::String ty::Type
{ forwards to mkNode(just(datumTy(name, ty, location=top.location)), location=top.location); }

abstract production mkFunDecl
top::SGDecl ::= name::String ty::Type
{ forwards to mkNode(just(datumTy(name, ty, location=top.location)), location=top.location); }

abstract production mkLocalDecl
top::SGDecl ::= name::String ty::Type
{ forwards to mkNode(just(datumTy(name, ty, location=top.location)), location=top.location); }

--------------------------------------------------

synthesized attribute name::String;
synthesized attribute res::[Decorated SGDecl];
attribute id occurs on SGRef;

nonterminal SGRef with name, lex, res, location;

abstract production mkRef
top::SGRef ::= name::String dfa::DFA
{
  top.id = genInt();
  top.name = name;

  top.res = dfa.decls(top, head(top.lex));
}

abstract production mkOccurenceRef
top::SGRef ::= name::String
{
  top.id = genInt();

  local dfa::DFA = lexOccDFA();
  top.name = name;
  top.res = dfa.decls(top, head(top.lex));
}

abstract production mkNonterminalRef
top::SGRef ::= name::String
{
  top.id = genInt();

  local dfa::DFA = lexNontDFA();
  top.name = name;
  top.res = dfa.decls(top, head(top.lex));
}

--------------------------------------------------

synthesized attribute test::(Boolean ::= Decorated SGRef);

nonterminal SGDatum with name, test, location;

--synthesized attribute str::String occurs on Datum;

abstract production datumNont
top::SGDatum ::= name::String
{
  top.name = name;
  top.test = \r::Decorated SGRef -> r.name == top.name;
}

abstract production datumAttr
top::SGDatum ::= name::String ty::Type
{
  top.name = name;
  top.test = \r::Decorated SGRef -> r.name == top.name;
}

abstract production datumOccurs
top::SGDatum ::= name::String
{
  top.name = name;
  top.test = \r::Decorated SGRef -> r.name == top.name;
}

abstract production datumProd
top::SGDatum ::= name::String ty::Type
{
  top.name = name;
  top.test = \r::Decorated SGRef -> r.name == top.name;
}

abstract production datumFun
top::SGDatum ::= name::String ty::Type
{
  top.name = name;
  top.test = \r::Decorated SGRef -> r.name == top.name;
}

abstract production datumLocal
top::SGDatum ::= name::String ty::Type
{
  top.name = name;
  top.test = \r::Decorated SGRef -> r.name == top.name;
}

abstract production datumTy
top::SGDatum ::= name::String ty::Type
{
  top.name = name;
  top.test = \r::Decorated SGRef -> r.name == top.name;
}