package lm;

import java.util.ArrayList;

abstract class DFA<T> extends TreeNode<T> {
  public ArrayList<Scope<? extends haschild_Scope<?>>> 
    decls(Ref<? extends haschild_Ref<?>> r, Scope<? extends haschild_Scope<?>> s) { return null; }
}

class varDFA<T> extends DFA<T>
implements haschild_DFAState<varDFA<T>> {

  /* Children
   * 0: local stateLex:dfaState
   * 1: local stateImp:dfaState
   * 2: local stateFinal:dfaStateFinal
   * 3: local stateSink:dfaStateSink
   */

  /* LOCALS */

  // local stateLex:DFAState = dfaState()
  private DFAState<varDFA<T>> stateLex = null;
  private Boolean stateLex_computed = false;
  private Boolean stateLex_visited = false;
  public DFAState<varDFA<T>> stateLex() {
    if (this.stateLex_computed) return this.stateLex;
    Boolean interrupted_circle = false;
    if (!this.stateLex_visited){
      this.stateLex_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.stateLex = new dfaState<varDFA<T>>();
      this.stateLex.setParent(this, 0);
      this.stateLex_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.stateLex_visited = false;
      return this.stateLex;
    }
    throw new RuntimeException("Circular definition of varDFA.stateLex");
  }

  // local stateImp:DFAState = dfaState()
  private DFAState<varDFA<T>> stateImp = null;
  private Boolean stateImp_computed = false;
  private Boolean stateImp_visited = false;
  public DFAState<varDFA<T>> stateImp() {
    if (this.stateImp_computed) return this.stateImp;
    Boolean interrupted_circle = false;
    if (!this.stateImp_visited){
      this.stateImp_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.stateImp = new dfaState<varDFA<T>>();
      this.stateImp.setParent(this, 1);
      this.stateImp_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.stateImp_visited = false;
      return this.stateImp;
    }
    throw new RuntimeException("Circular definition of varDFA.stateImp");
  }

  // local stateFinal:DFAState = dfaStateFinal()
  private DFAState<varDFA<T>> stateFinal = null;
  private Boolean stateFinal_computed = false;
  private Boolean stateFinal_visited = false;
  public DFAState<varDFA<T>> stateFinal() {
    if (this.stateFinal_computed) return this.stateFinal;
    Boolean interrupted_circle = false;
    if (!this.stateFinal_visited){
      this.stateFinal_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.stateFinal = new dfaStateFinal<varDFA<T>>();
      this.stateFinal.setParent(this, 2);
      this.stateFinal_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.stateFinal_visited = false;
      return this.stateFinal;
    }
    throw new RuntimeException("Circular definition of varDFA.stateFinal");
  }

  // local stateSink:DFAState = dfaStateSink()
  private DFAState<varDFA<T>> stateSink = null;
  private Boolean stateSink_computed = false;
  private Boolean stateSink_visited = false;
  public DFAState<varDFA<T>> stateSink() {
    if (this.stateSink_computed) return this.stateSink;
    Boolean interrupted_circle = false;
    if (!this.stateSink_visited){
      this.stateSink_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.stateSink = new dfaStateSink<varDFA<T>>();
      this.stateSink.setParent(this, 3);
      this.stateSink_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.stateSink_visited = false;
      return this.stateSink;
    }
    throw new RuntimeException("Circular definition of varDFA.stateSink");
  }

  /* INHERITED ATTRIBUTES PUSHED DOWN */

  /* stateLex.lexT   = state0
   * stateImp.lexT   = stateSink
   * stateFinal.lexT = stateSink
   * stateSink.lexT  = stateSink
   */
  public DFAState<varDFA<T>> lexT(int childId) {
    if (childId == 0) {
      return this.stateLex();
    }
    else if (childId == 1) {
      return this.stateSink();
    }
    else if (childId == 2) {
      return this.stateSink();
    }
    else if (childId == 3) {
      return this.stateSink();
    }
    return null;
  }

  /* stateLex.varT   = stateFinal
   * stateImp.varT   = stateFinal
   * stateFinal.varT = stateSink
   * stateSink.varT  = stateSink
   */
  public DFAState<varDFA<T>> varT(int childId) {
    if (childId == 0) {
      return this.stateFinal();
    }
    else if (childId == 1) {
      return this.stateFinal();
    }
    else if (childId == 2) {
      return this.stateSink();
    }
    else if (childId == 3) {
      return this.stateSink();
    }
    return null;
  }

  /* stateLex.modT   = stateSink
   * stateImp.modT   = stateSink
   * stateFinal.modT = stateSink
   * stateSink.modT  = stateSink
   */
  public DFAState<varDFA<T>> modT(int childId) {
    if (childId == 0) {
      return this.stateSink();
    }
    else if (childId == 1) {
      return this.stateSink();
    }
    else if (childId == 2) {
      return this.stateSink();
    }
    else if (childId == 3) {
      return this.stateSink();
    }
    return null;
  }

  /* stateLex.impT   = stateImp
   * stateImp.impT   = stateSink
   * stateFinal.impT = stateSink
   * stateSink.impT  = stateSink
   */
  public DFAState<varDFA<T>> impT(int childId) {
    if (childId == 0) {
      return this.stateImp();
    }
    else if (childId == 1) {
      return this.stateSink();
    }
    else if (childId == 2) {
      return this.stateSink();
    }
    else if (childId == 3) {
      return this.stateSink();
    }
    return null;
  }

  /* SYNTHESIZED ATTRIBUTES */

  public ArrayList<Scope<? extends haschild_Scope<?>>> 
  decls(Ref<? extends haschild_Ref<?>> r, Scope<? extends haschild_Scope<?>> s){
    return this.stateLex().decls(r, s);
  }

}

