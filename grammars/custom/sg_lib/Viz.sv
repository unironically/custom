grammar custom:sg_lib;

--------------------------------------------------

synthesized attribute graphvizString::String occurs on SGNode, SGRef;

aspect production mkNode
top::SGNode ::=
  datum::Maybe<SGDatum>
{
  local lexEdgesString::String = 
    concat(map(edgeStyle("LEX", "black", top, _), top.lex));
  local varEdgesString::String = --"";
    concat(map(edgeStyle("VAR", "green", top, _), top.var));
  local nontEdgesString::String = 
    concat(map(edgeStyle("NONT", "blue", top, _), top.nont));
  local attrEdgesString::String = 
    concat(map(edgeStyle("ATTR", "red", top, _), top.attr));
  local occEdgesString::String =
    concat(map(edgeStyle("OCC", "orange", top, _), top.occ));
  local prodEdgesString::String =
    concat(map(edgeStyle("PROD", "pink", top, _), top.prod));
  local paramEdgesString::String =
    concat(map(edgeStyle("PARAM", "yellow", top, _), top.param));
  
  top.graphvizString = 
    "{" ++ lexEdgesString ++ 
           varEdgesString ++ 
           nontEdgesString ++ 
           attrEdgesString ++ 
           occEdgesString ++
           prodEdgesString ++
           paramEdgesString ++
    "}\n";

}

aspect production mkRef
top::SGRef ::= name::String dfa::DFA
{
  local lexEdgesString::String = 
    concat(map(refEdgeStyle("LEX", "gray", top, _), top.lex));

  top.graphvizString = "{" ++ lexEdgesString ++ "}\n";
}

aspect production mkOccurenceRef
top::SGRef ::= name::String
{
  local lexEdgesString::String = 
    concat(map(refEdgeStyle("LEX", "gray", top, _), top.lex));

  top.graphvizString = "{" ++ lexEdgesString ++ "}\n";
}

aspect production mkNonterminalRef
top::SGRef ::= name::String
{
  local lexEdgesString::String = 
    concat(map(refEdgeStyle("LEX", "gray", top, _), top.lex));

  top.graphvizString = "{" ++ lexEdgesString ++ "}\n";
}

--------------------------------------------------

function graphvizScopes
String ::= scopes::[Decorated SGNode] refs::[Decorated SGRef]
{
  return "digraph {layout=dot\n" ++ 
  
    implode("\n", map(nodeStyle, scopes)) ++ "\n" ++ 
    implode("\n", map(refStyle, refs)) ++ "\n" ++ 
    
    implode("\n", map((.graphvizString), scopes)) ++ "\n" ++
    implode("\n", map((.graphvizString), refs)) ++ "\n" ++
  
  "}\n";
}

function refStyle
String ::= r::Decorated SGRef
{
  local refStr::String = r.name ++ "_" ++ toString(r.location.line) ++ 
                                   "_" ++ toString(r.location.column);

  return "{ node [label=\"" ++ refStr ++ "\" shape=rect " ++ 
         "fontsize=12 margin=0 color=gray fillcolor=white] " ++ 
         toString(r.id) ++ "}";
}

function nodeStyle
String ::= s::Decorated SGNode
{
  local datumString::String =
    case s.datum of
    | just(d) -> d.name ++ "_" ++ 
        toString(d.location.line) ++ "_" ++ toString(d.location.column)
    | _ -> "()"
    end;
  
  return "{ node [label=\"" ++ toString(s.id) ++ " ↦ " ++ datumString ++ "\" style=rounded shape=rect fontsize=12 margin=0 fillcolor=white] " ++ toString(s.id) ++ "}";
}

function edgeStyle
String ::= lab::String col::String src::Decorated SGNode tgt::Decorated SGNode
{
  return "{edge [label=" ++ lab ++ " color=\"" ++ col ++ "\" " ++ 
    "fontcolor=\"" ++ col ++ "\"] " ++ 
    toString(src.id) ++ "->" ++ toString(tgt.id) ++ "}";
}

function refEdgeStyle
String ::= lab::String col::String src::Decorated SGRef tgt::Decorated SGNode
{
  return "{edge [label=" ++ lab ++ " color=\"" ++ col ++ "\" " ++ 
    "fontcolor=\"" ++ col ++ "\"] " ++ 
    toString(src.id) ++ "->" ++ toString(tgt.id) ++ "}";
}