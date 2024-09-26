package lm;

import java.util.ArrayList;
import java.util.List;

abstract class Bind<T extends inh_scope> extends TreeNode {
  
  protected T parent;

  protected Scope scope            = null;
  protected Boolean scope_computed = false;

  protected ArrayList<Scope> vars  = null;
  protected Boolean vars_computed  = false;

  protected Type type = null;
  protected Boolean type_computed = false;
  protected Type type() { return null; }

}

class bnd<T extends inh_scope> extends Bind<T>
implements inh_scope, inh_lex, inh_var, inh_mod, inh_imp {

  /* Children:
   * 0: e:Exp
   * 1: d:Scope
   */

  protected String id;
  protected Exp e;

  public bnd(String id, Exp e) {
    this.id = id;
    this.e = e;
  }

  /* LOCALS */

  private Scope d = null;
  private Boolean d_computed = false;

  public Scope d() {
    if (this.d_computed) {
      return this.d;
    }
    this.d = new mkVar(this.id);
    this.d_computed = true;
    return this.d;
  }

  /* INHERITED ATTRIBUTES */

  // e.scope = this.scope
  public Scope scope(int childId) {
    if (childId == 0) {
      return this.parent.scope(this.childId);
    }
    return null;
  }

  // d.lex = []
  public ArrayList<Scope> lex(int childId) {
    if (childId == 1) {
      return new ArrayList<Scope>();
    }
    return new ArrayList<Scope>();
  }

  // d.var = []
  public ArrayList<Scope> var(int childId) {
    if (childId == 1) {
      return new ArrayList<Scope>();
    }
    return new ArrayList<Scope>();
  }

  // d.mod = []
  public ArrayList<Scope> mod(int childId) {
    if (childId == 1) {
      return new ArrayList<Scope>();
    }
    return new ArrayList<Scope>();
  }

  // d.imp = []
  public ArrayList<Scope> imp(int childId) {
    if (childId == 1) {
      return new ArrayList<Scope>();
    }
    return new ArrayList<Scope>();
  }

  /* SYNTHESIZED ATTRIBUTES */

  // this.type = e.type
  public Type type() {
    if (this.type_computed) {
      return this.type;
    }
    this.type = this.e.type();
    this.type_computed = true;
    return this.type;
  }

  // this.vars = [d]
  public ArrayList<Scope> vars() {
    if (this.vars_computed) {
      return this.vars;
    }
    this.vars = new ArrayList<Scope>(List.of( this.d() ));
    this.vars_computed = true;
    return this.vars;
  }

}