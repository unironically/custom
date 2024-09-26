grammar custom:sg_lib;


--------------------------------------------------

synthesized attribute decls::
  ([Decorated SGDecl] ::= Decorated SGRef Decorated SGScope);

nonterminal DFA with decls;


{- For an occurence declaration to lookup the attribute declared -}
abstract production lexOccDFA
top::DFA ::= 
{
  -- LEX* OCC

  local state0::DFAState = stateLexOcc();
  local final::DFAState  = stateFinal();
  local sink::DFAState   = stateSink();

  state0.lexT  = state0;
  state0.varT  = sink;
  state0.nontT = sink;
  state0.attrT = sink;
  state0.occT = final;
  state0.prodT = sink;
  state0.paramT = sink;

  final.lexT  = sink;
  final.varT  = sink;
  final.nontT = sink;
  final.attrT = sink;
  final.occT = sink;
  final.prodT = sink;
  final.paramT = sink;

  sink.lexT  = sink;
  sink.varT  = sink;
  sink.nontT = sink;
  sink.attrT = sink;
  sink.occT = sink;
  sink.prodT = sink;
  sink.paramT = sink;

  top.decls = state0.decls;
}

{- For an occurence declaration to lookup the attribute declared -}
abstract production lexAttrDFA
top::DFA ::= 
{
  -- LEX* ATTR

  local state0::DFAState = stateLexAttr();
  local final::DFAState  = stateFinal();
  local sink::DFAState   = stateSink();

  state0.lexT  = state0;
  state0.varT  = sink;
  state0.nontT = sink;
  state0.attrT = final;
  state0.occT  = sink;
  state0.prodT = sink;
  state0.paramT = sink;

  final.lexT  = sink;
  final.varT  = sink;
  final.nontT = sink;
  final.attrT = sink;
  final.occT  = sink;
  final.prodT = sink;
  final.paramT = sink;

  sink.lexT  = sink;
  sink.varT  = sink;
  sink.nontT = sink;
  sink.attrT = sink;
  sink.occT  = sink;
  sink.prodT = sink;
  sink.paramT = sink;

  top.decls = state0.decls;
}

{- For an occurence declaration to lookup the nonterminal it is on -}
abstract production lexNontDFA
top::DFA ::= 
{
  -- LEX* NONT

  local state0::DFAState = stateLexNont();
  local final::DFAState  = stateFinal();
  local sink::DFAState   = stateSink();

  state0.lexT  = state0;
  state0.varT  = sink;
  state0.nontT = final;
  state0.attrT = sink;
  state0.occT = sink;
  state0.prodT = sink;
  state0.paramT = sink;

  final.lexT  = sink;
  final.varT  = sink;
  final.nontT = sink;
  final.attrT = sink;
  final.occT = sink;
  final.prodT = sink;
  final.paramT = sink;

  sink.lexT  = sink;
  sink.varT  = sink;
  sink.nontT = sink;
  sink.attrT = sink;
  sink.occT = sink;
  sink.prodT = sink;
  sink.paramT = sink;

  top.decls = state0.decls;
}

{- For local lookups -}
abstract production lexVarDFA
top::DFA ::= 
{
  -- LEX* VAR

  local state0::DFAState = stateLexVar();
  local final::DFAState  = stateFinal();
  local sink::DFAState   = stateSink();

  state0.lexT  = state0;
  state0.varT  = final;
  state0.nontT = sink;
  state0.attrT = sink;
  state0.occT  = sink;
  state0.prodT = sink;
  state0.paramT = sink;

  final.lexT  = sink;
  final.varT  = sink;
  final.nontT = sink;
  final.attrT = sink;
  final.occT  = sink;
  final.prodT = sink;
  final.paramT = sink;

  sink.lexT  = sink;
  sink.varT  = sink;
  sink.nontT = sink;
  sink.attrT = sink;
  sink.occT  = sink;
  sink.prodT = sink;
  sink.paramT = sink;

  top.decls = state0.decls;
}

{- For qualified local lookups -}
abstract production nontAttrDFA
top::DFA ::= 
{
  -- NONT ATTR

  local state0::DFAState = stateNont();
  local state1::DFAState = stateAttr();
  local final::DFAState  = stateFinal();
  local sink::DFAState   = stateSink();

  state0.lexT  = sink;
  state0.varT  = sink;
  state0.nontT = state1;
  state0.attrT = sink;
  state0.occT  = sink;
  state0.prodT = sink;
  state0.paramT = sink;

  state1.lexT  = sink;
  state1.varT  = sink;
  state1.nontT = sink;
  state1.attrT = final;
  state1.occT  = sink;
  state1.prodT = sink;
  state1.paramT = sink;

  final.lexT  = sink;
  final.varT  = sink;
  final.nontT = sink;
  final.attrT = sink;
  final.occT  = sink;
  final.prodT = sink;
  final.paramT = sink;

  sink.lexT  = sink;
  sink.varT  = sink;
  sink.nontT = sink;
  sink.attrT = sink;
  sink.occT = sink;
  sink.prodT = sink;
  sink.paramT = sink;

  top.decls = state0.decls;
}

