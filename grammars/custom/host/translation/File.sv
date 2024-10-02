grammar custom:host:translation;

imports custom:host:abstractsyntax;

inherited attribute grammarName::String;

attribute
  filesToWrite, grammarName
occurs on File;

aspect production file
top::File ::= ds::TopDecls
{
  top.filesToWrite =
    map (
      (
        \nt::String ->

          -- syn occurs
          let occurTrans::[(String, String)] =
            filter((\p::(String, String) -> p.1 == nt), ds.occurTransPairs)
          in
          let allOccursTrans::[String] = 
            map((\p::(String, String) -> p.2), occurTrans)
          in

          -- prods
          let prodsForNt::[(String, String)] = 
            filter((\p::(String, String) -> p.1 == nt), ds.prodDeclsTrans)
          in
          let prodsTrans::[String] = 
            map((\p::(String, String) -> p.2), prodsForNt)
          in
            stitchFile(
              top.grammarName,
              nt, 
              allOccursTrans,
              prodsTrans
            )
          end end end end
      ),
      ds.nonterminals
    );

  ds.inhAttrsInh = ds.inhAttrs;
  ds.synAttrsInh = ds.synAttrs;
  ds.inhOccursInh = ds.inhOccurs;
  ds.synOccursInh = ds.synOccurs;
}

--------------------------------------------------------------------------------

function stitchFile
(String, String) ::= 
  grammarName::String
  nt::String
  synsOccurTrans::[String]
  prodDeclsTrans::[String]
{
  local header::String = "package " ++ grammarName ++ ";\n" ++
    "abstract class " ++ nt ++ "<T extends hasChild_" ++ nt ++ "<T>> " ++
      "extends TreeNode<T>";
  
  local transComplete::String = 
   header ++ " {\n" ++
     implode("\n\n", synsOccurTrans) ++
   "\n}\n\n" ++ 
   
   implode ("\n\n", prodDeclsTrans)
   
   ;

  return (nt ++ ".java", transComplete);
}