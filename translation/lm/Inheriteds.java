package lm;

import java.util.ArrayList;

interface inh_scope { public Scope scope(int child); }

interface inh_lex { public ArrayList<Scope> lex(int child); }
interface inh_var { public ArrayList<Scope> var(int child); }
interface inh_mod { public ArrayList<Scope> mod(int child); }
interface inh_imp { public ArrayList<Scope> imp(int child); }

interface haschild_Scope<T extends haschild_Scope<T>> {
  public ArrayList<Scope<? extends haschild_Scope<?>>> var(int child);
  public ArrayList<Scope<? extends haschild_Scope<?>>> mod(int child);
  public ArrayList<Scope<? extends haschild_Scope<?>>> imp(int child);
  public ArrayList<Scope<? extends haschild_Scope<?>>> impTentative(int child);
  public ArrayList<Scope<? extends haschild_Scope<?>>> lex(int child);
}

interface haschild_Ref<T extends haschild_Ref<T>> {
  public ArrayList<Scope<? extends haschild_Scope<?>>> lex(int child);
}

interface haschild_DFAState<T extends haschild_DFAState<T>> {
  public DFAState<T> lexT(int child);
  public DFAState<T> varT(int child);
  public DFAState<T> modT(int child);
  public DFAState<T> impT(int child);
}

interface haschild_Dcls<T extends haschild_Dcls<T>> {
  public Scope<? extends haschild_Scope<?>> scope(int child);
}

interface haschild_Dcl<T extends haschild_Dcl<T>> {
  public Scope<? extends haschild_Scope<?>> scope(int child);
}

interface haschild_ModRef<T extends haschild_ModRef<T>> {
  public Scope<? extends haschild_Scope<?>> scope(int child);
}

interface haschild_VarRef<T extends haschild_VarRef<T>> {
  public Scope<? extends haschild_Scope<?>> scope(int child);
}

interface haschild_Bind<T extends haschild_Bind<T>> {
  public Scope<? extends haschild_Scope<?>> scope(int child);
}

interface haschild_Exp<T extends haschild_Exp<T>> {
  public Scope<? extends haschild_Scope<?>> scope(int child);
}