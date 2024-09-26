grammar custom:host:concretesyntax;

imports custom:host:abstractsyntax;

synthesized attribute ast<a>::a;
synthesized attribute exprs::[Expr];
synthesized attribute ids::[String];


{- File -}

closed nonterminal File_c with ast<File>, location;

concrete production file_c
top::File_c ::= ds::TopDecls_c
{ top.ast = file(ds.ast, location=top.location); }


{- Top declarations -}

closed nonterminal TopDecls_c with ast<TopDecls>, location;

concrete production topDeclsCons_c
top::TopDecls_c ::= d::TopDecl_c ds::TopDecls_c
{ top.ast = topDeclsCons(d.ast, ds.ast, location=top.location); }

concrete production topDeclsNil_c
top::TopDecls_c ::=
{ top.ast = topDeclsNil(location=top.location); }


{- Top declaration -}

closed nonterminal TopDecl_c with ast<TopDecl>, location;

concrete production nonterminalDecl_c
top::TopDecl_c ::= 'nt' id::Id_t ';'
{ top.ast = nonterminalDecl(id.lexeme, location=top.location); }

concrete production attributeDecl_c
top::TopDecl_c ::= at::AttrType_c 'attr' id::Id_t ':' ty::Type_c ';'
{ top.ast = attributeDecl(at.ast, id.lexeme, ty.ast, 
                          location=top.location); }

concrete production occursDecl_c
top::TopDecl_c ::= 'attr' attrId::Id_t 'occurs' 'on' nontId::Id_t ';'
{ top.ast = occursDecl(attrId.lexeme, nontId.lexeme, location=top.location); }

concrete production productionDecl_c
top::TopDecl_c ::= 'prod' id::Id_t ':' nont::Id_t '::=' cs::Children_c
                   '{' eq::Equations_c '}'
{ top.ast = productionDecl(id.lexeme, nont.lexeme, cs.ast, eq.ast, 
                           location=top.location);}

concrete production functionDecl_c
top::TopDecl_c ::= 'fun' id::Id_t ':' ty::Type_c '::=' cs::Children_c '='
                   e::Expr_c
{ top.ast = functionDecl(id.lexeme, ty.ast, cs.ast, e.ast, 
                         location=top.location); }


{- AttrType -}

nonterminal AttrType_c with ast<AttrType>, location;

concrete production synAttrType
top::AttrType_c ::= 'syn'
{ top.ast = attrTypeSyn(location=top.location); }

concrete production inhAttrType
top::AttrType_c ::= 'inh'
{ top.ast = attrTypeInh(location=top.location); }


{- Children -}

closed nonterminal Children_c with ast<Children>, location;

concrete production childrenCons_c
top::Children_c ::= c::Child_c ps::Children_c
{ top.ast = childrenCons(c.ast, ps.ast, location=top.location); }

concrete production childrenNil_c
top::Children_c ::=
{ top.ast = childrenNil(location=top.location); }


{- Child -}

closed nonterminal Child_c with ast<Child>, location;

concrete production child_c
top::Child_c ::= id::Id_t ':' ty::Type_c
{ top.ast = child(id.lexeme, ty.ast, location=top.location); }


{- Equations -}

closed nonterminal Equations_c with ast<Equations>, location;

concrete production equationsCons_c
top::Equations_c ::= e::Equation_c es::Equations_c
{ top.ast = equationsCons(e.ast, es.ast, location=top.location); }

concrete production equationsNil_c
top::Equations_c ::=
{ top.ast = equationsNil(location=top.location); }


{- Equation -}

closed nonterminal Equation_c with ast<Equation>, location;

concrete production localDeclEquation_c
top::Equation_c ::= 'local' id::Id_t ':' t::Type_c '=' e::Expr_c ';'
{ top.ast = localDeclEquation(id.lexeme, t.ast, e.ast, location=top.location); }

concrete production assignEquation_c
top::Equation_c ::= lhs::LHS_c '=' e::Expr_c ';'
{ top.ast = assignEquation(lhs.ast, e.ast, location=top.location); }


{- Exprs -}

closed nonterminal Exprs_c with ast<Exprs>, location;

