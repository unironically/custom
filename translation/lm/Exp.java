package lm;

abstract class Exp<T extends inh_scope> extends TreeNode {

  protected T parent;

  protected Scope scope            = null;
  protected Boolean scope_computed = false;

  protected Type type = null;
  protected Boolean type_computed = false;

  public Type type() { return null; }
  
}

class expRef<T extends inh_scope> extends Exp<T> 
implements inh_scope {

  /* Children:
   * 0: r:VarRef
   */

  private VarRef r;

  public expRef(VarRef r) {
    this.r = r;
  }

  /* LOCALS */

  /* INHERITED ATTRIBUTES */

  // r.scope = this.scope
  public Scope scope(int childId) {
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
    this.type = new intType(); //TODO
    this.type_computed = true;
    return this.type;
  }

}

class expInt<T extends inh_scope> extends Exp<T> {
  
  /* Children:
   *
   */

  public expInt(int i) {

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

}