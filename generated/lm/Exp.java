package lm;
import lm.*;
import java.util.*;
abstract class Exp<T extends hasChild_Exp<T>> extends TreeNode<T> {
	protected Scope<? extends hasChild_Scope<?>> scope = null;
	protected Boolean scope_computed = false;
	public Scope<? extends hasChild_Scope<?>> scope() { return null; }

	protected Type<? extends hasChild_Type<?>> type = null;
	protected Boolean type_computed = false;
	public Type<? extends hasChild_Type<?>> type() { return null; }

	protected ArrayList<Pair<Ref<? extends hasChild_Ref<?>>, ArrayList<Scope<? extends hasChild_Scope<?>>>>> binds = null;
	protected Boolean binds_computed = false;
	public ArrayList<Pair<Ref<? extends hasChild_Ref<?>>, ArrayList<Scope<? extends hasChild_Scope<?>>>>> binds() { return null; }
}

class expRef<T extends hasChild_Exp<T>> extends Exp<T> implements hasChild_VarRef<expRef<T>> {

/* CHILD FIELDS */
private VarRef<expRef<T>> r;
/* CONSTRUCTOR */
public expRef(VarRef<expRef<T>> r) {
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
public Type<? extends hasChild_Type<?>> type() {
if (this.type_computed) return this.type;
this.type = (r.res().size() > 0) ? r.res().get(0).datum().type() : new errType<>();
this.type_computed = true;
return this.type;
}
public ArrayList<Pair<Ref<? extends hasChild_Ref<?>>, ArrayList<Scope<? extends hasChild_Scope<?>>>>> binds() {
if (this.binds_computed) return this.binds;
this.binds = r.binds();
this.binds_computed = true;
return this.binds;
}

}

class expInt<T extends hasChild_Exp<T>> extends Exp<T> {

/* CHILD FIELDS */
private Integer i;
/* CONSTRUCTOR */
public expInt(Integer i) {
this.i = i;
}
/* GETTING OWN INHS */
public Scope<? extends hasChild_Scope<?>> scope() {
if (this.scope_computed) return this.scope;
this.scope = this.parent.scope(this.childId);
this.scope_computed = true;
return this.scope;
}
/* SYNTHESIZED ATTRS */
public Type<? extends hasChild_Type<?>> type() {
if (this.type_computed) return this.type;
this.type = new intType<>();
this.type_computed = true;
return this.type;
}
public ArrayList<Pair<Ref<? extends hasChild_Ref<?>>, ArrayList<Scope<? extends hasChild_Scope<?>>>>> binds() {
if (this.binds_computed) return this.binds;
this.binds = new ArrayList<>();
this.binds_computed = true;
return this.binds;
}

}