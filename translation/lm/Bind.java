package lm;
import lm.*;

import java.util.ArrayList;
import java.util.List;

abstract class Bind<T extends haschild_Bind<T>> extends TreeNode<T> {

  protected Scope<? extends haschild_Scope<?>> scope = null;
  protected Boolean scope_computed = false;
  protected Boolean scope_visited = false;

  protected ArrayList<Scope<? extends haschild_Scope<?>>> vars  = null;
  protected Boolean vars_computed  = false;
  protected Boolean vars_visited = false;
  public ArrayList<Scope<? extends haschild_Scope<?>>> vars() { return null; }

  protected Type type = null;
  protected Boolean type_computed = false;
  protected Boolean type_visited = false;
  protected Type type() { return null; }

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

class bnd<T extends haschild_Bind<T>> extends Bind<T>
implements haschild_Scope<bnd<T>>, haschild_Exp<bnd<T>> {

  /* Children:
   * 0: e:Exp
   * 1: d:Scope
   */

  private String id;
  private Type t;
  private Exp<bnd<T>> e;

  public bnd(String id, Type t, Exp<bnd<T>> e) {
    this.id = id;
    this.t = t;
    this.e = e;

    this.e.setParent(this, 0);
  }

  /* LOCALS */

  private Scope<bnd<T>> d = null;
  private Boolean d_computed = false;
  private Boolean d_visited = false;

  public Scope<bnd<T>> d() {
    if (this.d_computed) return this.d;
    boolean interrupted_circle = false;
    if (!this.d_visited) {
      this.d_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.d = new mkVar<bnd<T>>(this.id, this.t);
      this.d.setParent(this, 1);
      this.d_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.d_visited = false;
      return this.d;
    }
    throw new RuntimeException("Circular definition of bnd.d");
  }

  /* INHERITED ATTRIBUTES */

  // e.scope = this.scope
  public Scope<? extends haschild_Scope<?>> scope(int childId) {
    if (childId == 0) {
      return this.parent.scope(this.childId);
    }
    return null;
  }

  // d.lex = []
  public ArrayList<Scope<? extends haschild_Scope<?>>> lex(int childId) {
    if (childId == 1) {
      return new ArrayList<Scope<? extends haschild_Scope<?>>>();
    }
    return new ArrayList<Scope<? extends haschild_Scope<?>>>();
  }

  // d.var = []
  public ArrayList<Scope<? extends haschild_Scope<?>>> var(int childId) {
    if (childId == 1) {
      return new ArrayList<Scope<? extends haschild_Scope<?>>>();
    }
    return new ArrayList<Scope<? extends haschild_Scope<?>>>();
  }

  // d.mod = []
  public ArrayList<Scope<? extends haschild_Scope<?>>> mod(int childId) {
    if (childId == 1) {
      return new ArrayList<Scope<? extends haschild_Scope<?>>>();
    }
    return new ArrayList<Scope<? extends haschild_Scope<?>>>();
  }

  // d.imp = []
  public ArrayList<Scope<? extends haschild_Scope<?>>> imp(int childId) {
    if (childId == 1) {
      return new ArrayList<Scope<? extends haschild_Scope<?>>>();
    }
    return new ArrayList<Scope<? extends haschild_Scope<?>>>();
  }

  /* SYNTHESIZED ATTRIBUTES */

  // this.type = e.type
  public Type type() {
    if (this.type_computed) {
      return this.type;
    }
    this.type = this.t;
    this.type_computed = true;
    return this.type;
  }

  // this.vars = [d]
  public ArrayList<Scope<? extends haschild_Scope<?>>> vars() {
    if (this.vars_computed) return this.vars;
    boolean interrupted_circle = false;
    if (!this.vars_visited) {
      this.vars_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.vars = new ArrayList<>();
      this.vars.add(this.d());
      this.vars_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.vars_visited = false;
      return this.vars;
    }
    throw new RuntimeException("Circular definition of dclsCons.vars");
  }

  public String pp() {
    if (this.pp_computed) return this.pp;
    this.pp = "bnd(\"" + this.id + "\", " + this.e.pp() + ")";
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
      this.binds.addAll(this.e.binds());
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