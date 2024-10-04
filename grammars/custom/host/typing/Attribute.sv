grammar custom:host:typing;

inherited attribute attrs::[Attribute];
synthesized attribute name::String;


-- Attribute

nonterminal Attribute with name, type;

abstract production synAttr
top::Attribute ::= name::String type::Type
{
  top.name = name;
  top.type = just(type);
}

abstract production inhAttr
top::Attribute ::= name::String type::Type
{
  top.name = name;
  top.type = just(type);
}



-- Nonterminal

nonterminal Nonterminal with name, attrs;

abstract production nont
top::Nonterminal ::= name::String
{
  top.name = name;
}


-- Functions

function lookupAttr
Maybe<Attribute> ::= name::String attrsList::[Attribute]
{
  return case attrsList of
         | synAttr(n, ty)::_ when n == name -> just(synAttr(n, ty))
         | inhAttr(n, ty)::_ when n == name -> just(inhAttr(n, ty))
         | _::t -> lookupAttr(name, t)
         | [] -> nothing()
         end;
}

function lookupNt
Maybe<Decorated Nonterminal> ::= name::String nontsList::[Decorated Nonterminal]
{
  return case nontsList of
         | nt::_ when nt.name == name -> just(nt)
         | _::t -> lookupNt(name, t)
         | [] -> nothing()
         end;
}

function lookupOccsForNont
[Attribute] ::= name::String occsList::[(Attribute, Decorated Nonterminal)]
{
  return case occsList of
         | (at, nt)::t when at.name == name -> at :: lookupOccsForNont(name, t)
         | _::t -> lookupOccsForNont(name, t)
         | [] -> []
         end;
}