class modDFA<T> extends DFA<T>
implements haschild_DFAState<modDFA<T>> {

  /* Children
   * 0: local stateLex:dfaState
   * 1: local stateImp:dfaState
   * 2: local stateFinal:dfaStateFinal
   * 3: local stateSink:dfaStateSink
   */

  /* LOCALS */

  // local stateLex:DFAState = dfaState()
  private DFAState<modDFA<T>> stateLex = null;
  private Boolean stateLex_computed = false;
  private Boolean stateLex_visited = false;
  public DFAState<modDFA<T>> stateLex() {
    if (this.stateLex_computed) return this.stateLex;
    Boolean interrupted_circle = false;
    if (!this.stateLex_visited){
      this.stateLex_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.stateLex = new dfaStateMod<modDFA<T>>();
      this.stateLex.setParent(this, 0);
      this.stateLex_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.stateLex_visited = false;
      return this.stateLex;
    }
    throw new RuntimeException("Circular definition of modDFA.stateLex");
  }

  // local stateImp:DFAState = dfaState()
  private DFAState<modDFA<T>> stateImp = null;
  private Boolean stateImp_computed = false;
  private Boolean stateImp_visited = false;
  public DFAState<modDFA<T>> stateImp() {
    if (this.stateImp_computed) return this.stateImp;
    Boolean interrupted_circle = false;
    if (!this.stateImp_visited){
      this.stateImp_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.stateImp = new dfaStateMod<modDFA<T>>();
      this.stateImp.setParent(this, 1);
      this.stateImp_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.stateImp_visited = false;
      return this.stateImp;
    }
    throw new RuntimeException("Circular definition of modDFA.stateImp");
  }

  // local stateFinal:DFAState = dfaStateFinal()
  private DFAState<modDFA<T>> stateFinal = null;
  private Boolean stateFinal_computed = false;
  private Boolean stateFinal_visited = false;
  public DFAState<modDFA<T>> stateFinal() {
    if (this.stateFinal_computed) return this.stateFinal;
    Boolean interrupted_circle = false;
    if (!this.stateFinal_visited){
      this.stateFinal_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.stateFinal = new dfaStateFinal<modDFA<T>>();
      this.stateFinal.setParent(this, 2);
      this.stateFinal_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.stateFinal_visited = false;
      return this.stateFinal;
    }
    throw new RuntimeException("Circular definition of modDFA.stateFinal");
  }

  // local stateSink:DFAState = dfaStateSink()
  private DFAState<modDFA<T>> stateSink = null;
  private Boolean stateSink_computed = false;
  private Boolean stateSink_visited = false;
  public DFAState<modDFA<T>> stateSink() {
    if (this.stateSink_computed) return this.stateSink;
    Boolean interrupted_circle = false;
    if (!this.stateSink_visited){
      this.stateSink_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.stateSink = new dfaStateSink<modDFA<T>>();
      this.stateSink.setParent(this, 3);
      this.stateSink_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.stateSink_visited = false;
      return this.stateSink;
    }
    throw new RuntimeException("Circular definition of modDFA.stateSink");
  }

  /* INHERITED ATTRIBUTES PUSHED DOWN */

  /* stateLex.lexT   = state0
   * stateImp.lexT   = stateSink
   * stateFinal.lexT = stateSink
   * stateSink.lexT  = stateSink
   */
  public DFAState<modDFA<T>> lexT(int childId) {
    if (childId == 0) {
      return this.stateLex();
    }
    else if (childId == 1) {
      return this.stateSink();
    }
    else if (childId == 2) {
      return this.stateFinal();
    }
    else if (childId == 3) {
      return this.stateSink();
    }
    return null;
  }

  /* stateLex.varT   = stateSink
   * stateImp.varT   = stateSink
   * stateFinal.varT = stateSink
   * stateSink.varT  = stateSink
   */
  public DFAState<modDFA<T>> varT(int childId) {
    if (childId == 0) {
      return this.stateFinal();
    }
    else if (childId == 1) {
      return this.stateFinal();
    }
    else if (childId == 2) {
      return this.stateSink();
    }
    else if (childId == 3) {
      return this.stateSink();
    }
    return null;
  }

  /* stateLex.modT   = stateFinal
   * stateImp.modT   = stateFinal
   * stateFinal.modT = stateSink
   * stateSink.modT  = stateSink
   */
  public DFAState<modDFA<T>> modT(int childId) {
    if (childId == 0) {
      return this.stateFinal();
    }
    else if (childId == 1) {
      return this.stateFinal();
    }
    else if (childId == 2) {
      return this.stateSink();
    }
    else if (childId == 3) {
      return this.stateSink();
    }
    return null;
  }

  /* stateLex.impT   = stateImp
   * stateImp.impT   = stateSink
   * stateFinal.impT = stateSink
   * stateSink.impT  = stateSink
   */
  public DFAState<modDFA<T>> impT(int childId) {
    if (childId == 0) {
      return this.stateImp();
    }
    else if (childId == 1) {
      return this.stateSink();
    }
    else if (childId == 2) {
      return this.stateSink();
    }
    else if (childId == 3) {
      return this.stateSink();
    }
    return null;
  }

  /* SYNTHESIZED ATTRIBUTES */

  public ArrayList<Scope<? extends haschild_Scope<?>>> 
  decls(Ref<? extends haschild_Ref<?>> r, Scope<? extends haschild_Scope<?>> s) { 
    return this.stateLex().decls(r, s);
  }

}


