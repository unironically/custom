package lm;

import java.util.ArrayList;

abstract class Ref<T extends haschild_Ref<T>> extends TreeNode<T> {

  protected ArrayList<Scope<? extends haschild_Scope<?>>> lex = null;
  protected Boolean lex_computed = false;
  protected Boolean lex_visited = false;
  public ArrayList<Scope<? extends haschild_Scope<?>>> lex() { return null; }

  protected ArrayList<Scope<? extends haschild_Scope<?>>> res = new ArrayList<>();
  protected Boolean res_computed = false;
  protected Boolean res_visited = false;
  public ArrayList<Scope<? extends haschild_Scope<?>>> res() { return new ArrayList<>(); }

  protected String str = null;
  protected Boolean str_computed = false;
  public String str() { return null; }

  protected ArrayList<Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>> 
    binds = null;
  protected Boolean binds_computed = false;
  protected Boolean binds_visited = false;
  public ArrayList<Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>> 
    binds() { return null; }  

  protected String pp = "";
  protected Boolean pp_computed  = false;
  public String pp() { return null; }

}

class mkVarRef<T extends haschild_Ref<T>> extends Ref<T> {

  protected String s;

  public mkVarRef(String s) {
    this.s = s;
    if(scopeTrace) System.out.println("- Created ref " + this.pp());
  }
  
  /* LOCALS */

  private DFA<mkVarRef<T>> dfa = null;
  private Boolean dfa_computed = false;
  private Boolean dfa_visited = false;
  protected DFA<mkVarRef<T>> dfa () {
    if (this.dfa_computed) return this.dfa;
    Boolean interrupted_circle = false;
    if (!this.dfa_visited) {
      this.dfa_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.dfa = new varDFA<mkVarRef<T>>();
      this.dfa_computed = true;
      if (interrupted_circle) {
          TreeNode.CHANGE = TreeNode.STACK.pop();
          TreeNode.IN_CIRCLE = true;
        }
      this.dfa_visited = false;
      return this.dfa;
    }
    throw new RuntimeException("Circular definition of mkVarRef.dfa");
  }

  /* INHERITED ATTRIBUTES */

  /* GETTING OWN INHERITED ATTRIBUTES */

  public ArrayList<Scope<? extends haschild_Scope<?>>> lex() {
    if (this.lex_computed) return this.lex;
    Boolean interrupted_circle = false;
    if (!this.lex_visited) {
      this.lex_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.lex = this.parent.lex(this.childId);
      this.lex_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.lex_visited = false;
      return this.lex;
    }
    throw new RuntimeException("Circular definition of mkVarRef.lex");
  }
  
  /* SYNTHESIZED ATTRIBUTES */

  public ArrayList<Scope<? extends haschild_Scope<?>>> res() {
    if (this.res_computed) return this.res;
    boolean interrupted_circle = false;
    if (!this.res_visited) {
      this.res_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.res = new ArrayList<>();
      this.res.addAll(this.dfa().decls(this, this.lex().get(0)));
      this.res_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.res_visited = false;
      return this.res;
    }
    throw new RuntimeException("Circular definition of mkVarRef.res");
  }

  public String str() {
    if (this.str_computed) return this.str;
    this.str = this.s;
    this.str_computed = true;
    return this.str;
  }

  public ArrayList<Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>> 
  binds() {
    if(this.binds_computed) return this.binds;
    Boolean interrupted_circle = false;
    if (!this.binds_visited) {
      this.binds_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.binds = new ArrayList<>();
      binds.add(new Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>
      (this, this.res()));
      this.binds_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.binds_visited = false;
      return this.binds;
    }
    throw new RuntimeException("Circular definition of mkVarRef.binds");
  }

  public String pp() {
    if (this.pp_computed) return this.pp;
    this.pp = "mkVarRef(\"" + this.s + "\")_" + 
      Integer.toString(this.hashCode());
    this.pp_computed = true;
    return this.pp;
  }

}

