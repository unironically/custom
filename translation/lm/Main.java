package lm;
import lm.*;

import java.util.ArrayList;

// nt Main
abstract class Main extends TreeNode {

  protected String pp = "";
  protected Boolean pp_computed  = false;
  public String pp() { return null; }

  protected ArrayList<Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>> 
    binds = null;
  protected Boolean binds_computed = false;
  public ArrayList<Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>> 
    binds() { return null; }

}


// prod main: Main ::= ds:Dcls name:String
class main extends Main
  implements haschild_Dcls<main>, haschild_Scope<main> {

  protected Dcls<main> ds;
  
  protected Scope<main> s = null;
  protected Boolean s_computed = false;

  public main(Dcls<main> ds) {
    this.ds = ds;

    // setting parents
    ds.setParent(this, 0);
  }

  public Scope<main> s() {
    if (s_computed) {
      return this.s;
    } else {
      this.s = new mkScope<main>();
      this.s.setParent(this, 1);
      this.s_computed = true;
      return this.s;
    }
  }

  /* INHERITED ATTRIBUTES */

  public Scope<main> scope(int child) {
    if (child == 0) {
      return this.s();
    }
    return null;
  }

  public ArrayList<Scope<? extends haschild_Scope<?>>> lex(int child) {
    if (child == 1) {
      return new ArrayList<Scope<? extends haschild_Scope<?>>>();
    }
    return new ArrayList<Scope<? extends haschild_Scope<?>>>();
  }

  public ArrayList<Scope<? extends haschild_Scope<?>>> var(int child) {
    if (child == 1) {
      return this.ds.vars();
    }
    return new ArrayList<Scope<? extends haschild_Scope<?>>>();
  }

  public ArrayList<Scope<? extends haschild_Scope<?>>> mod(int child) {
    if (child == 1) {
      return this.ds.mods();
    }
    return new ArrayList<Scope<? extends haschild_Scope<?>>>();
  }

  public ArrayList<Scope<? extends haschild_Scope<?>>> imp(int child) {
    if (child == 1) {
      return this.ds.imps();
    }
    return new ArrayList<Scope<? extends haschild_Scope<?>>>();
  }

  /* SYNTHESIZED ATTRIBUTES */

  public String pp() {
    if (this.pp_computed) return this.pp;
    this.pp = "main(" + this.ds.pp() + ")";
    this.pp_computed = true;
    return this.pp;
  }

  public ArrayList<Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>> 
  binds() {
    if(this.binds_computed) return this.binds;
    this.binds = this.ds.binds();
    this.binds_computed = true;
    return this.binds;
  }

}