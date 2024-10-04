package lm;
import lm.*;
import java.util.*;
abstract class VarRef<T extends hasChild_VarRef<T>> extends TreeNode<T> {
	protected Scope<? extends hasChild_Scope<?>> scope = null;
	protected Boolean scope_computed = false;
	public Scope<? extends hasChild_Scope<?>> scope() { return null; }

	protected ArrayList<Scope<? extends hasChild_Scope<?>>> res = null;
	protected Boolean res_computed = false;
	public ArrayList<Scope<? extends hasChild_Scope<?>>> res() { return null; }

	protected ArrayList<Pair<Ref<? extends hasChild_Ref<?>>, ArrayList<Scope<? extends hasChild_Scope<?>>>>> binds = null;
	protected Boolean binds_computed = false;
	public ArrayList<Pair<Ref<? extends hasChild_Ref<?>>, ArrayList<Scope<? extends hasChild_Scope<?>>>>> binds() { return null; }
}

class vref<T extends hasChild_VarRef<T>> extends VarRef<T> implements hasChild_Ref<vref<T>> {

/* CHILD FIELDS */
private String id;
/* CONSTRUCTOR */
public vref(String id) {
this.id = id;
}
/* LOCALS */
private Ref<vref<T>> r = null;
private Boolean r_computed = false;
public Ref<vref<T>> r() {
if (this.r_computed) return this.r;
this.r = new mkVarRef<>(id);
this.r.setParent(this, 0);
this.r_computed = true;
return this.r;
}
/* SETTING CHILD INHS */
public ArrayList<Scope<? extends hasChild_Scope<?>>> lex(int childId) {
// r.lex
if (childId == 0) {
ArrayList<Scope<? extends hasChild_Scope<?>>> combList_9 = new ArrayList<>();
combList_9.add(this.scope());
return combList_9;
}return null;
}
/* GETTING OWN INHS */
public Scope<? extends hasChild_Scope<?>> scope() {
if (this.scope_computed) return this.scope;
this.scope = this.parent.scope(this.childId);
this.scope_computed = true;
return this.scope;
}
/* SYNTHESIZED ATTRS */
public ArrayList<Scope<? extends hasChild_Scope<?>>> res() {
if (this.res_computed) return this.res;
this.res = r().res();
this.res_computed = true;
return this.res;
}
public ArrayList<Pair<Ref<? extends hasChild_Ref<?>>, ArrayList<Scope<? extends hasChild_Scope<?>>>>> binds() {
if (this.binds_computed) return this.binds;ArrayList<Pair<Ref<? extends hasChild_Ref<?>>, ArrayList<Scope<? extends hasChild_Scope<?>>>>> combList_10 = new ArrayList<>();
combList_10.add(new Pair<>(r(), r().res()));
this.binds = combList_10;
this.binds_computed = true;
return this.binds;
}

}