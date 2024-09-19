grammar custom:host:concretesyntax;
--imports custom:abstractsyntax;


{- File -}

closed nonterminal File_c;

concrete production file_c
top::File_c ::= ds::TopDecls_c
{}


{- Top declarations -}

closed nonterminal TopDecls_c;

concrete production topDeclsCons_c
top::TopDecls_c ::= d::TopDecl_c ds::TopDecls_c
{}

concrete production topDeclsNil_c
top::TopDecls_c ::=
{}


{- Top declaration -}

closed nonterminal TopDecl_c;

concrete production nonterminalDecl_c
top::TopDecl_c ::= 'nt' id::Id_t ';'
{}

concrete production synAttributeDecl_c
top::TopDecl_c ::= 'syn' 'attr' id::Id_t ':' ty::Type_c ';'
{}

concrete production inhAttributeDecl_c
top::TopDecl_c ::= 'inh' 'attr' id::Id_t ':' ty::Type_c ';'
{}

concrete production occursDecl_c
top::TopDecl_c ::= 'attr' idl::IdList_c 'occurs' 'on' ty::Type_c ';'
{}

concrete production productionDecl_c
top::TopDecl_c ::= 'prod' id::Id_t ':' ty::Type_c '::=' ps::Params_c
                   '{' eq::Equations_c '}'
{}


{- IdList -}

nonterminal IdList_c;

concrete production idListCons_c
top::IdList_c ::= id::Id_t ',' idl::IdList_c
{}

concrete production idListOne_c
top::IdList_c ::= id::Id_t
{}


{- Params -}

closed nonterminal Params_c;

concrete production paramsCons_c
top::Params_c ::= p::Param_c ps::Params_c
{}

concrete production paramsNil_c
top::Params_c ::=
{}


{- Param -}

closed nonterminal Param_c;

concrete production param_c
top::Param_c ::= id::Id_t ':' ty::Type_c
{}


{- Equations -}

closed nonterminal Equations_c;

concrete production equationsCons_c
top::Equations_c ::= p::Equation_c ps::Equations_c
{}

concrete production equationsNil_c
top::Equations_c ::=
{}


{- Equation -}

closed nonterminal Equation_c;

concrete production localEquation_c
top::Equation_c ::= 'local' id::Id_t ':' ty::Type_c '=' e::Expr_c ';'
{}

concrete production assignEquation_c
top::Equation_c ::= lhs::LHS_c '=' e::Expr_c ';'
{}


{- Exprs -}

closed nonterminal Exprs_c;

concrete production exprsCons_c
top::Exprs_c ::= e::Expr_c ',' es::Exprs_c
{}

concrete production exprOne_C
top::Exprs_c ::= e::Expr_c
{}


{- Expr -}

closed nonterminal Expr_c;

concrete production appendLists_c
top::Expr_c ::= e1::AddExpr_c '++' e2::Expr_c
{}

concrete production expr_c
top::Expr_c ::= e::AddExpr_c
{}

--

closed nonterminal AddExpr_c;

concrete production plus_c
top::AddExpr_c ::= e1::AddExpr_c '+' e2::FactorExpr_c
{}

concrete production factor_c
top::AddExpr_c ::= e::FactorExpr_c
{}

--

closed nonterminal FactorExpr_c;

concrete production num_c
top::FactorExpr_c ::= i::Int_t
{}

concrete production true_c
top::FactorExpr_c ::= 'true'
{}

concrete production false_c
top::FactorExpr_c ::= 'false'
{}

concrete production ref_c
top::FactorExpr_c ::= id::Id_t
{}

concrete production qualifiedRef_c
top::FactorExpr_c ::= f::FactorExpr_c '.' id::Id_t
{}

concrete production prodArgs_c
top::FactorExpr_c ::= id::Id_t '(' es::Exprs_c ')'
{}

concrete production prodNoArgs_c
top::FactorExpr_c ::= id::Id_t '(' ')'
{}

concrete production parensExpr_c
top::FactorExpr_c ::= '(' e::Expr_c ')'
{}

concrete production listExpr_c
top::FactorExpr_c ::= '[' e::Expr_c ']'
{}

{- LHS -}

closed nonterminal LHS_c;

concrete production nameLHS_c
top::LHS_c ::= id::Id_t
{}

concrete production qualifiedLHS_c
top::LHS_c ::= lhs::LHS_c '.' id::Id_t
{}


{- Types -}

closed nonterminal Type_c;

concrete production intType_c
top::Type_c ::= 'Integer'
{}

concrete production stringType_c
top::Type_c ::= 'String'
{}

concrete production boolType_c
top::Type_c ::= 'Boolean'
{}

concrete production listType_c
top::Type_c ::= '[' ty::Type_c ']'
{}


concrete production nonterminalType_c
top::Type_c ::= id::Id_t
{}