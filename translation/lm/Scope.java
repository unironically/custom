package lm;

import java.util.ArrayList;

abstract class Scope<T extends haschild_Scope<T>> 
  extends TreeNode<T> {

  protected ArrayList<Scope<? extends haschild_Scope<?>>> var = null;
  protected Boolean var_computed = false;
  protected Boolean var_visited = false;
  public ArrayList<Scope<? extends haschild_Scope<?>>> var() { return null; }

  protected ArrayList<Scope<? extends haschild_Scope<?>>> mod = null;
  protected Boolean mod_computed = false;
  protected Boolean mod_visited = false;
  public ArrayList<Scope<? extends haschild_Scope<?>>> mod() { return null; }

  protected ArrayList<Scope<? extends haschild_Scope<?>>> imp = null;
  protected Boolean imp_computed = false;
  protected Boolean imp_visited = false;
  public ArrayList<Scope<? extends haschild_Scope<?>>> imp() { return null; }

  protected ArrayList<Scope<? extends haschild_Scope<?>>> lex = null;
  protected Boolean lex_computed = false;
  protected Boolean lex_visited = false;
  public ArrayList<Scope<? extends haschild_Scope<?>>> lex() { return null; }

  // impTentative, circular attribute
  protected ArrayList<Scope<? extends haschild_Scope<?>>> impTentative = new ArrayList<>();
  protected Boolean impTentative_computed = false;
  protected Boolean impTentative_visited = false;
  public ArrayList<Scope<? extends haschild_Scope<?>>> impTentative()
    { return new ArrayList<>(); }

  protected Datum datum = null;
  protected Boolean datum_computed = false;
  protected Boolean datum_visited = false;
  public Datum datum() { return null; }

  protected String pp = "";
  protected Boolean pp_computed  = false;
  public String pp() { return null; }

}

class mkScope<T extends haschild_Scope<T>> extends Scope<T> {

  public mkScope() {
    if(scopeTrace) System.out.println("- Created scope " + this.pp());
  }

  /* SYNTHESIZED ATTRIBUTES */

  public Datum datum() {
    if (this.datum_computed) return this.datum;
    Boolean interrupted_circle = false;
    if (!this.datum_visited) {
      this.datum_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.datum = new datumNone();
      this.datum_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.datum_visited = false;
      return this.datum;
    }
    throw new RuntimeException("Circular definition of scope.datum");
  }

  /* GETTING OWN INHERITED ATTRIBUTES */

  public ArrayList<Scope<? extends haschild_Scope<?>>> var() {
    if (this.var_computed) return this.var;
    Boolean interrupted_circle = false;
    if (!this.var_visited) {
      this.var_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.var = this.parent.var(this.childId);
      this.var_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.var_visited = false;
      return this.var;
    }
    throw new RuntimeException("Circular definition of mkScope.var");
  }

  public ArrayList<Scope<? extends haschild_Scope<?>>> mod() {
    if (this.mod_computed) return this.mod;
    Boolean interrupted_circle = false;
    if (!this.mod_visited) {
      this.mod_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.mod = this.parent.mod(this.childId);
      this.mod_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.mod_visited = false;
      return this.mod;
    }
    throw new RuntimeException("Circular definition of mkScope.mod");
  }

  public ArrayList<Scope<? extends haschild_Scope<?>>> imp() {
    if (this.imp_computed) return this.imp;
    Boolean interrupted_circle = false;
    if (!this.imp_visited) {
      this.imp_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.imp = this.parent.imp(this.childId);
      this.imp_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.imp_visited = false;
      return this.imp;
    }
    throw new RuntimeException("Circular definition of mkScope.imp");
  }

  // circular attribute impTentative
  public ArrayList<Scope<? extends haschild_Scope<?>>> impTentative() {
    if (impTentative_computed) return impTentative;
    if (!IN_CIRCLE) {
      System.out.println("Init call of " + this.pp() + ".impTentative");
      IN_CIRCLE = true;
      impTentative_visited = true;
      do {
        CHANGE = false;
        ArrayList<Scope<? extends haschild_Scope<?>>> new_impTentative_value = 
          this.parent.impTentative(this.childId);
        if (!new_impTentative_value.equals(impTentative)) CHANGE = true;
        impTentative = new_impTentative_value;
      } while (CHANGE);
      impTentative_visited = false;
      impTentative_computed = true;
      IN_CIRCLE = false;
      return impTentative;
    }
    else if (!impTentative_visited) {
      impTentative_visited = true;
      ArrayList<Scope<? extends haschild_Scope<?>>> new_impTentative_value = 
          this.parent.impTentative(this.childId);
      if (!new_impTentative_value.equals(impTentative)) CHANGE = true;
      impTentative = new_impTentative_value;
      impTentative_visited = false;
      return impTentative;
    }
    else return impTentative;
  }

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
    throw new RuntimeException("Circular definition of mkScope.lex");
  }

  /* SYNTHESIZED ATTRIBUTES */

  public String pp() {
    if (this.pp_computed) return this.pp;
    this.pp = "mkScope_" + Integer.toString(this.hashCode());
    this.pp_computed = true;
    return this.pp;
  }

}

