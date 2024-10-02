grammar custom:host:translation;

synthesized attribute preExprTranslation::[String];

attribute
  translationStr, preExprTranslation
occurs on Expr;

aspect production appendListsExpr
top::Expr ::= e1::Expr e2::Expr
{
  local combListInt::Integer = genInt();
  local combListName::String = "combList_" ++ toString(combListInt);

  top.translationStr = combListName;

  local listTyStr::String = 
    case e1.ty of
    | just(t) -> t.translationStr
    | nothing() -> "ERROR(Expr.appendListsExpr)"
    end;

  top.preExprTranslation = e1.preExprTranslation ++ e2.preExprTranslation ++ [
    "ArrayList<" ++ listTyStr ++ "> " ++ combListName ++
      " = new ArrayList<>()",
    combListName ++ ".addAll(" ++ e1.translationStr ++ ")",
    combListName ++ ".addAll(" ++ e2.translationStr ++ ")"
  ];
}

aspect production plusExpr
top::Expr ::= e1::Expr e2::Expr
{
  top.translationStr = e1.translationStr ++ " + " ++ e2.translationStr;
  top.preExprTranslation = [];
}

aspect production numExpr
top::Expr ::= i::Integer
{
  top.translationStr = toString(i);
  top.preExprTranslation = [];
}

aspect production boolExpr
top::Expr ::= b::Boolean
{
  top.translationStr = toString(b);
  top.preExprTranslation = [];
}

aspect production callExpr
top::Expr ::= id::String args::Exprs
{
  top.translationStr = 
    "new " ++ id ++ "(" ++ implode(", ", args.translationLst) ++ ")";
  top.preExprTranslation = args.preExprTranslation;
}

aspect production listExpr
top::Expr ::= elements::Exprs
{
  local combListInt::Integer = genInt();
  local combListName::String = "combList_" ++ toString(combListInt);

  local listTyStr::String = 
    case elements.ty of
    | just(t) -> t.translationStr
    | nothing() -> "ERROR(Expr.appendListsExpr)"
    end;

  top.translationStr = 
    case elements of
    | exprsCons(_, _) -> combListName
    | _ -> "new ArrayList<>()"
    end;

  top.preExprTranslation = 
    case elements of
    | exprsCons(_, _) ->
      elements.preExprTranslation ++ [
      "ArrayList<" ++ listTyStr ++ "> " ++ combListName ++ 
        " = new ArrayList<>()"] ++ 
      map ((\s::String -> combListName ++ ".add(" ++ s ++ ")"),
           elements.translationLst)
    | _ -> []
    end;
}

aspect production refExpr
top::Expr ::= r::Ref
{
  top.translationStr = r.translationStr;
  top.preExprTranslation = r.preExprTranslation;
}

aspect production ifExpr
top::Expr ::= c::Expr e1::Expr e2::Expr
{
  top.translationStr = 
    "(" ++ c.translationStr ++ ") ? " ++ 
      e1.translationStr ++ " : " ++ e2.translationStr;
  top.preExprTranslation = [];
}

aspect production gtExpr
top::Expr ::= e1::Expr e2::Expr
{
  top.translationStr = e1.translationStr ++ " > " ++ e2.translationStr;
  top.preExprTranslation = [];
}

aspect production ltExpr
top::Expr ::= e1::Expr e2::Expr
{
  top.translationStr = e1.translationStr ++ " < " ++ e2.translationStr;
  top.preExprTranslation = [];
}


attribute translationLst, preExprTranslation occurs on Exprs;

aspect production exprsCons
top::Exprs ::= e::Expr es::Exprs
{
  top.preExprTranslation = e.preExprTranslation ++ es.preExprTranslation;
  top.translationLst = e.translationStr :: es.translationLst;
}

aspect production exprsNil
top::Exprs ::=
{
  top.preExprTranslation = [];
  top.translationLst = [];
}



attribute translationStr, preExprTranslation occurs on Ref;

aspect production fieldAccessRef
top::Ref ::= e::Expr id::String
{
  top.translationStr = e.translationStr ++ "." ++ id ++ "()";
  top.preExprTranslation = e.preExprTranslation;
}

aspect production nameRef
top::Ref ::= id::String
{
  top.translationStr = id;
  top.preExprTranslation = [];
}