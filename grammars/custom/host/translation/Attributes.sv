grammar custom:host:translation;

synthesized attribute filesToWrite::[(String, String)];

monoid attribute nonterminals::[String] with [], ++;
monoid attribute occurTransPairs::[(String, String)] with [], ++;
monoid attribute prodDeclsTrans::[(String, String)] with [], ++;

synthesized attribute translationStr::String;
synthesized attribute translationLst::[String];

inherited attribute parentProdName::String;
inherited attribute childOrLocalNodeType::Boolean;

monoid attribute localsNamesTypes::[(String, Type)] with [], ++;
monoid attribute localDeclsTrans::[String] with [], ++;