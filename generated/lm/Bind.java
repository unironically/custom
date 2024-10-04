package lm;
import lm.*;
import java.util.*;
abstract class Bind<T extends hasChild_Bind<T>> extends TreeNode<T> {
	protected Scope<? extends hasChild_Scope<?>> scope = null;
	protected Boolean scope_computed = false;
	public Scope<? extends hasChild_Scope<?>> scope() { return null; }

	protected ArrayList<Scope<? extends hasChild_Scope<?>>> vars = null;
	protected Boolean vars_computed = false;
	public ArrayList<Scope<? extends hasChild_Scope<?>>> vars() { return null; }

	protected Type<? extends hasChild_Type<?>> type = null;
	protected Boolean type_computed = false;
	public Type<? extends hasChild_Type<?>> type() { return null; }

	protected ArrayList<Pair<Ref<? extends hasChild_Ref<?>>, ArrayList<Scope<? extends hasChild_Scope<?>>>>> binds = null;
	protected Boolean binds_computed = false;
	public ArrayList<Pair<Ref<? extends hasChild_Ref<?>>, ArrayList<Scope<? extends hasChild_Scope<?>>>>> binds() { return null; }
}

class bnd<T extends hasChild_Bind<T>> extends Bind<T> implements hasChild_Type<bnd<T>>, hasChild_Exp<bnd<T>>, hasChild_Scope<bnd<T>> {

/* CHILD FIELDS */
private String id;
private Type<bnd<T>> t;
private Exp<bnd<T>> e;
/* CONSTRUCTOR */
public bnd(String id, Type<bnd<T>> t, Exp<bnd<T>> e) {
this.id = id;
this.t = t;
this.e = e;
this.t.setParent(this, 0);
this.e.setParent(this, 1);
}
/* LOCALS */
private Scope<bnd<T>> d = null;
private Boolean d_computed = false;
public Scope<bnd<T>> d() {
if (this.d_computed) return this.d;
this.d = new mkVar<>(id, t);
this.d.setParent(this, 2);
this.d_computed = true;
return this.d;
}
/* SETTING CHILD INHS */
public ArrayList<Scope<? extends hasChild_Scope<?>>> var(int childId) {
// d.var
if (childId == 2) {

return new ArrayList<>();
}return null;
}
public ArrayList<Scope<? extends hasChild_Scope<?>>> mod(int childId) {
// d.mod
if (childId == 2) {

return new ArrayList<>();
}return null;
}
public ArrayList<Scope<? extends hasChild_Scope<?>>> imp(int childId) {
// d.imp
if (childId == 2) {

return new ArrayList<>();
}return null;
}
public ArrayList<Scope<? extends hasChild_Scope<?>>> lex(int childId) {
// d.lex
if (childId == 2) {

return new ArrayList<>();
}return null;
}
public Scope<? extends hasChild_Scope<?>> scope(int childId) {
// e.scope
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
if (this.vars_computed) return this.vars;ArrayList<Scope<? extends hasChild_Scope<?>>> combList_6 = new ArrayList<>();
combList_6.add(d());
this.vars = combList_6;
this.vars_computed = true;
return this.vars;
}
public Type<? extends hasChild_Type<?>> type() {
if (this.type_computed) return this.type;
this.type = e.type();
this.type_computed = true;
return this.type;
}
public ArrayList<Pair<Ref<? extends hasChild_Ref<?>>, ArrayList<Scope<? extends hasChild_Scope<?>>>>> binds() {
if (this.binds_computed) return this.binds;
this.binds = e.binds();
this.binds_computed = true;
return this.binds;
}

}