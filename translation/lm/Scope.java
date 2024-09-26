package lm;

import java.util.ArrayList;

abstract class Scope<T extends inh_lex, inh_var, inh_mod, inh_imp> 
  extends TreeNode {
  
  private ArrayList<Scope> lex = null;
  private Boolean lex_computed = false;

  private ArrayList<Scope> var = null;
  private Boolean var_computed = false;

  private ArrayList<Scope> mod = null;
  private Boolean mod_computed = false;

  private ArrayList<Scope> imp = null;
  private Boolean imp_computed = false;

}

class mkScope extends Scope {

  public mkScope() {

  }

}

class mkMod extends Scope {

  private String s;

  public mkMod(String s) {
    this.s = s;
  }

}

class mkVar extends Scope {

  private String s;

  public mkVar(String s) {
    this.s = s;
  }

}