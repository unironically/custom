grammar custom:host:abstractsyntax;

inherited attribute occursEnvInh::[(String, String)];
monoid attribute occursEnvSyn::[(String, String)] with [], ++;

nonterminal TopDecl with 
  location, tyEnvSyn, tyEnvInh, occursEnvInh, occursEnvSyn;

propagate occursEnvSyn on TopDecl excluding occursDecl;

abstract production nonterminalDecl
top::TopDecl ::= id::String
{
  top.tyEnvSyn = [(id, nonterminalType(id, location=top.location))];
}

abstract production attributeDecl
top::TopDecl ::= attrType::AttrType id::String ty::Type
{
  top.tyEnvSyn = [(id, ty)];
}

abstract production occursDecl
top::TopDecl ::= attrId::String nontId::String
{ 
  top.tyEnvSyn = []; 
  top.occursEnvSyn := [(nontId, attrId)];
}

abstract production productionDecl
top::TopDecl ::= id::String nont::String ps::Children eq::Equations
{ top.tyEnvSyn = []; } -- todo?

abstract production functionDecl
top::TopDecl ::= id::String ty::Type cs::Children e::Expr
{ top.tyEnvSyn = []; } -- todo?


nonterminal TopDecls with 
  location, tyEnvSyn, tyEnvInh, occursEnvInh, occursEnvSyn;

propagate occursEnvInh, occursEnvSyn on TopDecls;

abstract production topDeclsCons
top::TopDecls ::= d::TopDecl ds::TopDecls
{
  d.tyEnvInh = top.tyEnvInh;
  ds.tyEnvInh = top.tyEnvInh;
  top.tyEnvSyn = d.tyEnvSyn ++ ds.tyEnvSyn;
}

abstract production topDeclsNil
top::TopDecls ::=
{
  top.tyEnvSyn = [];
}



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


nonterminal AttrType with location, isSyn;
synthesized attribute isSyn::Boolean;

abstract production attrTypeSyn
top::AttrType ::= 
{
  top.isSyn = true;
}

abstract production attrTypeInh
top::AttrType ::=
{
  top.isSyn = false;
}