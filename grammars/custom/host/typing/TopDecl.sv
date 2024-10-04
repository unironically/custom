grammar custom:host:typing;

monoid attribute allAttrsSyn::[Attribute] with [], ++;
inherited attribute allAttrsInh::[Attribute];

monoid attribute allNontsSyn::[Decorated Nonterminal] with [], ++;
inherited attribute allNontsInh::[Decorated Nonterminal];

monoid attribute allOccsSyn::[(Attribute, Decorated Nonterminal)] with [], ++;
inherited attribute allOccsInh::[(Attribute, Decorated Nonterminal)];



-- TopDecl

attribute 
  allAttrsSyn, allAttrsInh,
  allNontsSyn, allNontsInh,
  allOccsSyn, allOccsInh
occurs on TopDecl;

propagate 
  allAttrsInh, allNontsInh, allOccsInh 
on TopDecl;
propagate allAttrsSyn on TopDecl excluding attributeDecl;
propagate allNontsSyn on TopDecl excluding nonterminalDecl;
propagate allOccsSyn  on TopDecl excluding occursDecl;

aspect production nonterminalDecl
top::TopDecl ::= id::String
{
  local attrsForNont::[Attribute] = lookupOccsForNont(id, top.allOccsInh);
  local nt::Nonterminal = nont(id); nt.attrs = attrsForNont;

  top.allNontsSyn := [nt];
}

aspect production attributeDecl
top::TopDecl ::= attrType::AttrType id::String ty::Type
{
  top.allAttrsSyn := case attrType of
                  | attrTypeSyn() -> [synAttr(id, ty)]
                  | attrTypeInh() -> [inhAttr(id, ty)]
                  end;
}

aspect production occursDecl
top::TopDecl ::= attrId::String nontId::String
{
  local attrReferenced::Maybe<Attribute> =
    lookupAttr(attrId, top.allAttrsInh);
  local ntReferenced::Maybe<Decorated Nonterminal> =
    lookupNt(nontId, top.allNontsInh);

  top.allOccsSyn := case attrReferenced, ntReferenced of
                    | just(at), just(nt) -> [(at, nt)]
                    | _, _ -> []
                    end;
}

aspect production productionDecl
top::TopDecl ::= id::String nont::String ps::Children eq::Equations
{ 
}

aspect production functionDecl
top::TopDecl ::= id::String ty::Type cs::Children e::Expr
{ 
} 



-- TopDecls

attribute 
  allAttrsSyn, allAttrsInh,
  allNontsSyn, allNontsInh,
  allOccsSyn, allOccsInh
occurs on TopDecls;

propagate 
  allAttrsSyn, allAttrsInh, 
  allNontsSyn, allNontsInh,
  allOccsSyn, allOccsInh
on TopDecls;

aspect production topDeclsCons
top::TopDecls ::= d::TopDecl ds::TopDecls
{
}

aspect production topDeclsNil
top::TopDecls ::=
{
}



-- Children

aspect production childrenCons
top::Children ::= c::Child cs::Children
{
}

aspect production childrenNil
top::Children ::= 
{
}



--Child

aspect production child
top::Child ::= id::String ty::Type
{
}