////////////////////////////////////////////////////////////////////////////////

abstract class DFAState<T extends haschild_DFAState<T>>
extends TreeNode<T> {

  protected DFAState<T> lexT = null;
  protected Boolean lexT_computed = false;
  protected Boolean lexT_visited = false;

  protected DFAState<T> varT = null;
  protected Boolean varT_computed = false;
  protected Boolean varT_visited = false;

  protected DFAState<T> modT = null;
  protected Boolean modT_computed = false;
  protected Boolean modT_visited = false;

  protected DFAState<T> impT = null;
  protected Boolean impT_computed = false;
  protected Boolean impT_visited = false;

  public ArrayList<Scope<? extends haschild_Scope<?>>> 
    decls(Ref<? extends haschild_Ref<?>> r, Scope<? extends haschild_Scope<?>> s) { return null; }

  protected String pp = null;
  protected Boolean pp_computed = false;
  public String pp() { return null; }
}

class dfaState<T extends haschild_DFAState<T>>
extends DFAState<T> {

  public dfaState() {}

  /* GETTING OWN INHERITED ATTRIBUTES */

  public DFAState<T> lexT() {
    if (this.lexT_computed) return this.lexT;
    Boolean interrupted_circle = false;
    if (!this.lexT_visited) {
      this.lexT_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.lexT = this.parent.lexT(this.childId);
      this.lexT_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.lexT_visited = false;
      return this.lexT;
    }
    throw new RuntimeException("Circular definition of dfaState.lexT");
  }

  public DFAState<T> varT() {
    if (this.varT_computed) return this.varT;
    Boolean interrupted_circle = false;
    if (!this.varT_visited) {
      this.varT_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.varT = this.parent.varT(this.childId);
      this.varT_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.varT_visited = false;
      return this.varT;
    }
    throw new RuntimeException("Circular definition of dfaState.varT");
  }

  public DFAState<T> modT() {
    if (this.modT_computed) return this.modT;
    Boolean interrupted_circle = false;
    if (!this.modT_visited) {
      this.modT_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.modT = this.parent.modT(this.childId);
      this.modT_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.modT_visited = false;
      return this.modT;
    }
    throw new RuntimeException("Circular definition of dfaState.modT");
  }

  public DFAState<T> impT() {
    if (this.impT_computed) return this.impT;
    Boolean interrupted_circle = false;
    if (!this.impT_visited) {
      this.impT_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.impT = this.parent.impT(this.childId);
      this.impT_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.impT_visited = false;
      return this.impT;
    }
    throw new RuntimeException("Circular definition of dfaState.impT");
  }

  /* SYNTHESIZED ATTRIBUTES */

  public String pp() {
    if (this.pp_computed) return this.pp;
    this.pp = "dfaState_" + Integer.toString(this.hashCode());
    this.pp_computed = true;
    return this.pp;
  }

  public ArrayList<Scope<? extends haschild_Scope<?>>> 
  decls(Ref<? extends haschild_Ref<?>> r, Scope<? extends haschild_Scope<?>> s) { 

    ArrayList<Scope<? extends haschild_Scope<?>>> varRes = 
      new ArrayList<Scope<? extends haschild_Scope<?>>>();
    for (Scope<? extends haschild_Scope<?>> sVar: s.var()) {
      if (scopeTrace)
      System.out.println("In resolving " + r.pp() + " in state " + this.pp() + ", traversing a VAR " + "edge from " + s.pp() + " to " + sVar.pp() + ", and traversing DFA to state " + this.varT().pp());
      varRes.addAll(this.varT().decls(r, sVar));
    }

    ArrayList<Scope<? extends haschild_Scope<?>>> modRes = 
      new ArrayList<Scope<? extends haschild_Scope<?>>>();
    for (Scope<? extends haschild_Scope<?>> sMod: s.mod()) {
      if (scopeTrace)
      System.out.println("In resolving " + r.pp() + " in state " + this.pp() + ", traversing a MOD " + "edge from " + s.pp() + " to " + sMod.pp() + ", and traversing DFA to state " + this.modT().pp());
      modRes.addAll(this.modT().decls(r, sMod));
    }

    ArrayList<Scope<? extends haschild_Scope<?>>> impRes = 
      new ArrayList<Scope<? extends haschild_Scope<?>>>();
    for (Scope<? extends haschild_Scope<?>> sImp: s.imp()) {
      if (scopeTrace)
      System.out.println("In resolving " + r.pp() + " in state " + this.pp() + ", traversing a IMP " + "edge from " + s.pp() + " to " + sImp.pp() + ", and traversing DFA to state " + this.impT().pp());
      impRes.addAll(this.impT().decls(r, sImp));
    }

    ArrayList<Scope<? extends haschild_Scope<?>>> lexRes = 
      new ArrayList<Scope<? extends haschild_Scope<?>>>();
    for (Scope<? extends haschild_Scope<?>> sLex: s.lex()) {
      if (scopeTrace)
      System.out.println("In resolving " + r.pp() + " in state " + this.pp() + ", traversing a LEX " + "edge from " + s.pp() + " to " + sLex.pp() + ", and traversing DFA to state " + this.lexT().pp());
      lexRes.addAll(this.lexT().decls(r, sLex));
    }

    return 
      (!varRes.isEmpty()) ? varRes :
      (!modRes.isEmpty()) ? modRes :
      (!impRes.isEmpty()) ? impRes :
      lexRes;
  
  }

}

