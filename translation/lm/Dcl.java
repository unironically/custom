package lm;

import lm.*;
import java.util.ArrayList;
import java.util.List;

abstract class Dcl<T extends inh_scope> extends TreeNode {

  protected T parent;

  protected Scope scope            = null;
  protected Boolean scope_computed = false;

  protected ArrayList<Scope> vars  = null;
  protected Boolean vars_computed  = false;

  protected ArrayList<Scope> mods  = null;
  protected Boolean mods_computed  = false;

  protected ArrayList<Scope> imps  = null;
  protected Boolean imps_computed  = false;
 
}

class dclMod<T extends inh_scope> extends Dcl<T>
implements inh_scope, inh_lex, inh_var, inh_mod, inh_imp {

  /* Children:
   * 0: ds:Dcls
   * 1: local s:Scope
   */

  protected String id;
  protected Dcls<dclMod> ds;

  protected Scope s = null;
  protected Boolean s_computed = false;

  public dclMod(String id, Dcls<dclMod> ds) {
    this.id = id;
    this.ds = ds;
  }

  /* LOCALS */

  // local s:Scope = mkMod(id)
  public Scope s() {
    if (this.s_computed) {
      return this.s;
    }
    this.s = new mkMod(this.id);
    this.s_computed = true;
    return this.s;
  }

  /* INHERITED ATTRIBUTES */

  // ds.scope = s
  public Scope scope(int childId) {
    if (childId == 0) {
      return this.s();
    }
    return null;
  }

  // s.lex = [this.scope]
  public ArrayList<Scope> lex(int childId) {
    if (childId == 1) {
      return new ArrayList<Scope>(List.of( this.parent.scope(this.childId) ));
    }
    return new ArrayList<Scope>();
  }

  // s.var = ds.vars
  public ArrayList<Scope> var(int childId) {
    if (childId == 1) {
      return this.ds.vars();
    }
    return new ArrayList<Scope>();
  }

  // s.mod = ds.mods
  public ArrayList<Scope> mod(int childId) {
    if (childId == 1) {
      return this.ds.mods();
    }
    return new ArrayList<Scope>();
  }

  // s.imp = ds.imps
  public ArrayList<Scope> imp(int childId) {
    if (childId == 1) {
      return this.ds.imps();
    }
    return new ArrayList<Scope>();
  }

  /* SYNTHESIZED ATTRIBUTES */

  // this.vars = []
  public ArrayList<Scope> vars() {
    if (this.vars_computed) {
      return this.vars;
    }
    this.vars = new ArrayList<Scope>();
    this.vars_computed = true;
    return this.vars;
  }

  // this.mods = [s]
  public ArrayList<Scope> mods() {
    if (this.mods_computed) {
      return this.mods;
    }
    this.mods = new ArrayList<Scope>(List.of( this.s() ));
    this.mods_computed = true;
    return this.mods;
  }

  // this.imps = []
  public ArrayList<Scope> imps() {
    if (this.imps_computed) {
      return this.imps;
    }
    this.imps = new ArrayList<Scope>();
    this.imps_computed = true;
    return this.imps;
  }

}


class dclImp<T extends inh_scope> extends Dcl<T>
implements inh_scope {

  /* Children:
   * 0: r:ModRef
   */

  protected ModRef<dclImp> r;

  public dclImp(ModRef<dclImp> r) {
    this.r = r;
  }

  /* INHERITED ATTRIBUTES */

  // r.scope = this.scope
  public Scope scope(int childId) {
    if (childId == 0) {
      return this.parent.scope(this.childId);
    }
    return null;
  }

  /* SYNTHESIZED ATTRIBUTES */

  // this.vars = []
  public ArrayList<Scope> vars() {
    if (this.vars_computed) {
      return this.vars;
    }
    this.vars = new ArrayList<Scope>();
    this.vars_computed = true;
    return this.vars;
  }

  // this.mods = []
  public ArrayList<Scope> mods() {
    if (this.mods_computed) {
      return this.mods;
    }
    this.mods = new ArrayList<Scope>();
    this.mods_computed = true;
    return this.mods;
  }

  // this.imps = r.imp
  public ArrayList<Scope> imps() {
    if (this.imps_computed) {
      return this.imps;
    }
    this.imps = this.r.imps();
    this.imps_computed = true;
    return this.imps;
  }

}


class dclBind<T extends inh_scope> extends Dcl<T>
implements inh_scope {

  /* Children:
   * 0: b:Bind
   */

  protected Bind b;

  public dclBind(Bind b) {
    this.b = b;
  }

  /* INHERITED ATTRIBUTES */

  public Scope scope(int childId) {
    if (childId == 0) {
      return this.parent.scope(this.childId);
    }
    return null;
  }

  /* SYNTHESIZED ATTRIBUTES */

  public ArrayList<Scope> vars() {
    if (this.vars_computed) {
      return this.vars;
    }
    this.vars = new ArrayList<Scope>(); // this.b.vars();
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