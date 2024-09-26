package lm;

import lm.*;
import java.util.ArrayList;

abstract class Dcls<T extends inh_scope> extends TreeNode  {

  protected T parent;

  protected Scope scope            = null;
  protected Boolean scope_computed = false;

  protected ArrayList<Scope> vars  = null;
  protected Boolean vars_computed  = false;

  protected ArrayList<Scope> mods  = null;
  protected Boolean mods_computed  = false;

  protected ArrayList<Scope> imps  = null;
  protected Boolean imps_computed  = false;

  /* SYNTHESIZED ATTRIBUTES */

  public ArrayList<Scope> vars() { return new ArrayList<Scope>(); }
  public ArrayList<Scope> mods() { return new ArrayList<Scope>(); }
  public ArrayList<Scope> imps() { return new ArrayList<Scope>(); }

}

class dclsCons<T extends inh_scope> extends Dcls<T> 
implements inh_scope {

  private Dcl<dclsCons> d;
  private Dcls<dclsCons> ds;

  public dclsCons(Dcl<dclsCons> d, Dcls<dclsCons> ds) {
    this.d = d;
    this.ds = ds;

    // setting parents
    this.d.setParent(this, 0);
    this.ds.setParent(this, 1);
  }

  public Scope scope() {
    if (this.scope_computed) {
      return this.scope;
    } else {
      this.scope = this.parent.scope(this.childId);
      this.scope_computed = true;
      return this.scope;
    }
  }

  /* INHERITED ATTRIBUTES */

  public Scope scope(int child) {
    if (child == 0) {
      return this.scope();
    } else if (child == 1) {
      return this.scope();
    }
    return null;
  }

  /* SYNTHESIZED ATTRIBUTES */

  public ArrayList<Scope> vars() {
    if (this.vars_computed) {
      return this.vars;
    }
    this.vars = new ArrayList<Scope>();
    this.vars_computed = true;
    return this.vars;
  }

  public ArrayList<Scope> mods() {
    if (this.mods_computed) {
      return this.mods;
    }
    this.mods = new ArrayList<Scope>();
    this.mods_computed = true;
    return this.mods;
  }

  public ArrayList<Scope> imps() {
    if (this.imps_computed) {
      return this.imps;
    }
    this.imps = new ArrayList<Scope>();
    this.imps_computed = true;
    return this.imps;
  }

}

class dclsNil<T extends inh_scope> extends Dcls<T> {

  public dclsNil() {

  }

  /* INHERITED ATTRIBUTES */

  public Scope scope(int child) {
    return null;
  }

  /* SYNTHESIZED ATTRIBUTES */

  public ArrayList<Scope> vars() {
    if (this.vars_computed) {
      return this.vars;
    }
    this.vars = new ArrayList<Scope>();
    this.vars_computed = true;
    return this.vars;
  }

  public ArrayList<Scope> mods() {
    if (this.mods_computed) {
      return this.mods;
    }
    this.mods = new ArrayList<Scope>();
    this.mods_computed = true;
    return this.mods;
  }

  public ArrayList<Scope> imps() {
    if (this.imps_computed) {
      return this.imps;
    }
    this.imps = new ArrayList<Scope>();
    this.imps_computed = true;
    return this.imps;
  }

}