class dfaStateFinal<T extends haschild_DFAState<T>>
extends DFAState<T> {

  public dfaStateFinal() {}

  /* GETTING OWN INHERITED ATTRIBUTES */

  public DFAState<T> lexT() {
    if (this.lexT_computed) return this.lexT;
    Boolean interrupted_circle = false;
    if (!this.lexT_visited) {
      this.lexT_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.lexT = this.parent.lexT(this.childId);
      this.lexT_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.lexT_visited = false;
      return this.lexT;
    }
    throw new RuntimeException("Circular definition of dfaStateFinal.lexT");
  }

  public DFAState<T> varT() {
    if (this.varT_computed) return this.varT;
    Boolean interrupted_circle = false;
    if (!this.varT_visited) {
      this.varT_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.varT = this.parent.varT(this.childId);
      this.varT_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.varT_visited = false;
      return this.varT;
    }
    throw new RuntimeException("Circular definition of dfaStateFinal.varT");
  }

  public DFAState<T> modT() {
    if (this.modT_computed) return this.modT;
    Boolean interrupted_circle = false;
    if (!this.modT_visited) {
      this.modT_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.modT = this.parent.modT(this.childId);
      this.modT_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.modT_visited = false;
      return this.modT;
    }
    throw new RuntimeException("Circular definition of dfaStateFinal.modT");
  }

  public DFAState<T> impT() {
    if (this.impT_computed) return this.impT;
    Boolean interrupted_circle = false;
    if (!this.impT_visited) {
      this.impT_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.impT = this.parent.impT(this.childId);
      this.impT_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.impT_visited = false;
      return this.impT;
    }
    throw new RuntimeException("Circular definition of dfaStateFinal.impT");
  }

  /* SYNTHESIZED ATTRIBUTES */

  public String pp() {
    if (this.pp_computed) return this.pp;
    this.pp = "dfaStateFinal_" + Integer.toString(this.hashCode());
    this.pp_computed = true;
    return this.pp;
  }

  public ArrayList<Scope<? extends haschild_Scope<?>>> 
  decls(Ref<? extends haschild_Ref<?>> r, Scope<? extends haschild_Scope<?>> s) { 

    ArrayList<Scope<? extends haschild_Scope<?>>> res = 
      new ArrayList<Scope<? extends haschild_Scope<?>>>();

    if (s.datum().str().equals(r.str())) {
      res.add(s);
      if (scopeTrace)
      System.out.println("\tFinal state: " + r.pp() + ", found a good match " + s.pp());
    } else {
      if (scopeTrace)
      System.out.println("\tFinal state: " + r.pp() + ", found a bad match " + s.pp());
    }

    return res;
  
  } 

}

