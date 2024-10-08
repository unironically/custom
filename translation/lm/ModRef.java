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

  // impTentative, circular attribute
  protected ArrayList<Scope<? extends haschild_Scope<?>>> impTentative = null;
  protected Boolean impTentative_computed = false;
  protected Boolean impTentative_visited = false;
  public ArrayList<Scope<? extends haschild_Scope<?>>> impTentative()
    { return null; }

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
  /*public ArrayList<Scope<? extends haschild_Scope<?>>> imps() {
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
  }*/

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

  // circular attribute impTentative
  public ArrayList<Scope<? extends haschild_Scope<?>>> impTentative() {
    if (READY && !impTentative_computed) {
      impTentative_computed = true;
      this.r().res();
    }
    if (scopeTrace) System.out.println(TreeNode.tab() + this.pp() + ".impTentative demanded");
    if (impTentative_computed) {
      if (scopeTrace) System.out.println(TreeNode.tab() + this.pp() + ".impTentative already computed");
      return impTentative;
    }
    if (scopeTrace) System.out.print(TreeNode.tab() + this.pp() + ".impTentative not yet computed ");
    if (!IN_CIRCLE) {
      if (scopeTrace) System.out.println("(initial circular demand)");
      IN_CIRCLE = true;
      impTentative_visited = true;
      do {
        if (scopeTrace) System.out.println(TreeNode.tab() + this.pp() + ".impTentative new iteration");
        CHANGE = false;
        TreeNode.tabIncrease();
        ArrayList<Scope<? extends haschild_Scope<?>>> new_impTentative_value = 
          this.r().res();
        TreeNode.tabDecrease();
        if (!new_impTentative_value.equals(impTentative)) CHANGE = true;
        impTentative = new_impTentative_value;
      } while (CHANGE);
      impTentative_visited = false;
      impTentative_computed = true;
      IN_CIRCLE = false;
      if (scopeTrace) System.out.println(TreeNode.tab() + this.pp() + ".impTentative done computing");

      READY = true;
      this.r().res();
      READY = false;

      return impTentative;
    }
    else if (!impTentative_visited) {
      if (scopeTrace) System.out.println("(intermediate circular demand)");
      impTentative_visited = true;
      TreeNode.tabIncrease();
      ArrayList<Scope<? extends haschild_Scope<?>>> new_impTentative_value = 
          this.r().res();
      TreeNode.tabDecrease();
      if (!new_impTentative_value.equals(impTentative)) CHANGE = true;
      impTentative = new_impTentative_value;
      impTentative_visited = false;
      if (scopeTrace) System.out.println(TreeNode.tab() + this.pp() + ".impTentative done computing, but not set");
      return impTentative;
    }
    else {
      if (scopeTrace) System.out.println("(already visited)");
      return impTentative;
    }
  }

}