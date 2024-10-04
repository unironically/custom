package lm;
import lm.*;
import java.util.*;
abstract class Dcls<T extends hasChild_Dcls<T>> extends TreeNode<T> {
	protected Scope<? extends hasChild_Scope<?>> scope = null;
	protected Boolean scope_computed = false;
	public Scope<? extends hasChild_Scope<?>> scope() { return null; }

	protected ArrayList<Scope<? extends hasChild_Scope<?>>> vars = null;
	protected Boolean vars_computed = false;
	public ArrayList<Scope<? extends hasChild_Scope<?>>> vars() { return null; }

	protected ArrayList<Scope<? extends hasChild_Scope<?>>> mods = null;
	protected Boolean mods_computed = false;
	public ArrayList<Scope<? extends hasChild_Scope<?>>> mods() { return null; }

	protected ArrayList<Scope<? extends hasChild_Scope<?>>> imps = null;
	protected Boolean imps_computed = false;
	public ArrayList<Scope<? extends hasChild_Scope<?>>> imps() { return null; }

	protected ArrayList<Pair<Ref<? extends hasChild_Ref<?>>, ArrayList<Scope<? extends hasChild_Scope<?>>>>> binds = null;
	protected Boolean binds_computed = false;
	public ArrayList<Pair<Ref<? extends hasChild_Ref<?>>, ArrayList<Scope<? extends hasChild_Scope<?>>>>> binds() { return null; }
}

class dclsCons<T extends hasChild_Dcls<T>> extends Dcls<T> implements hasChild_Dcl<dclsCons<T>>, hasChild_Dcls<dclsCons<T>> {

/* CHILD FIELDS */
private Dcl<dclsCons<T>> d;
private Dcls<dclsCons<T>> ds;
/* CONSTRUCTOR */
public dclsCons(Dcl<dclsCons<T>> d, Dcls<dclsCons<T>> ds) {
this.d = d;
this.ds = ds;
this.d.setParent(this, 0);
this.ds.setParent(this, 1);
}
/* SETTING CHILD INHS */
public Scope<? extends hasChild_Scope<?>> scope(int childId) {
// d.scope
if (childId == 0) {

return this.scope();
}
// ds.scope
if (childId == 1) {

return this.scope();
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
public ArrayList<Scope<? extends hasChild_Scope<?>>> vars() {
if (this.vars_computed) return this.vars;ArrayList<Scope<? extends hasChild_Scope<?>>>combList_0 = new ArrayList<>();
combList_0.addAll(d.vars());
combList_0.addAll(ds.vars());
this.vars = combList_0;
this.vars_computed = true;
return this.vars;
}
public ArrayList<Scope<? extends hasChild_Scope<?>>> mods() {
if (this.mods_computed) return this.mods;ArrayList<Scope<? extends hasChild_Scope<?>>>combList_1 = new ArrayList<>();
combList_1.addAll(d.mods());
combList_1.addAll(ds.mods());
this.mods = combList_1;
this.mods_computed = true;
return this.mods;
}
public ArrayList<Scope<? extends hasChild_Scope<?>>> imps() {
if (this.imps_computed) return this.imps;ArrayList<Scope<? extends hasChild_Scope<?>>>combList_2 = new ArrayList<>();
combList_2.addAll(d.imps());
combList_2.addAll(ds.imps());
this.imps = combList_2;
this.imps_computed = true;
return this.imps;
}
public ArrayList<Pair<Ref<? extends hasChild_Ref<?>>, ArrayList<Scope<? extends hasChild_Scope<?>>>>> binds() {
if (this.binds_computed) return this.binds;ArrayList<Pair<Ref<? extends hasChild_Ref<?>>, ArrayList<Scope<? extends hasChild_Scope<?>>>>>combList_3 = new ArrayList<>();
combList_3.addAll(d.binds());
combList_3.addAll(ds.binds());
this.binds = combList_3;
this.binds_computed = true;
return this.binds;
}

}

class dclsNil<T extends hasChild_Dcls<T>> extends Dcls<T> {

/* CONSTRUCTOR */
public dclsNil() {

}
/* GETTING OWN INHS */
public Scope<? extends hasChild_Scope<?>> scope() {
if (this.scope_computed) return this.scope;
this.scope = this.parent.scope(this.childId);
this.scope_computed = true;
return this.scope;
}
/* SYNTHESIZED ATTRS */
public ArrayList<Scope<? extends hasChild_Scope<?>>> vars() {
if (this.vars_computed) return this.vars;
this.vars = new ArrayList<>();
this.vars_computed = true;
return this.vars;
}
public ArrayList<Scope<? extends hasChild_Scope<?>>> mods() {
if (this.mods_computed) return this.mods;
this.mods = new ArrayList<>();
this.mods_computed = true;
return this.mods;
}
public ArrayList<Scope<? extends hasChild_Scope<?>>> imps() {
if (this.imps_computed) return this.imps;
this.imps = new ArrayList<>();
this.imps_computed = true;
return this.imps;
}
public ArrayList<Pair<Ref<? extends hasChild_Ref<?>>, ArrayList<Scope<? extends hasChild_Scope<?>>>>> binds() {
if (this.binds_computed) return this.binds;
this.binds = new ArrayList<>();
this.binds_computed = true;
return this.binds;
}

}