package lm;
import lm.*;
import java.util.*;
abstract class Dcl<T extends hasChild_Dcl<T>> extends TreeNode<T> {
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

class dclMod<T extends hasChild_Dcl<T>> extends Dcl<T> implements hasChild_Dcls<dclMod<T>>, hasChild_Scope<dclMod<T>> {

/* CHILD FIELDS */
private String id;
private Dcls<dclMod<T>> ds;
/* CONSTRUCTOR */
public dclMod(String id, Dcls<dclMod<T>> ds) {
this.id = id;
this.ds = ds;
this.ds.setParent(this, 0);
}
/* LOCALS */
private Scope<dclMod<T>> s = null;
private Boolean s_computed = false;
public Scope<dclMod<T>> s() {
if (this.s_computed) return this.s;
this.s = new mkMod<>(id);
this.s.setParent(this, 1);
this.s_computed = true;
return this.s;
}
/* SETTING CHILD INHS */
public ArrayList<Scope<? extends hasChild_Scope<?>>> var(int childId) {
// s.var
if (childId == 1) {

return ds.vars();
}return null;
}
public ArrayList<Scope<? extends hasChild_Scope<?>>> mod(int childId) {
// s.mod
if (childId == 1) {

return ds.mods();
}return null;
}
public ArrayList<Scope<? extends hasChild_Scope<?>>> imp(int childId) {
// s.imp
if (childId == 1) {

return ds.imps();
}return null;
}
public ArrayList<Scope<? extends hasChild_Scope<?>>> lex(int childId) {
// s.lex
if (childId == 1) {
ArrayList<Scope<? extends hasChild_Scope<?>>> combList_4 = new ArrayList<>();
combList_4.add(this.scope());
return combList_4;
}return null;
}
public Scope<? extends hasChild_Scope<?>> scope(int childId) {
// ds.scope
if (childId == 0) {

return s();
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
if (this.vars_computed) return this.vars;
this.vars = new ArrayList<>();
this.vars_computed = true;
return this.vars;
}
public ArrayList<Scope<? extends hasChild_Scope<?>>> mods() {
if (this.mods_computed) return this.mods;ArrayList<Scope<? extends hasChild_Scope<?>>> combList_5 = new ArrayList<>();
combList_5.add(s());
this.mods = combList_5;
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
this.binds = ds.binds();
this.binds_computed = true;
return this.binds;
}

}

class dclBind<T extends hasChild_Dcl<T>> extends Dcl<T> implements hasChild_Bind<dclBind<T>> {

/* CHILD FIELDS */
private Bind<dclBind<T>> b;
/* CONSTRUCTOR */
public dclBind(Bind<dclBind<T>> b) {
this.b = b;
this.b.setParent(this, 0);
}
/* SETTING CHILD INHS */
public Scope<? extends hasChild_Scope<?>> scope(int childId) {
// b.scope
if (childId == 0) {

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
if (this.vars_computed) return this.vars;
this.vars = b.vars();
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
this.binds = b.binds();
this.binds_computed = true;
return this.binds;
}

}

class dclImp<T extends hasChild_Dcl<T>> extends Dcl<T> implements hasChild_ModRef<dclImp<T>> {

/* CHILD FIELDS */
private ModRef<dclImp<T>> r;
/* CONSTRUCTOR */
public dclImp(ModRef<dclImp<T>> r) {
this.r = r;
this.r.setParent(this, 0);
}
/* SETTING CHILD INHS */
public Scope<? extends hasChild_Scope<?>> scope(int childId) {
// r.scope
if (childId == 0) {

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
this.imps = r.imps();
this.imps_computed = true;
return this.imps;
}
public ArrayList<Pair<Ref<? extends hasChild_Ref<?>>, ArrayList<Scope<? extends hasChild_Scope<?>>>>> binds() {
if (this.binds_computed) return this.binds;
this.binds = r.binds();
this.binds_computed = true;
return this.binds;
}

}