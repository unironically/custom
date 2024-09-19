grammar custom:attributewith:concretesyntax;

imports custom:host:concretesyntax;

terminal With    'with'    lexer classes {KEYWORD};

concrete production occursWithDecl_c
top::TopDecls_c ::= 'nt' id::Id_t 'with' ids::IdList_c ';'
                   ds::TopDecls_c
{
  forwards to
    topDeclsCons_c (
      nonterminalDecl_c (
        'nt', id, ';',
        location=top.location
      ),
      topDeclsCons_c (
        occursDecl_c (
          'attr', ids, 'occurs', 'on', 
          nonterminalType_c(id, location=top.location), ';',
          location=top.location
        ),
        ds,
        location=top.location
      ),
      location=top.location
    );
}