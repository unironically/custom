package lm;
import lm.*;
import java.util.*;
abstract class Main<T extends hasChild_Main<T>> extends TreeNode<T> {
	protected ArrayList<Pair<Ref<? extends hasChild_Ref<?>>, ArrayList<Scope<? extends hasChild_Scope<?>>>>> binds = null;
	protected Boolean binds_computed = false;
	public ArrayList<Pair<Ref<? extends hasChild_Ref<?>>, ArrayList<Scope<? extends hasChild_Scope<?>>>>> binds() { return null; }
}

class main<T extends hasChild_Main<T>> extends Main<T> implements hasChild_Dcls<main<T>>, hasChild_Scope<main<T>> {

/* CHILD FIELDS */
private Dcls<main<T>> ds;
/* CONSTRUCTOR */
public main(Dcls<main<T>> ds) {
this.ds = ds;
this.ds.setParent(this, 0);
}
/* LOCALS */
private Scope<main<T>> s = null;
private Boolean s_computed = false;
public Scope<main<T>> s() {
if (this.s_computed) return this.s;
this.s = new mkScope<>();
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

return new ArrayList<>();
}return null;
}
public Scope<? extends hasChild_Scope<?>> scope(int childId) {
// ds.scope
if (childId == 0) {

return s();
}return null;
}
/* SYNTHESIZED ATTRS */
public ArrayList<Pair<Ref<? extends hasChild_Ref<?>>, ArrayList<Scope<? extends hasChild_Scope<?>>>>> binds() {
if (this.binds_computed) return this.binds;
this.binds = ds.binds();
this.binds_computed = true;
return this.binds;
}

}