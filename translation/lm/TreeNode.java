package lm;

import java.util.Stack;

abstract class TreeNode<T> {

  public static Boolean IN_CIRCLE = false;
  public static Boolean CHANGE = false;
  public static Stack<Boolean> STACK = new Stack<>();

  protected Boolean trace = false;
  protected Boolean scopeTrace = false;

  protected int childId;

  protected T parent;

  public void setParent(T parent, int childId) {
    this.parent = parent;
    this.childId = childId;
  }

}