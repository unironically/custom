package lm;
import lm.*;

import java.util.ArrayList;

abstract class Dcls<T extends haschild_Dcls<T>> extends TreeNode<T>  {

  protected Scope<? extends haschild_Scope<?>> scope = null;
  protected Boolean scope_computed = false;
  protected Boolean scope_visited = false;

  protected ArrayList<Scope<? extends haschild_Scope<?>>> vars  = null;
  protected Boolean vars_computed = false;
  protected Boolean vars_visited = false;
  public ArrayList<Scope<? extends haschild_Scope<?>>> vars() { return new ArrayList<Scope<? extends haschild_Scope<?>>>(); }

  protected ArrayList<Scope<? extends haschild_Scope<?>>> mods  = null;
  protected Boolean mods_computed = false;
  protected Boolean mods_visited = false;
  public ArrayList<Scope<? extends haschild_Scope<?>>> mods() { return new ArrayList<Scope<? extends haschild_Scope<?>>>(); }

  protected ArrayList<Scope<? extends haschild_Scope<?>>> imps  = null;
  protected Boolean imps_computed = false;
  protected Boolean imps_visited = false;
  public ArrayList<Scope<? extends haschild_Scope<?>>> imps() { return new ArrayList<Scope<? extends haschild_Scope<?>>>(); }

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

class dclsCons<T extends haschild_Dcls<T>> extends Dcls<T> 
implements haschild_Dcl<dclsCons<T>>, haschild_Dcls<dclsCons<T>>
           //, haschild_Scope<dclsCons<T>> 
{

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
  /*private Scope<dclsCons<T>> s = null;
  private Boolean s_computed = false;
  public Scope<dclsCons<T>> s() {
    if (this.s_computed) return this.s;
    this.s = new mkScope<dclsCons<T>>();
    this.s.setParent(this, 2);
    this.s_computed = true;
    return this.s;
  }*/

  /* GIVING CHILDREN INHERITED ATTRIBUTES */

  public Scope<? extends haschild_Scope<?>> scope(int child) {
    if (child == 0) {
      return this.scope();
    } else if (child == 1) {
      // return this.s();          // LM 2
      return this.scope();    // LM 4 (circular)
    }
    return null;
  }

  /*public ArrayList<Scope<? extends haschild_Scope<?>>> lex(int child) {
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
  }*/


  /* GETTING OWN INHERITED ATTRIBUTES */

  public Scope<? extends haschild_Scope<?>> scope() {
    if (this.scope_computed) return this.scope;
    Boolean interrupted_circle = false;
    if (!this.scope_visited) {
      this.scope_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.scope = this.parent.scope(this.childId);
      this.scope_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.scope_visited = false;
      return this.scope; 
    }
    throw new RuntimeException("Circular definition of dclsCons.scope");
  }

  /* SYNTHESIZED ATTRIBUTES */

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
      this.vars.addAll(this.d.vars());
      this.vars.addAll(this.ds.vars());
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

  public ArrayList<Scope<? extends haschild_Scope<?>>> mods() {
    if (this.mods_computed) return this.mods;
    boolean interrupted_circle = false;
    if (!this.mods_visited) {
      this.mods_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.mods = new ArrayList<>();
      this.mods.addAll(this.d.mods());
      this.mods.addAll(this.ds.mods());
      this.mods_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.mods_visited = false;
      return this.mods;
    }
    throw new RuntimeException("Circular definition of dclsCons.mods");
  }

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
      this.imps.addAll(this.d.imps());
      this.imps.addAll(this.ds.imps());
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
    this.pp = "dclsCons(" + this.d.pp() + ", " + this.ds.pp() + ")";
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
      this.binds.addAll(this.d.binds());
      this.binds.addAll(this.ds.binds());
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

  public ArrayList<Scope<? extends haschild_Scope<?>>> impTentative() {
    if (impTentative_computed) return impTentative;
    if (!IN_CIRCLE) {
      IN_CIRCLE = true;
      impTentative_visited = true;
      do {
        CHANGE = false;
        ArrayList<Scope<? extends haschild_Scope<?>>> new_impTentative_value = 
          new ArrayList<>();
        new_impTentative_value.addAll(this.d.impTentative());
        new_impTentative_value.addAll(this.ds.impTentative());
        if (!new_impTentative_value.equals(impTentative)) CHANGE = true;
        impTentative = new_impTentative_value;
      } while (CHANGE);
      impTentative_visited = false;
      impTentative_computed = true;
      IN_CIRCLE = false;
      return impTentative;
    }
    else if (!impTentative_visited) {
      impTentative_visited = true;
      ArrayList<Scope<? extends haschild_Scope<?>>> new_impTentative_value = 
          new ArrayList<>();
        new_impTentative_value.addAll(this.d.impTentative());
        new_impTentative_value.addAll(this.ds.impTentative());
      if (!new_impTentative_value.equals(impTentative)) CHANGE = true;
      impTentative = new_impTentative_value;
      impTentative_visited = false;
      return impTentative;
    }
    else return impTentative;
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
    if (this.vars_computed) return this.vars;
    boolean interrupted_circle = false;
    if (!this.vars_visited) {
      this.vars_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.vars = new ArrayList<>();
      this.vars_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.vars_visited = false;
      return this.vars;
    }
    throw new RuntimeException("Circular definition of dclsNil.vars");
  }

  public ArrayList<Scope<? extends haschild_Scope<?>>> mods() {
    if (this.mods_computed) return this.mods;
    boolean interrupted_circle = false;
    if (!this.mods_visited) {
      this.mods_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.mods = new ArrayList<>();
      this.mods_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.mods_visited = false;
      return this.mods;
    }
    throw new RuntimeException("Circular definition of dclsNil.mods");
  }

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
      this.imps_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.imps_visited = false;
      return this.imps;
    }
    throw new RuntimeException("Circular definition of dclsNil.imps");
  }*/

  public String pp() {
    if (this.pp_computed) return this.pp;
    this.pp = "dclsNil()";
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
      this.binds_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.binds_visited = false;
      return this.binds;
    }
    throw new RuntimeException("Circular definition of dclsNil.binds");
  }

  public ArrayList<Scope<? extends haschild_Scope<?>>> impTentative() {
    if (impTentative_computed) return impTentative;
    if (!IN_CIRCLE) {
      IN_CIRCLE = true;
      impTentative_visited = true;
      do {
        CHANGE = false;
        ArrayList<Scope<? extends haschild_Scope<?>>> new_impTentative_value = 
          new ArrayList<>();
        if (!new_impTentative_value.equals(impTentative)) CHANGE = true;
        impTentative = new_impTentative_value;
      } while (CHANGE);
      impTentative_visited = false;
      impTentative_computed = true;
      IN_CIRCLE = false;
      return impTentative;
    }
    else if (!impTentative_visited) {
      impTentative_visited = true;
      ArrayList<Scope<? extends haschild_Scope<?>>> new_impTentative_value = 
          new ArrayList<>();
      if (!new_impTentative_value.equals(impTentative)) CHANGE = true;
      impTentative = new_impTentative_value;
      impTentative_visited = false;
      return impTentative;
    }
    else return impTentative;
  }

}