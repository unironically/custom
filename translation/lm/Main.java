package lm;
import lm.*;

import java.util.ArrayList;

// nt Main
abstract class Main extends TreeNode<Driver> {

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


// prod main: Main ::= ds:Dcls name:String
class main extends Main
  implements haschild_Dcls<main>, haschild_Scope<main> {

  protected Dcls<main> ds;
  
  protected Scope<main> s = null;
  protected Boolean s_computed = false;
  protected Boolean s_visited = false;

  public main(Dcls<main> ds) {
    this.ds = ds;

    // setting parents
    ds.setParent(this, 0);
  }

  public Scope<main> s() {
    if (s_computed) return this.s;
    Boolean interrupted_circle = false;
    if (!this.s_visited) {
      this.s_visited = true;
      if (TreeNode.IN_CIRCLE){
        TreeNode.STACK.push(TreeNode.CHANGE);
        TreeNode.IN_CIRCLE = false;
        interrupted_circle = true;
      }
      this.s = new mkScope<main>();
      this.s.setParent(this, 1);
      this.s_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.s_visited = false;
      return this.s;
    }
    throw new RuntimeException("Circular definition of Main.s");
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

  // s.imp = s.impTentative
  public ArrayList<Scope<? extends haschild_Scope<?>>> imp(int childId) {
    if (childId == 1) {
      return this.s.impTentative();
    }
    return new ArrayList<Scope<? extends haschild_Scope<?>>>();
  }

  // s.impTentative = ds.impTentative
  public ArrayList<Scope<? extends haschild_Scope<?>>> impTentative(int childId) {
    if (childId == 1) {
      return this.ds.impTentative();
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
    Boolean interrupted_circle = false;
    if (!this.binds_visited) {
      this.binds_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.binds = new ArrayList<>();
      this.binds.addAll(this.ds.binds());
      this.binds_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.binds_visited = false;
      return this.binds;
    }
    throw new RuntimeException("Circular definition of main.binds");
  }

}