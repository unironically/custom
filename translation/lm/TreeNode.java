package lm;

abstract class TreeNode<T> {

  protected Boolean trace = false;
  protected Boolean scopeTrace = true;

  protected int childId;

  protected T parent;
  
  public void setParent(T parent, int childId) {
    this.parent = parent;
    this.childId = childId;
  }

}