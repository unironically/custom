package lm;

import lm.*;
import java.util.ArrayList;

// nt Main
abstract class Main extends TreeNode {}


// prod main: Main ::= ds:Dcls name:String
class main extends Main
  implements inh_scope, inh_lex, inh_var, inh_mod, inh_imp {

  protected Dcls<main> ds;
  
  protected Scope s = null;
  protected Boolean s_computed = false;

  public main(Dcls<main> ds) {
    this.ds = ds;

    // setting parents
    ds.setParent(this, 0);
  }

  public Scope s() {
    if (s_computed) {
      return this.s;
    } else {
      this.s = new mkScope();
      this.s.setParent(this, 1);
      this.s_computed = true;
      return this.s;
    }
  }

  /* INHERITED ATTRIBUTES */

  public Scope scope(int child) {
    if (child == 0) {
      return this.s();
    }
    return null;
  }

  public ArrayList<Scope> lex(int child) {
    if (child == 1) {
      return new ArrayList<Scope>();
    }
    return new ArrayList<Scope>();
  }

  public ArrayList<Scope> var(int child) {
    if (child == 1) {
      return this.ds.vars();
    }
    return new ArrayList<Scope>();
  }

  public ArrayList<Scope> mod(int child) {
    if (child == 1) {
      return this.ds.mods();
    }
    return new ArrayList<Scope>();
  }

  public ArrayList<Scope> imp(int child) {
    if (child == 1) {
      return this.ds.imps();
    }
    return new ArrayList<Scope>();
  }

}