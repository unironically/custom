package lm;
import lm.*;

import java.util.ArrayList;
import java.util.List;

abstract class Dcl<T extends haschild_Dcl<T>> extends TreeNode<T> {

  protected Scope scope            = null;
  protected Boolean scope_computed = false;

  protected ArrayList<Scope<? extends haschild_Scope<?>>> vars  = null;
  protected Boolean vars_computed  = false;
  public ArrayList<Scope<? extends haschild_Scope<?>>> vars() { return null; }

  protected ArrayList<Scope<? extends haschild_Scope<?>>> mods  = null;
  protected Boolean mods_computed  = false;
  public ArrayList<Scope<? extends haschild_Scope<?>>> mods() { return new ArrayList<Scope<? extends haschild_Scope<?>>>(); }

  protected ArrayList<Scope<? extends haschild_Scope<?>>> imps  = null;
  protected Boolean imps_computed  = false;
  public ArrayList<Scope<? extends haschild_Scope<?>>> imps() { return new ArrayList<Scope<? extends haschild_Scope<?>>>(); }

  protected String pp = "";
  protected Boolean pp_computed  = false;
  public String pp() { return null; }

  protected ArrayList<Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>> 
    binds = null;
  protected Boolean binds_computed = false;
  public ArrayList<Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>> 
    binds() { return null; }
 
}

