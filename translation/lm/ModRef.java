package lm;

import java.util.ArrayList;
import java.util.List;

abstract class ModRef<T extends inh_scope> extends TreeNode {
  
  protected T parent;

  protected Scope scope            = null;
  protected Boolean scope_computed = false;

  protected ArrayList<Scope> imps = null;
  protected Boolean imps_computed = false;
  public ArrayList<Scope> imps() { return null; }

}

class mref<T extends inh_scope> extends ModRef<T>
implements inh_lex {

  /* Children:
   * 0: Ref
   */

  private String id;

  public mref(String id) {
    this.id = id;
  }

  /* LOCALS */

  protected Ref<mref> r = null;
  protected Boolean r_computed = false;

  public Ref<mref> r() {
    if (this.r_computed) {
      return this.r;
    }
    this.r = new mkModRef<mref>(this.id);
    this.r.setParent(this, 0);
    this.r_computed = true;
    return this.r;
  }

  /* INHERITED ATTRIBUTES */

  // r.lex = [this.scope]
  public ArrayList<Scope> lex(int childId) {
    if (childId == 0) {
      return new ArrayList<Scope>(List.of(this.parent.scope(this.childId)));
    }
    return null;
  }

  /* SYNTHESIZED ATTRIBUTES */

  // this.imps = r.res
  public ArrayList<Scope> imps() {
    if (this.imps_computed) {
      return this.imps;
    }
    this.imps = this.r().res();
    this.imps_computed = true;
    return this.imps;
  }

}