class mkMod<T extends haschild_Scope<T>> extends Scope<T> {

  private String s;

  public mkMod(String s) {
    this.s = s;
    if(scopeTrace) System.out.println("- Created scope " + this.pp());
  }

  /* SYNTHESIZED ATTRIBUTES */

  public Datum datum() {
    if (this.datum_computed) return this.datum;
    Boolean interrupted_circle = false;
    if (!this.datum_visited) {
      this.datum_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.datum = new datumMod(this.s);
      this.datum_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.datum_visited = false;
      return this.datum;
    }
    throw new RuntimeException("Circular definition of scope.datum");
  }

  /* GETTING OWN INHERITED ATTRIBUTES */

  public ArrayList<Scope<? extends haschild_Scope<?>>> var() {
    if (this.var_computed) return this.var;
    Boolean interrupted_circle = false;
    if (!this.var_visited) {
      this.var_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.var = this.parent.var(this.childId);
      this.var_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.var_visited = false;
      return this.var;
    }
    throw new RuntimeException("Circular definition of mkScope.var");
  }

  public ArrayList<Scope<? extends haschild_Scope<?>>> mod() {
    if (this.mod_computed) return this.mod;
    Boolean interrupted_circle = false;
    if (!this.mod_visited) {
      this.mod_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.mod = this.parent.mod(this.childId);
      this.mod_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.mod_visited = false;
      return this.mod;
    }
    throw new RuntimeException("Circular definition of mkScope.mod");
  }

  public ArrayList<Scope<? extends haschild_Scope<?>>> imp() {
    if (this.imp_computed) return this.imp;
    Boolean interrupted_circle = false;
    if (!this.imp_visited) {
      this.imp_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.imp = this.parent.imp(this.childId);
      this.imp_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.imp_visited = false;
      return this.imp;
    }
    throw new RuntimeException("Circular definition of mkScope.imp");
  }

  // circular attribute impTentative
  public ArrayList<Scope<? extends haschild_Scope<?>>> impTentative() {
    if (impTentative_computed) return impTentative;
    if (!IN_CIRCLE) {
      System.out.println("Init call of " + this.pp() + ".impTentative");
      IN_CIRCLE = true;
      impTentative_visited = true;
      do {
        CHANGE = false;
        ArrayList<Scope<? extends haschild_Scope<?>>> new_impTentative_value = 
          this.parent.impTentative(this.childId);
        if (!new_impTentative_value.equals(impTentative)) CHANGE = true;
        impTentative = new_impTentative_value;
      } while (CHANGE);
      impTentative_visited = false;
      impTentative_computed = true;
      IN_CIRCLE = false;
      return impTentative;
    }
    else if (!impTentative_visited) {
      impTentative_visited = true;
      ArrayList<Scope<? extends haschild_Scope<?>>> new_impTentative_value = 
          this.parent.impTentative(this.childId);
      if (!new_impTentative_value.equals(impTentative)) CHANGE = true;
      impTentative = new_impTentative_value;
      impTentative_visited = false;
      return impTentative;
    }
    else return impTentative;
  }

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
    throw new RuntimeException("Circular definition of mkScope.lex");
  }

  /* SYNTHESIZED ATTRIBUTES */

  public String pp() {
    if (this.pp_computed) return this.pp;
    this.pp = "mkMod(\"" + this.s + "\")_" + Integer.toString(this.hashCode());
    this.pp_computed = true;
    return this.pp;
  }

}

class mkVar<T extends haschild_Scope<T>> extends Scope<T> {

  private String s;
  private Type t;

  public mkVar(String s, Type t) {
    this.s = s;
    this.t = t;
    if(scopeTrace) System.out.println("- Created scope " + this.pp());
  }

  /* SYNTHESIZED ATTRIBUTES */

  public Datum datum() {
    if (this.datum_computed) return this.datum;
    Boolean interrupted_circle = false;
    if (!this.datum_visited) {
      this.datum_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.datum = new datumVar(this.s, this.t);
      this.datum_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.datum_visited = false;
      return this.datum;
    }
    throw new RuntimeException("Circular definition of scope.datum");
  }

  /* GETTING OWN INHERITED ATTRIBUTES */

  public ArrayList<Scope<? extends haschild_Scope<?>>> var() {
    if (this.var_computed) return this.var;
    Boolean interrupted_circle = false;
    if (!this.var_visited) {
      this.var_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.var = this.parent.var(this.childId);
      this.var_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.var_visited = false;
      return this.var;
    }
    throw new RuntimeException("Circular definition of mkScope.var");
  }