class dfaStateSink<T extends haschild_DFAState<T>>
extends DFAState<T> {

  public dfaStateSink() {}

  /* GETTING OWN INHERITED ATTRIBUTES */

  public DFAState<T> lexT() {
    if (this.lexT_computed) return this.lexT;
    Boolean interrupted_circle = false;
    if (!this.lexT_visited) {
      this.lexT_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.lexT = this.parent.lexT(this.childId);
      this.lexT_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.lexT_visited = false;
      return this.lexT;
    }
    throw new RuntimeException("Circular definition of dfaStateSink.lexT");
  }

  public DFAState<T> varT() {
    if (this.varT_computed) return this.varT;
    Boolean interrupted_circle = false;
    if (!this.varT_visited) {
      this.varT_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.varT = this.parent.varT(this.childId);
      this.varT_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.varT_visited = false;
      return this.varT;
    }
    throw new RuntimeException("Circular definition of dfaStateSink.varT");
  }

  public DFAState<T> modT() {
    if (this.modT_computed) return this.modT;
    Boolean interrupted_circle = false;
    if (!this.modT_visited) {
      this.modT_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.modT = this.parent.modT(this.childId);
      this.modT_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.modT_visited = false;
      return this.modT;
    }
    throw new RuntimeException("Circular definition of dfaStateSink.modT");
  }

  public DFAState<T> impT() {
    if (this.impT_computed) return this.impT;
    Boolean interrupted_circle = false;
    if (!this.impT_visited) {
      this.impT_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.impT = this.parent.impT(this.childId);
      this.impT_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.impT_visited = false;
      return this.impT;
    }
    throw new RuntimeException("Circular definition of dfaStateSink.impT");
  }

  /* SYNTHESIZED ATTRIBUTES */

  public String pp() {
    if (this.pp_computed) return this.pp;
    this.pp = "dfaStateSink_" + Integer.toString(this.hashCode());
    this.pp_computed = true;
    return this.pp;
  }

  public ArrayList<Scope<? extends haschild_Scope<?>>> 
  decls(Ref<? extends haschild_Ref<?>> r, Scope<? extends haschild_Scope<?>> s) { 
    if (scopeTrace) System.out.println("\tSink state");
    return new ArrayList<Scope<? extends haschild_Scope<?>>>();
  } 

}


