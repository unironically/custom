package lm;

abstract class TreeNode<T> {

  protected Boolean trace = false;
  protected Boolean scopeTrace = false;

  protected int childId;

  protected T parent;

  public void setParent(T parent, int childId) {
    this.parent = parent;
    this.childId = childId;
  }

}