{- For production name lookups -}
abstract production lexProdDFA
top::DFA ::= 
{
  -- LEX* PROD

  local state0::DFAState = stateLexProd();
  local final::DFAState  = stateFinal();
  local sink::DFAState   = stateSink();

  state0.lexT  = state0;
  state0.varT  = sink;
  state0.nontT = sink;
  state0.attrT = sink;
  state0.occT  = sink;
  state0.prodT = final;
  state0.paramT = sink;

  final.lexT  = sink;
  final.varT  = sink;
  final.nontT = sink;
  final.attrT = sink;
  final.occT  = sink;
  final.prodT = sink;
  final.paramT = sink;

  sink.lexT  = sink;
  sink.varT  = sink;
  sink.nontT = sink;
  sink.attrT = sink;
  sink.occT = sink;
  sink.prodT = sink;
  sink.paramT = sink;

  top.decls = state0.decls;
}

--------------------------------------------------

inherited attribute lexT::Decorated DFAState;
inherited attribute varT::Decorated DFAState;
inherited attribute nontT::Decorated DFAState;
inherited attribute attrT::Decorated DFAState;
inherited attribute occT::Decorated DFAState;
inherited attribute prodT::Decorated DFAState;
inherited attribute paramT::Decorated DFAState;

nonterminal DFAState with nontT, attrT, varT, lexT, occT, prodT, paramT, decls;

abstract production stateLexVar
top::DFAState ::=
{
  top.decls = \r::Decorated SGRef cur::Decorated SGScope ->
    let 
      lexRes::[Decorated SGDecl] = 
        concat(map(top.lexT.decls(r, _), cur.lex)) 
    in
    let 
      varRes::[Decorated SGDecl] = 
        concat(map(top.varT.decls(r, _), cur.var)) 
    in
      if !null(varRes) then varRes
      else lexRes
    end end;
}

abstract production stateLexAttr
top::DFAState ::=
{
  top.decls = \r::Decorated SGRef cur::Decorated SGScope ->
    let 
      lexRes::[Decorated SGDecl] = 
        concat(map(top.lexT.decls(r, _), cur.lex)) 
    in
    let 
      attrRes::[Decorated SGDecl] = 
        concat(map(top.attrT.decls(r, _), cur.attr)) 
    in
      if !null(attrRes) then attrRes
      else lexRes
    end end;
}

abstract production stateLexNont
top::DFAState ::=
{
  top.decls = \r::Decorated SGRef cur::Decorated SGScope ->
    let 
      lexRes::[Decorated SGDecl] = 
        concat(map(top.lexT.decls(r, _), cur.lex)) 
    in
    let 
      nontRes::[Decorated SGDecl] = 
        concat(map(top.nontT.decls(r, _), cur.nont)) 
    in
      if !null(nontRes) then nontRes
      else lexRes
    end end;
}

abstract production stateLexOcc
top::DFAState ::=
{
  top.decls = \r::Decorated SGRef cur::Decorated SGScope ->
    let 
      lexRes::[Decorated SGDecl] = 
        concat(map(top.lexT.decls(r, _), cur.lex)) 
    in
    let 
      occRes::[Decorated SGDecl] = 
        concat(map(top.occT.decls(r, _), cur.occ)) 
    in
      if !null(occRes) then occRes
      else lexRes
    end end;
}

abstract production stateLexProd
top::DFAState ::=
{
  top.decls = \r::Decorated SGRef cur::Decorated SGScope ->
    let 
      lexRes::[Decorated SGDecl] = 
        concat(map(top.lexT.decls(r, _), cur.lex)) 
    in
    let 
      prodRes::[Decorated SGDecl] = 
        concat(map(top.prodT.decls(r, _), cur.prod)) 
    in
      if !null(prodRes) then prodRes
      else lexRes
    end end;
}

abstract production stateNont
top::DFAState ::=
{
  top.decls = \r::Decorated SGRef cur::Decorated SGScope ->
    concat(map(top.nontT.decls(r, _), cur.nont));
}

abstract production stateAttr
top::DFAState ::=
{
  top.decls = \r::Decorated SGRef cur::Decorated SGScope ->
    concat(map(top.attrT.decls(r, _), cur.attr));
}

abstract production stateParam
top::DFAState ::=
{
  top.decls = \r::Decorated SGRef cur::Decorated SGScope ->
    concat(map(top.paramT.decls(r, _), cur.param));
}

abstract production stateFinal
top::DFAState ::=
{
  top.decls = \r::Decorated SGRef cur::Decorated SGScope ->
    case cur.datum of
    | just(d) when d.test(r) -> [cur]
    | _ -> []
    end;
}

abstract production stateSink
top::DFAState ::=
{ top.decls = \_ _ -> []; }