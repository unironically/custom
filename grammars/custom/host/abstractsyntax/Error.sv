grammar custom:host:abstractsyntax;

--synthesized attribute message::String;

nonterminal ErrMessage;-- with message;

abstract production errMessage
top::ErrMessage ::= msg::String loc::Location
{ 
  --top.message = "Error " ++ toString(loc.line) ++ "," ++ toString(loc.column) ++
  --              ": " ++ msg ++ "\n"; 
}