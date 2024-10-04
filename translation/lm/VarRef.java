package lm;
import lm.*;

import java.util.ArrayList;
import java.util.List;

abstract class VarRef<T extends haschild_VarRef<T>> extends TreeNode<T> {

  protected Scope<? extends haschild_Scope<?>> scope = null;
  protected Boolean scope_computed = false;
  protected Boolean scope_visited = false;

  protected ArrayList<Scope<? extends haschild_Scope<?>>> res = null;
  protected Boolean res_computed = false;
  protected Boolean res_visited = false;
  public ArrayList<Scope<? extends haschild_Scope<?>>> res() { return null; }

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
  protected Boolean r_visited = false;

  public Ref<vref<T>> r() {
    if (this.r_computed) return this.r;
    boolean interrupted_circle = false;
    if (!this.r_visited) {
      this.r_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.r = new mkVarRef<vref<T>>(this.id);
      this.r.setParent(this, 0);
      this.r_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.r_visited = false;
      return this.r;
    }
    throw new RuntimeException("Circular definition of vref.r");
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
    if (this.res_computed) return this.res;
    boolean interrupted_circle = false;
    if (!this.res_visited) {
      this.res_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.res = new ArrayList<>();
      this.res.addAll(this.r().res());
      this.res_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.res_visited = false;
      return this.res;
    }
    throw new RuntimeException("Circular definition of vref.res");
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