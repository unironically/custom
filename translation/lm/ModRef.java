package lm;
import lm.*;

import java.util.ArrayList;
import java.util.List;

abstract class ModRef<T extends haschild_ModRef<T>> extends TreeNode<T> {

  protected Scope<? extends haschild_Scope<?>> scope = null;
  protected Boolean scope_computed = false;

  protected ArrayList<Scope<? extends haschild_Scope<?>>> imps = null;
  protected Boolean imps_computed = false;
  public ArrayList<Scope<? extends haschild_Scope<?>>> imps() { return null; }

  protected String pp = "";
  protected Boolean pp_computed  = false;
  public String pp() { return null; }

  protected ArrayList<Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>> 
    binds = null;
  protected Boolean binds_computed = false;
  public ArrayList<Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>> 
    binds() { return null; }

}

class mref<T extends haschild_ModRef<T>> extends ModRef<T>
implements haschild_Ref<mref<T>> {

  /* Children:
   * 0: Ref
   */

  private String id;

  public mref(String id) {
    this.id = id;
  }

  /* LOCALS */

  protected Ref<mref<T>> r = null;
  protected Boolean r_computed = false;

  public Ref<mref<T>> r() {
    if (this.r_computed) {
      return this.r;
    }
    this.r = new mkModRef<mref<T>>(this.id);
    this.r.setParent(this, 0);
    this.r_computed = true;
    return this.r;
  }

  /* INHERITED ATTRIBUTES */

  // r.lex = [this.scope]
  public ArrayList<Scope<? extends haschild_Scope<?>>> lex(int childId) {
    if (childId == 0) {
      return 
        new ArrayList<Scope<? extends haschild_Scope<?>>>
          (List.of(this.parent.scope(this.childId)));
    }
    return null;
  }

  /* SYNTHESIZED ATTRIBUTES */

  // this.imps = r.res
  public ArrayList<Scope<? extends haschild_Scope<?>>> imps() {
    if (this.imps_computed) {
      return this.imps;
    }
    this.imps = this.r().res();
    this.imps_computed = true;
    return this.imps;
  }

  public String pp() {
    if (this.pp_computed) return this.pp;
    this.pp = "mref(\"" + this.id + "\")";
    this.pp_computed = true;
    return this.pp;
  }

  public ArrayList<Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>> 
  binds() {
    if(this.binds_computed) return this.binds;
    this.binds = this.r().binds();
    this.binds_computed = true;
    return this.binds;
  }

}