package lm;
import lm.*;

import java.util.ArrayList;

abstract class Exp<T extends haschild_Exp<T>> extends TreeNode {

  protected T parent;
  public void setParent(T parent, int childId) {
    this.parent = parent;
    this.childId = childId;
  }

  protected Scope scope            = null;
  protected Boolean scope_computed = false;

  protected Type type = null;
  protected Boolean type_computed = false;

  public Type type() { return null; }

  protected String pp = "";
  protected Boolean pp_computed  = false;
  public String pp() { return null; }

  protected ArrayList<Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>> 
    binds = null;
  protected Boolean binds_computed = false;
  public ArrayList<Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>> 
    binds() { return null; }
  
}

class expRef<T extends haschild_Exp<T>> extends Exp<T> 
implements haschild_VarRef<expRef<T>> {

  /* Children:
   * 0: r:VarRef
   */

  private VarRef<expRef<T>> r;

  public expRef(VarRef<expRef<T>> r) {
    this.r = r;
    this.r.setParent(this, 0);
  }

  /* LOCALS */

  /* INHERITED ATTRIBUTES */

  // r.scope = this.scope
  public Scope<? extends haschild_Scope<?>> scope(int childId) {
    if (childId == 0) {
      return this.parent.scope(this.childId);
    }
    return null;
  }

  /* SYNTHESIZED ATTRIBUTES */

  // this.type = r.type
  public Type type() {
    if (this.type_computed) {
      return this.type;
    }
    this.type = this.r.res().size() > 0 ?
                  ((Scope) this.r.res().get(0)).datum().type() :
                  new errType(); //new intType(); //TODO
    this.type_computed = true;
    return this.type;
  }

  public String pp() {
    if (this.pp_computed) return this.pp;
    this.pp = "expRef(" + this.r.pp() + ")";
    this.pp_computed = true;
    return this.pp;
  }

  public ArrayList<Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>> 
  binds() {
    if(this.binds_computed) return this.binds;
    this.binds = this.r.binds();
    this.binds_computed = true;
    return this.binds;
  }

}

class expInt<T extends haschild_Exp<T>> extends Exp<T> {
  
  private int i;

  /* Children:
   *
   */

  public expInt(int i) {
    this.i = i;
  }

  /* LOCALS */

  /* INHERITED ATTRIBUTES */

  /* SYNTHESIZED ATTRIBUTES */

  // this.type = intType()
  public Type type() {
    if (this.type_computed) {
      return this.type;
    }
    this.type = new intType();
    this.type_computed = true;
    return this.type;
  }

  public String pp() {
    if (this.pp_computed) return this.pp;
    this.pp = "expInt(" + Integer.toString(this.i) + ")";
    this.pp_computed = true;
    return this.pp;
  }

  public ArrayList<Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>> 
  binds() {
    if(this.binds_computed) return this.binds;
    this.binds = 
      new ArrayList<Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>>();
    this.binds_computed = true;
    return this.binds;
  }

}