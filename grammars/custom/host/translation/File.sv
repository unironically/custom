grammar custom:host:translation;

imports custom:host:abstractsyntax;

attribute
  filesToWrite
occurs on File;

aspect production file
top::File ::= ds::TopDecls
{
  top.filesToWrite =
    map (
      (
        \nt::String ->

          -- occurs
          let synsOccurTrans::[(String, String)] =
            filter((\p::(String, String) -> p.1 == nt), ds.synsOccurTransPairs)
          in
          let occursTrans::[String] = 
            map((\p::(String, String) -> p.2), synsOccurTrans)
          in

          -- prods
          let prodsForNt::[(String, String)] = 
            filter((\p::(String, String) -> p.1 == nt), ds.prodDeclsTrans)
          in
          let prodsTrans::[String] = 
            map((\p::(String, String) -> p.2), prodsForNt)
          in
            stitchFile(
              nt, 
              occursTrans, 
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
  nt::String
  synsOccurTrans::[String]
  prodDeclsTrans::[String]
{
  local header::String =
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