class dclMod<T extends haschild_Dcl<T>> extends Dcl<T>
implements haschild_Scope<dclMod<T>>, haschild_Dcls<dclMod<T>> {

  /* Children:
   * 0: ds:Dcls
   * 1: local s:Scope
   */

  protected String id;
  protected Dcls<dclMod<T>> ds;

  protected Scope<dclMod<T>> s = null;
  protected Boolean s_computed = false;

  public dclMod(String id, Dcls<dclMod<T>> ds) {
    this.id = id;
    this.ds = ds;

    ds.setParent(this, 0);
  }

  /* LOCALS */

  // local s:Scope = mkMod(id)
  public Scope<dclMod<T>> s() {
    if (this.s_computed) {
      return this.s;
    }
    this.s = new mkMod<dclMod<T>>(this.id);
    this.s.setParent(this, 1);
    this.s_computed = true;
    return this.s;
  }

  /* INHERITED ATTRIBUTES */

  // ds.scope = s
  public Scope<? extends haschild_Scope<?>> scope(int childId) {
    if (childId == 0) {
      return this.s();
    }
    return null;
  }

  // s.lex = [this.scope]
  public ArrayList<Scope<? extends haschild_Scope<?>>> lex(int childId) {
    if (childId == 1) {
      return new ArrayList<Scope<? extends haschild_Scope<?>>>(List.of( this.parent.scope(this.childId) ));
    }
    return new ArrayList<Scope<? extends haschild_Scope<?>>>();
  }

  // s.var = ds.vars
  public ArrayList<Scope<? extends haschild_Scope<?>>> var(int childId) {
    if (childId == 1) {
      return this.ds.vars();
    }
    return new ArrayList<Scope<? extends haschild_Scope<?>>>();
  }

  // s.mod = ds.mods
  public ArrayList<Scope<? extends haschild_Scope<?>>> mod(int childId) {
    if (childId == 1) {
      return this.ds.mods();
    }
    return new ArrayList<Scope<? extends haschild_Scope<?>>>();
  }

  // s.imp = ds.imps
  public ArrayList<Scope<? extends haschild_Scope<?>>> imp(int childId) {
    if (childId == 1) {
      return this.ds.imps();
    }
    return new ArrayList<Scope<? extends haschild_Scope<?>>>();
  }

  /* SYNTHESIZED ATTRIBUTES */

  // this.vars = []
  public ArrayList<Scope<? extends haschild_Scope<?>>> vars() {
    if (this.vars_computed) {
      return this.vars;
    }
    this.vars = new ArrayList<Scope<? extends haschild_Scope<?>>>();
    this.vars_computed = true;
    return this.vars;
  }

  // this.mods = [s]
  public ArrayList<Scope<? extends haschild_Scope<?>>> mods() {
    if (this.mods_computed) {
      return this.mods;
    }
    this.mods = new ArrayList<Scope<? extends haschild_Scope<?>>>(List.of( this.s() ));
    this.mods_computed = true;
    return this.mods;
  }

  // this.imps = []
  public ArrayList<Scope<? extends haschild_Scope<?>>> imps() {
    if (this.imps_computed) {
      return this.imps;
    }
    this.imps = new ArrayList<Scope<? extends haschild_Scope<?>>>();
    this.imps_computed = true;
    return this.imps;
  }

  public String pp() {
    if (this.pp_computed) return this.pp;
    this.pp = "declMod(" + this.id + ", " + this.ds.pp() + ")";
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


class dclImp<T extends haschild_Dcl<T>> extends Dcl<T>
implements haschild_ModRef<dclImp<T>> {

  /* Children:
   * 0: r:ModRef
   */

  protected ModRef<dclImp<T>> r;

  public dclImp(ModRef<dclImp<T>> r) {
    this.r = r;
    this.r.setParent(this, 0);
  }

  /* INHERITED ATTRIBUTES */

  // r.scope = this.scope
  public Scope<? extends haschild_Scope<?>> scope(int childId) {
    if (childId == 0) {
      return this.parent.scope(this.childId);
    }
    return null;
  }

  /* SYNTHESIZED ATTRIBUTES */

  // this.vars = []
  public ArrayList<Scope<? extends haschild_Scope<?>>> vars() {
    if (this.vars_computed) {
      return this.vars;
    }
    this.vars = new ArrayList<Scope<? extends haschild_Scope<?>>>();
    this.vars_computed = true;
    return this.vars;
  }

  // this.mods = []
  public ArrayList<Scope<? extends haschild_Scope<?>>> mods() {
    if (this.mods_computed) {
      return this.mods;
    }
    this.mods = new ArrayList<Scope<? extends haschild_Scope<?>>>();
    this.mods_computed = true;
    return this.mods;
  }

  // this.imps = r.imp
  public ArrayList<Scope<? extends haschild_Scope<?>>> imps() {
    if (this.imps_computed) {
      return this.imps;
    }
    this.imps = this.r.imps();
    this.imps_computed = true;
    return this.imps;
  }

  public String pp() {
    if (this.pp_computed) return this.pp;
    this.pp = "dclImp(" + this.r.pp() + ")";
    this.pp_computed = true;
    return this.pp;
  }

  public ArrayList<Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>> 
  binds() {
    if(this.binds_computed) return this.binds;
    this.binds = this.r.binds();
    this.binds_computed = true;
    return this.binds;
  }

}


class dclBind<T extends haschild_Dcl<T>> extends Dcl<T>
implements haschild_Bind<dclBind<T>> {

  /* Children:
   * 0: b:Bind
   */

  protected Bind<dclBind<T>> b;

  public dclBind(Bind<dclBind<T>> b) {
    this.b = b;
    this.b.setParent(this, 0);
  }

  /* INHERITED ATTRIBUTES */

  public Scope<? extends haschild_Scope<?>> scope(int childId) {
    if (childId == 0) {
      return this.parent.scope(this.childId);
    }
    return null;
  }

  /* SYNTHESIZED ATTRIBUTES */

  public ArrayList<Scope<? extends haschild_Scope<?>>> vars() {
    if (this.vars_computed) {
      return this.vars;
    }
    this.vars = this.b.vars();
    this.vars_computed = true;
    return this.vars;
  }

  public ArrayList<Scope<? extends haschild_Scope<?>>> mods() {
    if (this.mods_computed) {
      return this.mods;
    }
    this.mods = new ArrayList<Scope<? extends haschild_Scope<?>>>();
    this.mods_computed = true;
    return this.mods;
  }

  public ArrayList<Scope<? extends haschild_Scope<?>>> imps() {
    if (this.imps_computed) {
      return this.imps;
    }
    this.imps = new ArrayList<Scope<? extends haschild_Scope<?>>>();
    this.imps_computed = true;
    return this.imps;
  }

  public String pp() {
    if (this.pp_computed) return this.pp;
    this.pp = "dclBind(" + this.b.pp() + ")";
    this.pp_computed = true;
    return this.pp;
  }

  public ArrayList<Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>> 
  binds() {
    if(this.binds_computed) return this.binds;
    this.binds = this.b.binds();
    this.binds_computed = true;
    return this.binds;
  }

}