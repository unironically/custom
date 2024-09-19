grammar custom:composed;

-- host language
exports custom:host:concretesyntax;

-- extensions
exports custom:attributewith:concretesyntax;

------------------------------------------------------

parser parse :: File_c { custom:composed; }

function main
IO<Integer> ::= largs::[String]
{
  return
    if !null(largs)
      then do {
        let filePath :: String = head(largs);
        file :: String <- readFile(head(largs));

        let fileName::String = head(explode(".", last(explode("/", filePath))));

        let result :: ParseResult<File_c> = 
          custom:composed:parse(file, filePath);
        --let ast :: Main = result.parseTree.ast;

        --let fileNameExt::String = last(explode("/", filePath));
        --let fileNameExplode::[String] = explode(".", fileNameExt);
        --let fileName::String = head(fileNameExplode);

        --let viz::String = graphvizScopes(ast.allScopes, ast.allRefs);

        if result.parseSuccess
          then do {
            print("[✔] Parse success\n");
            return 0;
          }
          else do {
            print("[✗] Parse failure\n" ++ result.parseErrors ++ "\n");
            return -1;
          };
      }
      else do {
        print("[✗] No input file given\n");
            return -1;
      };
}