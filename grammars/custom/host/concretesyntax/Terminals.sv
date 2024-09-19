grammar custom:host:concretesyntax;


lexer class KEYWORD dominates {Id_t};
lexer class TYPE dominates {Id_t};


{- Values -}

terminal Id_t      /[a-zA-Z][a-zA-Z0-9_]*/;
terminal Int_t     /-?[0-9]+/;
terminal True_t    'true'    lexer classes {KEYWORD};
terminal False_t   'false'   lexer classes {KEYWORD};
terminal String_t  /[\"]([^\r\n\"\\]|[\\][\"]|[\\][\\]|[\\]b|[\\]n|[\\]r|[\\]f|[\\]t)*[\"]/;

{- Keywords -}

terminal Nt_t     'nt'     lexer classes {KEYWORD};
terminal Syn_t    'syn'    lexer classes {KEYWORD};
terminal Inh_t    'inh'    lexer classes {KEYWORD};
terminal Attr_t   'attr'   lexer classes {KEYWORD};
terminal Occurs_t 'occurs' lexer classes {KEYWORD};
terminal On_t     'on'     lexer classes {KEYWORD};
terminal Prod_t   'prod'   lexer classes {KEYWORD};
terminal Fun_t    'fun'    lexer classes {KEYWORD};
terminal Local_t  'local'  lexer classes {KEYWORD};


{- Types -}

terminal IntegerTy_t 'Integer' lexer classes {TYPE};
terminal StringTy_t  'String'  lexer classes {TYPE};
terminal BooleanTy_t 'Boolean' lexer classes {TYPE};


{- Other -}

terminal ProdTy_t   '::=';
terminal Colon_t    ':';

terminal Semi_t     ';';
terminal Dot_t      '.';
terminal Comma_t    ',';

terminal LCurly_t   '{';
terminal RCurly_t   '}';
terminal LParen_t   '(';
terminal RParen_t   ')';
terminal LAngle_t   '<';
terminal RAngle_t   '>';
terminal LSq_t      '[';
terminal RSq_t      ']';

terminal Equals_t   '=';
terminal Plus_t     '+';

terminal ListCons_t   '::';
terminal ListAppend_t '++';


{- Ignore -}

ignore terminal Spacing_t       /[\ \t\r\n]+/;
ignore terminal BlockComments /\{\-(\{\-([^\-]|\-+[^\}\-])*\-+\}|[^\-]|\-+[^\}\-])*\-+\}/;
ignore terminal Comments /([\-][\-].*)/;