grammar custom:host:translation;

--synthesized attribute message::String;

aspect production errMessage
top::ErrMessage ::= msg::String loc::Location
{ 
  --top.message = "Error " ++ toString(loc.line) ++ "," ++ toString(loc.column) ++
  --              ": " ++ msg ++ "\n"; 
}