class mkModRef<T extends haschild_Ref<T>> extends Ref<T> {

  protected String s;

  public mkModRef(String s) {
    this.s = s;
    if(scopeTrace) System.out.println("- Created ref " + this.pp());
  }

  /* LOCALS */

  private DFA<mkModRef<T>> dfa = null;
  private Boolean dfa_computed = false;
  private Boolean dfa_visited = false;
  protected DFA<mkModRef<T>> dfa () {
    if (this.dfa_computed) return this.dfa;
    Boolean interrupted_circle = false;
    if (!this.dfa_visited) {
      this.dfa_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.dfa = new modDFA<mkModRef<T>>();
      this.dfa_computed = true;
      if (interrupted_circle) {
          TreeNode.CHANGE = TreeNode.STACK.pop();
          TreeNode.IN_CIRCLE = true;
        }
      this.dfa_visited = false;
      return this.dfa;
    }
    throw new RuntimeException("Circular definition of mkModRef.dfa");
  }

  /* INHERITED ATTRIBUTES */

  /* GETTING OWN INHERITED ATTRIBUTES */

  public ArrayList<Scope<? extends haschild_Scope<?>>> lex() {
    if (this.lex_computed) return this.lex;
    Boolean interrupted_circle = false;
    if (!this.lex_visited) {
      this.lex_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.lex = this.parent.lex(this.childId);
      this.lex_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.lex_visited = false;
      return this.lex;
    }
    throw new RuntimeException("Circular definition of mkModRef.lex");
  }
  
  /* SYNTHESIZED ATTRIBUTES */

  /*public ArrayList<Scope<? extends haschild_Scope<?>>> res() {
    if (this.res_computed) return this.res;
    boolean interrupted_circle = false;
    if (!this.res_visited) {
      this.res_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.res = new ArrayList<>();
      this.res.addAll(this.dfa().decls(this, this.lex().get(0)));
      this.res_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.res_visited = false;
      return this.res;
    }
    throw new RuntimeException("Circular definition of mkModRef.res");
  }*/

  public ArrayList<Scope<? extends haschild_Scope<?>>> res() {
    if (res_computed) return res;
    if (!IN_CIRCLE) {
      IN_CIRCLE = true;
      res_visited = true;
      do {
        CHANGE = false;
        ArrayList<Scope<? extends haschild_Scope<?>>> new_res_value = 
          new ArrayList<>();
        new_res_value.addAll(this.dfa().decls(this, this.lex().get(0)));
        if (!new_res_value.equals(res)) CHANGE = true;
        res = new_res_value;
      } while (CHANGE);
      res_visited = false;
      res_computed = true;
      IN_CIRCLE = false;
      return res;
    }
    else if (!res_visited) {
      res_visited = true;
      ArrayList<Scope<? extends haschild_Scope<?>>> new_res_value = 
          new ArrayList<>();
      new_res_value.addAll(this.dfa().decls(this, this.lex().get(0)));
      if (!new_res_value.equals(res)) CHANGE = true;
      res = new_res_value;
      res_visited = false;
      return res;
    }
    else return res;
  }

  public String str() {
    if (this.str_computed) return this.str;
    this.str = this.s;
    this.str_computed = true;
    return this.str;
  }

  public ArrayList<Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>> 
  binds() {
    if(this.binds_computed) return this.binds;
    Boolean interrupted_circle = false;
    if (!this.binds_visited) {
      this.binds_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.binds = new ArrayList<>();
      this.binds.add(new Pair<> (this, this.res()));
      this.binds_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.binds_visited = false;
      return this.binds;
    }
    throw new RuntimeException("Circular definition of dclsCons.binds");
  }

  public String pp() {
    if (this.pp_computed) return this.pp;
    this.pp = "mkModRef(\"" + this.s + "\")_" + 
      Integer.toString(this.hashCode());
    this.pp_computed = true;
    return this.pp;
  }

}