class dfaStateMod<T extends haschild_DFAState<T>>
extends DFAState<T> {

  public dfaStateMod() {}

  /* GETTING OWN INHERITED ATTRIBUTES */

  public DFAState<T> lexT() {
    if (this.lexT_computed) return this.lexT;
    Boolean interrupted_circle = false;
    if (!this.lexT_visited) {
      this.lexT_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.lexT = this.parent.lexT(this.childId);
      this.lexT_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.lexT_visited = false;
      return this.lexT;
    }
    throw new RuntimeException("Circular definition of dfaStateMod.lexT");
  }

  public DFAState<T> varT() {
    if (this.varT_computed) return this.varT;
    Boolean interrupted_circle = false;
    if (!this.varT_visited) {
      this.varT_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.varT = this.parent.varT(this.childId);
      this.varT_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.varT_visited = false;
      return this.varT;
    }
    throw new RuntimeException("Circular definition of dfaStateMod.varT");
  }

  public DFAState<T> modT() {
    if (this.modT_computed) return this.modT;
    Boolean interrupted_circle = false;
    if (!this.modT_visited) {
      this.modT_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.modT = this.parent.modT(this.childId);
      this.modT_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.modT_visited = false;
      return this.modT;
    }
    throw new RuntimeException("Circular definition of dfaStateMod.modT");
  }

  public DFAState<T> impT() {
    if (this.impT_computed) return this.impT;
    Boolean interrupted_circle = false;
    if (!this.impT_visited) {
      this.impT_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.impT = this.parent.impT(this.childId);
      this.impT_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.impT_visited = false;
      return this.impT;
    }
    throw new RuntimeException("Circular definition of dfaStateMod.impT");
  }

  /* SYNTHESIZED ATTRIBUTES */

  public String pp() {
    if (this.pp_computed) return this.pp;
    this.pp = "dfaStateMod_" + Integer.toString(this.hashCode());
    this.pp_computed = true;
    return this.pp;
  }

  public ArrayList<Scope<? extends haschild_Scope<?>>> 
  decls(Ref<? extends haschild_Ref<?>> r, Scope<? extends haschild_Scope<?>> s) { 

    ArrayList<Scope<? extends haschild_Scope<?>>> varRes = 
      new ArrayList<Scope<? extends haschild_Scope<?>>>();
    for (Scope<? extends haschild_Scope<?>> sVar: s.var()) {
      if (scopeTrace)
      System.out.println(TreeNode.tab() + "In resolving " + r.pp() + " in state " + this.pp() + ", traversing a VAR " + "edge from " + s.pp() + " to " + sVar.pp() + ", and traversing DFA to state " + this.varT().pp());
      TreeNode.tabIncrease();
      varRes.addAll(this.varT().decls(r, sVar));
      TreeNode.tabDecrease();
    }

    ArrayList<Scope<? extends haschild_Scope<?>>> modRes = 
      new ArrayList<Scope<? extends haschild_Scope<?>>>();
    for (Scope<? extends haschild_Scope<?>> sMod: s.mod()) {
      if (scopeTrace)
      System.out.println(TreeNode.tab() + "In resolving " + r.pp() + " in state " + this.pp() + ", traversing a MOD " + "edge from " + s.pp() + " to " + sMod.pp() + ", and traversing DFA to state " + this.modT().pp());
      TreeNode.tabIncrease();
      modRes.addAll(this.modT().decls(r, sMod));
      TreeNode.tabDecrease();
    }

    ArrayList<Scope<? extends haschild_Scope<?>>> impTentativeRes = 
      new ArrayList<Scope<? extends haschild_Scope<?>>>();
    for (Scope<? extends haschild_Scope<?>> sImp: s.impTentative()) {
      if (scopeTrace)
      System.out.println(TreeNode.tab() + "In resolving " + r.pp() + " in state " + this.pp() + ", traversing a IMPTENTATIVE " + "edge from " + s.pp() + " to " + sImp.pp() + ", and traversing DFA to state " + this.impT().pp());
      TreeNode.tabIncrease();
      impTentativeRes.addAll(this.impT().decls(r, sImp));
      TreeNode.tabDecrease();
    }

    ArrayList<Scope<? extends haschild_Scope<?>>> lexRes = 
      new ArrayList<Scope<? extends haschild_Scope<?>>>();
    for (Scope<? extends haschild_Scope<?>> sLex: s.lex()) {
      if (scopeTrace)
      System.out.println(TreeNode.tab() + "In resolving " + r.pp() + " in state " + this.pp() + ", traversing a LEX " + "edge from " + s.pp() + " to " + sLex.pp() + ", and traversing DFA to state " + this.lexT().pp());
      TreeNode.tabIncrease();
      lexRes.addAll(this.lexT().decls(r, sLex));
      TreeNode.tabDecrease();
    }

    return 
      (!varRes.isEmpty()) ? varRes :
      (!modRes.isEmpty()) ? modRes :
      (!impTentativeRes.isEmpty()) ? impTentativeRes :
      lexRes;
  
  }

}