package lm;

abstract class TreeNode {

  protected TreeNode parent = null;
  protected int childId;

  protected void setParent(TreeNode parent, int childId) {
    this.parent = parent;
    this.childId = childId;
  }

}