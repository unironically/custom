abstract class Scope<T extends hasChild_Scope<T>> extends TreeNode<T> {
	protected ArrayList<Scope<? extends hasChild_Scope<?>>> lex = null;
	protected Boolean lex_computed = false;
	public ArrayList<Scope<? extends hasChild_Scope<?>>> lex() { return null; }

	protected ArrayList<Scope<? extends hasChild_Scope<?>>> vars = null;
	protected Boolean vars_computed = false;
	public ArrayList<Scope<? extends hasChild_Scope<?>>> vars() { return null; }
}

class mkScope<T extends hasChild_Scope<T>> extends Scope<T> {
/* CHILD FIELDS */

/* CONSTRUCTOR */
public mkScope() {

}
/* LOCALS */

/* SETTING CHILD INHS */

}