  public ArrayList<Scope<? extends haschild_Scope<?>>> mod() {
    if (this.mod_computed) return this.mod;
    Boolean interrupted_circle = false;
    if (!this.mod_visited) {
      this.mod_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.mod = this.parent.mod(this.childId);
      this.mod_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.mod_visited = false;
      return this.mod;
    }
    throw new RuntimeException("Circular definition of mkScope.mod");
  }

  public ArrayList<Scope<? extends haschild_Scope<?>>> imp() {
    if (this.imp_computed) return this.imp;
    Boolean interrupted_circle = false;
    if (!this.imp_visited) {
      this.imp_visited = true;
      if (TreeNode.IN_CIRCLE) {
        TreeNode.STACK.push(TreeNode.CHANGE);
        interrupted_circle = true;
      }
      this.imp = this.parent.imp(this.childId);
      this.imp_computed = true;
      if (interrupted_circle) {
        TreeNode.CHANGE = TreeNode.STACK.pop();
        TreeNode.IN_CIRCLE = true;
      }
      this.imp_visited = false;
      return this.imp;
    }
    throw new RuntimeException("Circular definition of mkScope.imp");
  }

  // circular attribute impTentative
  public ArrayList<Scope<? extends haschild_Scope<?>>> impTentative() {
    if (impTentative_computed) return impTentative;
    if (!IN_CIRCLE) {
      System.out.println("Init call of " + this.pp() + ".impTentative");
      IN_CIRCLE = true;
      impTentative_visited = true;
      do {
        CHANGE = false;
        ArrayList<Scope<? extends haschild_Scope<?>>> new_impTentative_value = 
          this.parent.impTentative(this.childId);
        if (!new_impTentative_value.equals(impTentative)) CHANGE = true;
        impTentative = new_impTentative_value;
      } while (CHANGE);
      impTentative_visited = false;
      impTentative_computed = true;
      IN_CIRCLE = false;
      return impTentative;
    }
    else if (!impTentative_visited) {
      impTentative_visited = true;
      ArrayList<Scope<? extends haschild_Scope<?>>> new_impTentative_value = 
          this.parent.impTentative(this.childId);
      if (!new_impTentative_value.equals(impTentative)) CHANGE = true;
      impTentative = new_impTentative_value;
      impTentative_visited = false;
      return impTentative;
    }
    else return impTentative;
  }

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
    throw new RuntimeException("Circular definition of mkScope.lex");
  }

  /* SYNTHESIZED ATTRIBUTES */

  public String pp() {
    if (this.pp_computed) return this.pp;
    this.pp = "mkVar(\"" + this.s + "\")_" + Integer.toString(this.hashCode());
    this.pp_computed = true;
    return this.pp;
  }

}

////////////////////////////////////////////////////////////////////////////////

abstract class Datum extends TreeNode<Scope> {
  
  protected String id_value = null;
  protected Boolean id_computed = false;
  public String id() { return null; }

  protected Type type = null;
  protected Boolean type_computed = false;
  public Type type() { return null; }

  protected String str = null;
  protected Boolean str_computed = false;
  public String str() { return null; }

}

class datumVar extends Datum {


  private String id;
  private Type t;

  public datumVar(String id, Type t) {
    this.id = id;
    this.t = t;
  }

  /* SYNTHESIZED ATTRIBUTES */

  public String id() {
    if (this.id_computed) return this.id_value;
    this.id_value = this.id;
    this.id_computed = true;
    return this.id_value;
  }

  public Type type() {
    if (this.type_computed) return this.type;
    this.type = this.t;
    this.type_computed = true;
    return this.type;
  }

  public String str() {
    if (this.str_computed) return this.str;
    this.str = this.id;
    this.str_computed = true;
    return this.str;
  }

}

class datumMod extends Datum {

  private String id;

  public datumMod(String id) {
    this.id = id;
  }

  /* SYNTHESIZED ATTRIBUTES */

  public String id() {
    if (this.id_computed) return this.id_value;
    this.id_value = this.id;
    this.id_computed = true;
    return this.id_value;
  }

  public Type type() {
    if (this.type_computed) return this.type;
    this.type = new errType();
    this.type_computed = true;
    return this.type;
  }

  public String str() {
    if (this.str_computed) return this.str;
    this.str = this.id;
    this.str_computed = true;
    return this.str;
  }

}

class datumNone extends Datum {

  public datumNone() {
  }

  /* SYNTHESIZED ATTRIBUTES */

  public String id() {
    if (this.id_computed) return this.id_value;
    this.id_value = "";
    this.id_computed = true;
    return this.id_value;
  }

  public Type type() {
    if (this.type_computed) return this.type;
    this.type = new errType();
    this.type_computed = true;
    return this.type;
  }

}