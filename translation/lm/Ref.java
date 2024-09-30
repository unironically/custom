package lm;

import java.util.ArrayList;

abstract class Ref<T extends haschild_Ref<T>> extends TreeNode<T> {

  protected ArrayList<Scope<? extends haschild_Scope<?>>> lex = null;
  protected Boolean lex_computed = false;
  public ArrayList<Scope<? extends haschild_Scope<?>>> lex() { return null; }

  protected ArrayList<Scope<? extends haschild_Scope<?>>> res = null;
  protected Boolean res_computed = false;
  public ArrayList<Scope<? extends haschild_Scope<?>>> res() { return null; }

  protected String str = null;
  protected Boolean str_computed = false;
  public String str() { return null; }

  protected ArrayList<Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>> 
    binds = null;
  protected Boolean binds_computed = false;
  public ArrayList<Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>> 
    binds() { return null; }  

  protected String pp = "";
  protected Boolean pp_computed  = false;
  public String pp() { return null; }

}

class mkVarRef<T extends haschild_Ref<T>> extends Ref<T> {

  protected String s;

  public mkVarRef(String s) {
    this.s = s;
    if(scopeTrace) System.out.println("- Created ref " + this.pp());
  }
  
  /* LOCALS */

  private DFA<mkVarRef<T>> dfa = null;
  private Boolean dfa_computed = false;
  protected DFA<mkVarRef<T>> dfa () {
    if (this.dfa_computed) return this.dfa;
    this.dfa = new varDFA<mkVarRef<T>>();
    this.dfa_computed = true;
    return this.dfa;
  }

  /* INHERITED ATTRIBUTES */

  /* GETTING OWN INHERITED ATTRIBUTES */

  public ArrayList<Scope<? extends haschild_Scope<?>>> lex() {
    if (this.lex_computed) return this.lex;
    this.lex = this.parent.lex(this.childId);
    this.lex_computed = true;
    return this.lex;
  }
  
  /* SYNTHESIZED ATTRIBUTES */

  public ArrayList<Scope<? extends haschild_Scope<?>>> res() {
    if (this.res_computed) {
      return this.res;
    }
    this.res = this.dfa().decls(this, this.lex().get(0));
    this.res_computed = true;
    return this.res;
  }

  public String str() {
    if (this.str_computed) return this.str;
    this.str = this.s;
    this.str_computed = true;
    return this.str;
  }

  public ArrayList<Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>> 
  binds() {
    
    if(this.binds_computed) return this.binds;
    
    ArrayList<Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>> binds =
      new ArrayList<Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>>();

    binds.add(new Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>
      (this, this.res()));

    this.binds = binds;
    this.binds_computed = true;
    return this.binds;

  }

  public String pp() {
    if (this.pp_computed) return this.pp;
    this.pp = "mkVarRef(\"" + this.s + "\")_" + 
      Integer.toString(this.hashCode());
    this.pp_computed = true;
    return this.pp;
  }

}

class mkModRef<T extends haschild_Ref<T>> extends Ref<T> {

  protected String s;

  public mkModRef(String s) {
    this.s = s;
    if(scopeTrace) System.out.println("- Created ref " + this.pp());
  }

  /* LOCALS */

  private DFA<mkModRef<T>> dfa = null;
  private Boolean dfa_computed = false;
  protected DFA<mkModRef<T>> dfa () {
    if (this.dfa_computed) return this.dfa;
    this.dfa = new modDFA<mkModRef<T>>();
    this.dfa_computed = true;
    return this.dfa;
  }

  /* INHERITED ATTRIBUTES */

  /* GETTING OWN INHERITED ATTRIBUTES */

  public ArrayList<Scope<? extends haschild_Scope<?>>> lex() {
    if (this.lex_computed) return this.lex;
    this.lex = this.parent.lex(this.childId);
    this.lex_computed = true;
    return this.lex;
  }
  
  /* SYNTHESIZED ATTRIBUTES */

  public ArrayList<Scope<? extends haschild_Scope<?>>> res() {
    
    if (this.res_computed) {
      return this.res;
    }
    this.res = this.dfa().decls(this, this.lex().get(0));
    this.res_computed = true;
    return this.res;
  }

  public String str() {
    if (this.str_computed) return this.str;
    this.str = this.s;
    this.str_computed = true;
    return this.str;
  }

  public ArrayList<Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>> 
  binds() {

    if(this.binds_computed) return this.binds;
    
    ArrayList<Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>> binds =
      new ArrayList<Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>>();

    binds.add(new Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>
      (this, this.res()));

    this.binds = binds;
    this.binds_computed = true;

    return this.binds;

  }

  public String pp() {
    if (this.pp_computed) return this.pp;
    this.pp = "mkModRef(\"" + this.s + "\")_" + 
      Integer.toString(this.hashCode());
    this.pp_computed = true;
    return this.pp;
  }

}