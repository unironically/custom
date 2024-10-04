package lm;
import lm.*;

import java.util.ArrayList;
import java.util.List;

abstract class ModRef<T extends haschild_ModRef<T>> extends TreeNode<T> {

  protected Scope<? extends haschild_Scope<?>> scope = null;
  protected Boolean scope_computed = false;
  protected Boolean scope_visited = false;

  protected ArrayList<Scope<? extends haschild_Scope<?>>> imps = null;
  protected Boolean imps_computed = false;
  protected Boolean imps_visited = false;
  public ArrayList<Scope<? extends haschild_Scope<?>>> imps() { return null; }

  protected String pp = "";
  protected Boolean pp_computed  = false;
  public String pp() { return null; }

  protected ArrayList<Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>> 
    binds = null;
  protected Boolean binds_computed = false;
  protected Boolean binds_visited = false;
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
  protected Boolean r_visited = false;

  public Ref<mref<T>> r() {
    if (this.r_computed) return this.r;
    boolean interrupted_circle = false;
    if (!this.r_visited) {
      this.r_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.r = new mkModRef<mref<T>>(this.id);
      this.r.setParent(this, 0);
      this.r_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.r_visited = false;
      return this.r;
    }
    throw new RuntimeException("Circular definition of mref.r");
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
    if (this.imps_computed) return this.imps;
    boolean interrupted_circle = false;
    if (!this.imps_visited) {
      this.imps_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.imps = new ArrayList<>();
      this.imps.addAll(this.r().res());
      this.imps_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.imps_visited = false;
      return this.imps;
    }
    throw new RuntimeException("Circular definition of dclsCons.imps");
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
    Boolean interrupted_circle = false;
    if (!this.binds_visited) {
      this.binds_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.binds = new ArrayList<>();
      this.binds.addAll(this.r().binds());
      this.binds_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.binds_visited = false;
      return this.binds;
    }
    throw new RuntimeException("Circular definition of dclsCons.binds");
  }

}