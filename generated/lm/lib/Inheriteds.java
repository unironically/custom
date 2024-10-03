package lm;

import java.util.ArrayList;

interface hasChild_Scope<T extends hasChild_Scope<T>> {
  public ArrayList<Scope<? extends hasChild_Scope<?>>> var(int child);

  public ArrayList<Scope<? extends hasChild_Scope<?>>> mod(int child);

  public ArrayList<Scope<? extends hasChild_Scope<?>>> imp(int child);

  public ArrayList<Scope<? extends hasChild_Scope<?>>> lex(int child);
}

interface hasChild_Ref<T extends hasChild_Ref<T>> {
  public ArrayList<Scope<? extends hasChild_Scope<?>>> lex(int child);
}

interface hasChild_DFAState<T extends hasChild_DFAState<T>> {
  public DFAState<T> lexT(int child);

  public DFAState<T> varT(int child);

  public DFAState<T> modT(int child);

  public DFAState<T> impT(int child);
}

interface hasChild_Main<T extends hasChild_Main<T>> {

}

interface hasChild_Type<T extends hasChild_Type<T>> {

}

interface hasChild_Dcls<T extends hasChild_Dcls<T>> {
  public Scope<? extends hasChild_Scope<?>> scope(int child);
}

interface hasChild_Dcl<T extends hasChild_Dcl<T>> {
  public Scope<? extends hasChild_Scope<?>> scope(int child);
}

interface hasChild_ModRef<T extends hasChild_ModRef<T>> {
  public Scope<? extends hasChild_Scope<?>> scope(int child);
}

interface hasChild_VarRef<T extends hasChild_VarRef<T>> {
  public Scope<? extends hasChild_Scope<?>> scope(int child);
}

interface hasChild_Bind<T extends hasChild_Bind<T>> {
  public Scope<? extends hasChild_Scope<?>> scope(int child);
}

interface hasChild_Exp<T extends hasChild_Exp<T>> {
  public Scope<? extends hasChild_Scope<?>> scope(int child);
}