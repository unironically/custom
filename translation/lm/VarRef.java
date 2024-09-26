package lm;

import java.util.ArrayList;
import java.util.List;

abstract class VarRef<T extends inh_scope> extends TreeNode {
  
  protected T parent;

  protected Scope scope            = null;
  protected Boolean scope_computed = false;

  protected ArrayList<Scope> res = null;
  protected Boolean res_computed = false;
  public ArrayList<Scope> res() { return null; }

}

class vref<T extends inh_scope> extends VarRef<T>
implements inh_lex {

  /* Children:
   * 0: Ref
   */

  private String id;

  public vref(String id) {
    this.id = id;
  }

  /* LOCALS */

  protected Ref<vref> r = null;
  protected Boolean r_computed = false;

  public Ref<vref> r() {
    if (this.r_computed) {
      return this.r;
    }
    this.r = new mkVarRef<vref>(this.id);
    this.r.setParent(this, 0);
    this.r_computed = true;
    return this.r;
  }

  /* INHERITED ATTRIBUTES */

  public ArrayList<Scope> lex(int childId) {
    if (childId == 0) {
      return new ArrayList<Scope>(List.of( this.parent.scope(this.childId) ));
    }
    return null;
  }

  /* SYNTHESIZED ATTRIBUTES */

  // this.res = r.res
  public ArrayList<Scope> res() {
    if (this.res_computed) {
      return this.res;
    }
    this.res = this.r().res();
    this.res_computed = true;
    return this.res;
  }

}