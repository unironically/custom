grammar custom:composed;

-- host language
exports custom:host:concretesyntax;
exports custom:host:abstractsyntax;
exports custom:host:translation;
exports custom:host:typing;

-- extensions
--exports custom:attributewith:concretesyntax;
--exports custom:sg_lib;

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
        let ast :: File = result.parseTree.ast;
        let astDec::Decorated File with {grammarName} = 
          decorate ast with {grammarName = fileName;};

        --let fileNameExt::String = last(explode("/", filePath));
        --let fileNameExplode::[String] = explode(".", fileNameExt);
        --let fileName::String = head(fileNameExplode);

        --let viz::String = graphvizScopes(ast.allScopes, []);

        if result.parseSuccess
          then do {
            --if null(ast.errs) then do {
              print("[✔] Success\n");
              --mkdir("out");
              --system("echo '" ++ viz ++ "' | dot -Tsvg > out/" ++ 
              --      fileName ++ ".svg");
              mkdir("generated");
              mkdir("generated/" ++ fileName);
              ret::Integer <- writeFiles(astDec.filesToWrite, 
                                         "generated/" ++ fileName);
              return ret;
            } 
            --else do {
            --  print("[✗] Errors found!\n");
            --  print(strErrs(ast.errs, filePath));
            --  mkdir("out");
            --  system("echo '" ++ viz ++ "' | dot -Tsvg > out/" ++ 
            --        fileName ++ ".svg");
            --  return -1;
            --}
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

fun strErrs String ::= errs::[ErrMessage] file::String =
  concat (
    map (
      (\e::ErrMessage ->
        case e of
        | errMessage(s, l) -> 
            file ++ ":" ++ toString(l.line) ++ ":" ++ toString(l.column) ++ 
            ": error: " ++ s ++ "\n"
        end),
      errs
    )
  );


fun writeFiles IO<Integer> ::= filesToWrite::[(String, String)] dir::String =
  case filesToWrite of
  | [] -> do { return 0; }
  | (fName, fContent)::t -> 
      do {
        writeFile(dir ++ "/" ++ fName, fContent);
        writeFiles(t, dir);
      }
  end;