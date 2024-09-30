package lm;
import lm.*;

import java.util.ArrayList;
import java.util.List;

abstract class VarRef<T extends haschild_VarRef<T>> extends TreeNode {
  
  protected T parent;
  public void setParent(T parent, int childId) {
    this.parent = parent;
    this.childId = childId;
  }

  protected Scope<? extends haschild_Scope<?>> scope = null;
  protected Boolean scope_computed = false;

  protected ArrayList<Scope<? extends haschild_Scope<?>>> res = null;
  protected Boolean res_computed = false;
  public ArrayList<Scope<? extends haschild_Scope<?>>> res() { return null; }

  protected String pp = "";
  protected Boolean pp_computed  = false;
  public String pp() { return null; }

  protected ArrayList<Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>> 
    binds = null;
  protected Boolean binds_computed = false;
  public ArrayList<Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>> 
    binds() { return null; }

}

class vref<T extends haschild_VarRef<T>> extends VarRef<T>
implements haschild_Ref<vref<T>> {

  /* Children:
   * 0: Ref
   */

  private String id;

  public vref(String id) {
    this.id = id;
  }

  /* LOCALS */

  protected Ref<vref<T>> r = null;
  protected Boolean r_computed = false;
  public Ref<vref<T>> r() {
    if (this.r_computed) {
      return this.r;
    }
    this.r = new mkVarRef<vref<T>>(this.id);
    this.r.setParent(this, 0);
    this.r_computed = true;
    return this.r;
  }

  /* INHERITED ATTRIBUTES */

  public ArrayList<Scope<? extends haschild_Scope<?>>> lex(int childId) {
    if (childId == 0) {
      return
        new ArrayList<Scope<? extends haschild_Scope<?>>>
          (List.of(this.parent.scope(this.childId)));
    }
    return null;
  }

  /* SYNTHESIZED ATTRIBUTES */

  // this.res = r.res
  public ArrayList<Scope<? extends haschild_Scope<?>>> res() {
    if (this.res_computed) {
      return this.res;
    }
    this.res = this.r().res();
    this.res_computed = true;
    return this.res;
  }

  public String pp() {
    if (this.pp_computed) return this.pp;
    this.pp = "vref(\"" + this.id + "\")";
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