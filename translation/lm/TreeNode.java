package lm;

import java.util.Stack;

abstract class TreeNode<T> {

  public static Boolean IN_CIRCLE = false;
  public static Boolean CHANGE = false;
  public static Stack<Boolean> STACK = new Stack<>();
  public static Boolean READY = false;

  protected Boolean trace = false;
  protected Boolean scopeTrace = true;
  
  public static Integer tabWidth = 0;
  public static void tabIncrease() { tabWidth++; }
  public static void tabDecrease() { tabWidth--; }
  public static String tab() {
    String tabbed = "";
    for (int i = 0; i < tabWidth; i++) tabbed += "    ";
    return tabbed;
  }

  protected int childId;

  protected T parent;

  public void setParent(T parent, int childId) {
    this.parent = parent;
    this.childId = childId;
  }

}