concrete production exprsCons_c
top::Exprs_c ::= e::Expr_c ',' es::Exprs_c
{ top.ast = exprsCons(e.ast, es.ast, location=top.location); }

concrete production exprOne_C
top::Exprs_c ::= e::Expr_c
{ top.ast = exprsCons(e.ast, exprsNil(location=top.location), 
                      location=top.location); }


{- Expr -}

closed nonterminal Expr_c with ast<Expr>, location;

concrete production appendLists_c
top::Expr_c ::= e1::AddExpr_c '++' e2::Expr_c
{ top.ast = appendListsExpr(e1.ast, e2.ast, location=top.location); }

concrete production expr_c
top::Expr_c ::= e::AddExpr_c
{ top.ast = e.ast; }

--

closed nonterminal AddExpr_c with ast<Expr>, location;

concrete production plus_c
top::AddExpr_c ::= e1::AddExpr_c '+' e2::FactorExpr_c
{ top.ast = plusExpr(e1.ast, e2.ast, location=top.location); }

concrete production factor_c
top::AddExpr_c ::= e::FactorExpr_c
{ top.ast = e.ast; }

--

closed nonterminal FactorExpr_c with ast<Expr>, location;

concrete production num_c
top::FactorExpr_c ::= i::Int_t
{ top.ast = numExpr(toInteger(i.lexeme), location=top.location); }

concrete production true_c
top::FactorExpr_c ::= 'true'
{ top.ast = boolExpr(true, location=top.location); }

concrete production false_c
top::FactorExpr_c ::= 'false'
{ top.ast = boolExpr(false, location=top.location); }

concrete production ref_c
top::FactorExpr_c ::= r::Ref_c
{ top.ast = refExpr(r.ast, location=top.location); }

concrete production prodArgs_c
top::FactorExpr_c ::= id::Id_t '(' es::Exprs_c ')'
{ top.ast = callExpr(id.lexeme, es.ast, location=top.location); }

concrete production prodNoArgs_c
top::FactorExpr_c ::= id::Id_t '(' ')'
{ top.ast = callExpr(id.lexeme, exprsNil(location=top.location), 
                     location=top.location); }

concrete production parensExpr_c
top::FactorExpr_c ::= '(' e::Expr_c ')'
{ top.ast = e.ast; }

concrete production listExpr_c
top::FactorExpr_c ::= '[' es::Exprs_c ']'
{ top.ast = listExpr(es.ast, location=top.location); }

concrete production listExprNil_c
top::FactorExpr_c ::= '[' ']'
{ top.ast = listExpr(exprsNil(location=top.location), location=top.location); }


{- Ref -}

nonterminal Ref_c with ast<Ref>, location;

concrete production fieldAccessRef_c
top::Ref_c ::= f::Ref_c '.' id::Id_t
{ top.ast = fieldAccessRef(f.ast, id.lexeme, location=top.location); }

concrete production nameRef_c
top::Ref_c ::= id::Id_t
{ top.ast = nameRef(id.lexeme, location=top.location); }

{- LHS -}

closed nonterminal LHS_c with ast<LHS>, location;

concrete production refLHS_c
top::LHS_c ::= id::Id_t
{ top.ast = refLHS(id.lexeme, location=top.location); }

concrete production fieldAccessLHS_c
top::LHS_c ::= lhs::LHS_c '.' id::Id_t
{ top.ast = fieldAccessLHS(lhs.ast, id.lexeme, location=top.location); }


{- Types -}

closed nonterminal Type_c with ast<Type>, location;

concrete production intType_c
top::Type_c ::= 'Integer'
{ top.ast = intType(location=top.location); }

concrete production boolType_c
top::Type_c ::= 'Boolean'
{ top.ast = boolType(location=top.location); }

concrete production stringType_c
top::Type_c ::= 'String'
{ top.ast = stringType(location=top.location); }

concrete production listType_c
top::Type_c ::= '[' ty::Type_c ']'
{ top.ast = listType(ty.ast, location=top.location); }

concrete production nonterminalType_c
top::Type_c ::= id::Id_t
{ top.ast = nonterminalType(id.lexeme, location=top.location); }