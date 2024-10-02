grammar custom:host:abstractsyntax;

nonterminal File with location;

abstract production file
top::File ::= ds::TopDecls
{ ds.tyEnvInh = ds.tyEnvSyn; 
  ds.occursEnvInh = ds.occursEnvSyn; }