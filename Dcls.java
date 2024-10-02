abstract class Dcls<T extends hasChild_Dcls<T>> extends TreeNode<T> {
	protected Scope<? extends haschild_Scope<?>> scope = null;
	protected Boolean scope_computed = false;
	public Scope<? extends haschild_Scope<?>> scope() { return null; }

	protected ArrayList<Scope<? extends hasChild_Scope<?>>> vars = null;
	protected Boolean vars_computed = false;
	public ArrayList<Scope<? extends hasChild_Scope<?>>> vars() { return null; }
}

class dclsCons<T extends hasChild_Dcls<T>> extends Dcls<T> implements haschild_Dcl<dclsCons<T>>, haschild_Dcls<dclsCons<T>>, haschild_Scope<dclsCons<T>>, haschild_Scope<dclsCons<T>> {
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
/* LOCALS */
private Scope<dclsCons<T>> s = null;
private Boolean s_computed = false;
public Scope<dclsCons<T>> s() {
if (this.s_computed) return this.s;
this.s = TODO
this.s.setParent(this, 2);
this.s_computed = true;
return this.s;
}
private Scope<dclsCons<T>> s2 = null;
private Boolean s2_computed = false;
public Scope<dclsCons<T>> s2() {
if (this.s2_computed) return this.s2;
this.s2 = TODO
this.s2.setParent(this, 3);
this.s2_computed = true;
return this.s2;
}
private Integer i = null;
private Boolean i_computed = false;
public Integer i() {
if (this.i_computed) return this.i;
this.i = TODO
this.i_computed = true;
return this.i;
}
/* SETTING CHILD INHS */
public Scope<? extends hasChild_Scope<?>> scope(int childId) {
// d.scope
if (childId == 0) {
return TODO
}
// ds.scope
if (childId == 1) {
return TODO
}return null;
}
}

class dclsNil<T extends hasChild_Dcls<T>> extends Dcls<T> {
/* CHILD FIELDS */

/* CONSTRUCTOR */
public dclsNil() {

}
/* LOCALS */

/* SETTING CHILD INHS */

}