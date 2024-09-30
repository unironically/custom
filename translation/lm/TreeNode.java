package lm;

abstract class TreeNode {

  protected Boolean trace = false;
  protected Boolean scopeTrace = true;

  protected TreeNode parent;
  protected int childId;

  protected void setParent(TreeNode parent, int childId) {
    this.parent = parent;
    this.childId = childId;
    this.trace = true;
  }

}