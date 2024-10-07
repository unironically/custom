package lm;
import lm.*;

import java.util.ArrayList;
import java.util.List;

abstract class Dcl<T extends haschild_Dcl<T>> extends TreeNode<T> {

  protected Scope scope            = null;
  protected Boolean scope_computed = false;
  protected Boolean scope_visited = false;

  protected ArrayList<Scope<? extends haschild_Scope<?>>> vars  = null;
  protected Boolean vars_computed  = false;
  protected Boolean vars_visited = false;
  public ArrayList<Scope<? extends haschild_Scope<?>>> vars() { return null; }

  protected ArrayList<Scope<? extends haschild_Scope<?>>> mods  = null;
  protected Boolean mods_computed  = false;
  protected Boolean mods_visited = false;
  public ArrayList<Scope<? extends haschild_Scope<?>>> mods() { return new ArrayList<Scope<? extends haschild_Scope<?>>>(); }

  protected ArrayList<Scope<? extends haschild_Scope<?>>> imps  = null;
  protected Boolean imps_computed  = false;
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
  protected Boolean s_visited = false;

  public dclMod(String id, Dcls<dclMod<T>> ds) {
    this.id = id;
    this.ds = ds;

    ds.setParent(this, 0);
  }

  /* LOCALS */

  // local s:Scope = mkMod(id)
  public Scope<dclMod<T>> s() {
    if (this.s_computed) return this.s;
    boolean interrupted_circle = false;
    if (!this.s_visited) {
      this.s_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.s = new mkMod<dclMod<T>>(this.id);
      this.s.setParent(this, 1);
      this.s_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.s_visited = false;
      return this.s;
    }
    throw new RuntimeException("Circular definition of dclMod.s");
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
      return this.s.impTentative();
    }
    return new ArrayList<Scope<? extends haschild_Scope<?>>>();
  }

  // s.impTentative = ds.impTentative
  public ArrayList<Scope<? extends haschild_Scope<?>>> impTentative(int childId) {
    if (childId == 1) {
      ArrayList<Scope<? extends haschild_Scope<?>>> it = this.ds.impTentative();
      return it;
    }
    return new ArrayList<Scope<? extends haschild_Scope<?>>>(); 
  }

  /* SYNTHESIZED ATTRIBUTES */

  // this.vars = []
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
    throw new RuntimeException("Circular definition of dclsCons.vars");
  }

  // this.mods = [s]
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
      this.mods.add(this.s());
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

  // this.imps = []
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
    throw new RuntimeException("Circular definition of dclsCons.imps");
  }*/

  public String pp() {
    if (this.pp_computed) return this.pp;
    this.pp = "declMod(" + this.id + ", " + this.ds.pp() + ")";
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
    throw new RuntimeException("Circular definition of dclsCons.vars");
  }

  // this.mods = []
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
    throw new RuntimeException("Circular definition of dclsCons.mods");
  }

  // this.imps = r.imp
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
      this.imps.addAll(this.r.imps());
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
    this.pp = "dclImp(" + this.r.pp() + ")";
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
      this.binds.addAll(this.r.binds());
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
          this.r.impTentative();
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
          this.r.impTentative();
      if (!new_impTentative_value.equals(impTentative)) CHANGE = true;
      impTentative = new_impTentative_value;
      impTentative_visited = false;
      return impTentative;
    }
    else return impTentative;
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
    if (this.vars_computed) return this.vars;
    boolean interrupted_circle = false;
    if (!this.vars_visited) {
      this.vars_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.vars = new ArrayList<>();
      this.vars.addAll(this.b.vars());
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
    this.pp = "dclBind(" + this.b.pp() + ")";
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
      this.binds.addAll(this.b.binds());
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