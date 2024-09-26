package lm;

import java.util.ArrayList;

abstract class Ref<T extends inh_lex> extends TreeNode {

  protected ArrayList<Scope> lex = null;
  protected Boolean lex_computed = false;

  protected ArrayList<Scope> res = null;
  protected Boolean res_computed = false;
  public ArrayList<Scope> res() { return null; }

  protected Type type = null;
  protected Boolean type_computed = false;
  public Type type() { return null; }

}

class mkVarRef<T extends inh_lex> extends Ref<T> {

  protected String s;

  public mkVarRef(String s) {
    this.s = s;
  }
  
  /* LOCALS */

  /* INHERITED ATTRIBUTES */
  
  /* SYNTHESIZED ATTRIBUTES */

  // this.res = []
  public ArrayList<Scope> res() {
    if (this.res_computed) {
      return this.res;
    }
    this.res = new ArrayList<Scope>();
    this.res_computed = true;
    return this.res;
  }

}

class mkModRef<T extends inh_lex> extends Ref<T> {

  protected String s;

  public mkModRef(String s) {
    this.s = s;
  }

  /* LOCALS */

  /* INHERITED ATTRIBUTES */
  
  /* SYNTHESIZED ATTRIBUTES */

  // this.res = []
  public ArrayList<Scope> res() {
    if (this.res_computed) {
      return this.res;
    }
    this.res = new ArrayList<Scope>();
    this.res_computed = true;
    return this.res;
  }

}