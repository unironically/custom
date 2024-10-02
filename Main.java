abstract class Main<T extends hasChild_Main<T>> extends TreeNode<T> {

}

class main<T extends hasChild_Main<T>> extends Main<T> implements haschild_Dcls<main<T>>, haschild_Scope<main<T>> {
/* CHILD FIELDS */
private Dcls<main<T>> ds;
private String name;
/* CONSTRUCTOR */
public main(Dcls<main<T>> ds, String name) {
this.ds = ds;
this.name = name;
this.ds.setParent(this, 0);
}
/* LOCALS */
private Scope<main<T>> s = null;
private Boolean s_computed = false;
public Scope<main<T>> s() {
if (this.s_computed) return this.s;
this.s = TODO
this.s.setParent(this, 1);
this.s_computed = true;
return this.s;
}
/* SETTING CHILD INHS */
public Scope<? extends hasChild_Scope<?>> scope(int childId) {
// ds.scope
if (childId == 0) {
return TODO
}return null;
}
}