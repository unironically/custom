package lm;
import lm.*;

import java.util.ArrayList;

abstract class Dcls<T extends haschild_Dcls<T>> extends TreeNode<T>  {

  protected Scope<? extends haschild_Scope<?>> scope = null;
  protected Boolean scope_computed = false;

  protected ArrayList<Scope<? extends haschild_Scope<?>>> vars  = null;
  protected Boolean vars_computed  = false;

  protected ArrayList<Scope<? extends haschild_Scope<?>>> mods  = null;
  protected Boolean mods_computed  = false;

  protected ArrayList<Scope<? extends haschild_Scope<?>>> imps  = null;
  protected Boolean imps_computed  = false;

  protected String pp = "";
  protected Boolean pp_computed  = false;

  public ArrayList<Scope<? extends haschild_Scope<?>>> vars() { return new ArrayList<Scope<? extends haschild_Scope<?>>>(); }
  public ArrayList<Scope<? extends haschild_Scope<?>>> mods() { return new ArrayList<Scope<? extends haschild_Scope<?>>>(); }
  public ArrayList<Scope<? extends haschild_Scope<?>>> imps() { return new ArrayList<Scope<? extends haschild_Scope<?>>>(); }
  public String pp() { return null; }

  protected ArrayList<Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>> 
    binds = null;
  protected Boolean binds_computed = false;
  public ArrayList<Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>> 
    binds() { return null; }

}

class dclsCons<T extends haschild_Dcls<T>> extends Dcls<T> 
implements haschild_Dcl<dclsCons<T>>, haschild_Dcls<dclsCons<T>>,
           haschild_Scope<dclsCons<T>> {

  /* Children:
   * 0: d:Dcl
   * 1: ds:Dcls
   * 2: local s:Scope
   */

  private Dcl<dclsCons<T>> d;
  private Dcls<dclsCons<T>> ds;

  public dclsCons(Dcl<dclsCons<T>> d, Dcls<dclsCons<T>> ds) {
    this.d = d;
    this.ds = ds;

    // setting parents
    this.d.setParent(this, 0);
    this.ds.setParent(this, 1);
  }

  /* LOCALS */
  private Scope<dclsCons<T>> s = null;
  private Boolean s_computed = false;
  public Scope<dclsCons<T>> s() {
    if (this.s_computed) return this.s;
    this.s = new mkScope<dclsCons<T>>();
    this.s.setParent(this, 2);
    this.s_computed = true;
    return this.s;
  }

  /* GIVING CHILDREN INHERITED ATTRIBUTES */

  public Scope<? extends haschild_Scope<?>> scope(int child) {
    if (child == 0) {
      return this.scope();
    } else if (child == 1) {
      return this.s();          // LM 2
      //return this.scope();    // LM 4 (circular)
    }
    return null;
  }

  public ArrayList<Scope<? extends haschild_Scope<?>>> lex(int child) {
    if (child == 2) {
      ArrayList<Scope<? extends haschild_Scope<?>>> par = new ArrayList<>();
      par.add(this.scope());
      return par;
    }
    return new ArrayList<Scope<? extends haschild_Scope<?>>>();
  }

  public ArrayList<Scope<? extends haschild_Scope<?>>> var(int child) {
    if (child == 2) {
      return new ArrayList<>();
    }
    return new ArrayList<Scope<? extends haschild_Scope<?>>>();
  }

  public ArrayList<Scope<? extends haschild_Scope<?>>> mod(int child) {
    if (child == 2) {
      return new ArrayList<>();
    }
    return new ArrayList<Scope<? extends haschild_Scope<?>>>();
  }

  public ArrayList<Scope<? extends haschild_Scope<?>>> imp(int child) {
    if (child == 2) {
      return this.d.imps();
    }
    return new ArrayList<Scope<? extends haschild_Scope<?>>>();
  }


  /* GETTING OWN INHERITED ATTRIBUTES */

  public Scope<? extends haschild_Scope<?>> scope() {
    if (this.scope_computed) {
      return this.scope;
    } else {
      this.scope = this.parent.scope(this.childId);
      this.scope_computed = true;
      return this.scope;
    }
  }

  /* SYNTHESIZED ATTRIBUTES */

  public ArrayList<Scope<? extends haschild_Scope<?>>> vars() {
    if (this.vars_computed) {
      return this.vars;
    }
    ArrayList<Scope<? extends haschild_Scope<?>>> combined =
      new ArrayList<Scope<? extends haschild_Scope<?>>>();
    combined.addAll(this.d.vars());
    combined.addAll(this.ds.vars());
    this.vars = combined;
    this.vars_computed = true;
    return this.vars;
  }

  public ArrayList<Scope<? extends haschild_Scope<?>>> mods() {
    if (this.mods_computed) {
      return this.mods;
    }
    ArrayList<Scope<? extends haschild_Scope<?>>> combined =
      new ArrayList<Scope<? extends haschild_Scope<?>>>();
    combined.addAll(this.d.mods());
    combined.addAll(this.ds.mods());
    this.mods = combined;
    this.mods_computed = true;
    return this.mods;
  }

  public ArrayList<Scope<? extends haschild_Scope<?>>> imps() {
    if (this.imps_computed) {
      return this.imps;
    }
    ArrayList<Scope<? extends haschild_Scope<?>>> combined =
      new ArrayList<Scope<? extends haschild_Scope<?>>>();
    this.imps = combined;
    this.imps_computed = true;
    return this.imps;
  }

  public String pp() {
    if (this.pp_computed) return this.pp;
    this.pp = "dclsCons(" + this.d.pp() + ", " + this.ds.pp() + ")";
    this.pp_computed = true;
    return this.pp;
  }

  public ArrayList<Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>> 
  binds() {
    if(this.binds_computed) return this.binds;
    ArrayList<Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>> binds =
      this.d.binds();
    binds.addAll(this.ds.binds());
    this.binds = binds;
    this.binds_computed = true;
    return this.binds;
  }

}

class dclsNil<T extends haschild_Dcls<T>> extends Dcls<T> {

  public dclsNil() {

  }

  /* INHERITED ATTRIBUTES */

  public Scope<? extends haschild_Scope<?>> scope(int child) {
    return null;
  }

  /* SYNTHESIZED ATTRIBUTES */

  public ArrayList<Scope<? extends haschild_Scope<?>>> vars() {
    if (this.vars_computed) {
      return this.vars;
    }
    this.vars = new ArrayList<>();
    this.vars_computed = true;
    return this.vars;
  }

  public ArrayList<Scope<? extends haschild_Scope<?>>> mods() {
    if (this.mods_computed) {
      return this.mods;
    }
    this.mods = new ArrayList<>();
    this.mods_computed = true;
    return this.mods;
  }

  public ArrayList<Scope<? extends haschild_Scope<?>>> imps() {
    if (this.imps_computed) {
      return this.imps;
    }
    this.imps = new ArrayList<>();
    this.imps_computed = true;
    return this.imps;
  }

  public String pp() {
    if (this.pp_computed) return this.pp;
    this.pp = "dclsNil()";
    this.pp_computed = true;
    return this.pp;
  }

  public ArrayList<Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>> 
  binds() {
    if(this.binds_computed) return this.binds;
    this.binds = new ArrayList<>();
    this.binds_computed = true;
    return this.binds;
  }

}