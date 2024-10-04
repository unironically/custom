package lm;
import lm.*;
import java.util.*;
abstract class ModRef<T extends hasChild_ModRef<T>> extends TreeNode<T> {
	protected Scope<? extends hasChild_Scope<?>> scope = null;
	protected Boolean scope_computed = false;
	public Scope<? extends hasChild_Scope<?>> scope() { return null; }

	protected ArrayList<Scope<? extends hasChild_Scope<?>>> imps = null;
	protected Boolean imps_computed = false;
	public ArrayList<Scope<? extends hasChild_Scope<?>>> imps() { return null; }

	protected ArrayList<Pair<Ref<? extends hasChild_Ref<?>>, ArrayList<Scope<? extends hasChild_Scope<?>>>>> binds = null;
	protected Boolean binds_computed = false;
	public ArrayList<Pair<Ref<? extends hasChild_Ref<?>>, ArrayList<Scope<? extends hasChild_Scope<?>>>>> binds() { return null; }
}

class mref<T extends hasChild_ModRef<T>> extends ModRef<T> implements hasChild_Ref<mref<T>> {

/* CHILD FIELDS */
private String id;
/* CONSTRUCTOR */
public mref(String id) {
this.id = id;
}
/* LOCALS */
private Ref<mref<T>> r = null;
private Boolean r_computed = false;
public Ref<mref<T>> r() {
if (this.r_computed) return this.r;
this.r = new mkModRef<>(id);
this.r.setParent(this, 0);
this.r_computed = true;
return this.r;
}
/* SETTING CHILD INHS */
public ArrayList<Scope<? extends hasChild_Scope<?>>> lex(int childId) {
// r.lex
if (childId == 0) {
ArrayList<Scope<? extends hasChild_Scope<?>>> combList_7 = new ArrayList<>();
combList_7.add(this.scope());
return combList_7;
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
public ArrayList<Scope<? extends hasChild_Scope<?>>> imps() {
if (this.imps_computed) return this.imps;
this.imps = r().res();
this.imps_computed = true;
return this.imps;
}
public ArrayList<Pair<Ref<? extends hasChild_Ref<?>>, ArrayList<Scope<? extends hasChild_Scope<?>>>>> binds() {
if (this.binds_computed) return this.binds;ArrayList<Pair<Ref<? extends hasChild_Ref<?>>, ArrayList<Scope<? extends hasChild_Scope<?>>>>> combList_8 = new ArrayList<>();
combList_8.add(new Pair<>(r(), r().res()));
this.binds = combList_8;
this.binds_computed = true;
return this.binds;
}

}