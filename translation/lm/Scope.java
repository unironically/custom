package lm;

import java.util.ArrayList;

abstract class Scope<T extends haschild_Scope<T>> 
  extends TreeNode<T> {

  protected ArrayList<Scope<? extends haschild_Scope<?>>> var = null;
  protected Boolean var_computed = false;
  public ArrayList<Scope<? extends haschild_Scope<?>>> var() { return null; }

  protected ArrayList<Scope<? extends haschild_Scope<?>>> mod = null;
  protected Boolean mod_computed = false;
  public ArrayList<Scope<? extends haschild_Scope<?>>> mod() { return null; }

  protected ArrayList<Scope<? extends haschild_Scope<?>>> imp = null;
  protected Boolean imp_computed = false;
  public ArrayList<Scope<? extends haschild_Scope<?>>> imp() { return null; }

  protected ArrayList<Scope<? extends haschild_Scope<?>>> lex = null;
  protected Boolean lex_computed = false;
  public ArrayList<Scope<? extends haschild_Scope<?>>> lex() { return null; }

  protected Datum datum = null;
  protected Boolean datum_computed = false;
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
    this.datum = new datumNone();
    this.datum_computed = true;
    return this.datum;
  }

  /* GETTING OWN INHERITED ATTRIBUTES */

  public ArrayList<Scope<? extends haschild_Scope<?>>> var() {
    return this.parent.var(this.childId);
  }

  public ArrayList<Scope<? extends haschild_Scope<?>>> mod() {
    return this.parent.mod(this.childId);
  }

  public ArrayList<Scope<? extends haschild_Scope<?>>> imp() {
    return this.parent.imp(this.childId);
  }

  public ArrayList<Scope<? extends haschild_Scope<?>>> lex() {
    return this.parent.lex(this.childId);
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
    this.datum = new datumMod(this.s);
    this.datum_computed = true;
    return this.datum;
  }

  /* GETTING OWN INHERITED ATTRIBUTES */

  public ArrayList<Scope<? extends haschild_Scope<?>>> var() {
    return this.parent.var(this.childId);
  }

  public ArrayList<Scope<? extends haschild_Scope<?>>> mod() {
    return this.parent.mod(this.childId);
  }

  public ArrayList<Scope<? extends haschild_Scope<?>>> imp() {
    return this.parent.imp(this.childId);
  }

  public ArrayList<Scope<? extends haschild_Scope<?>>> lex() {
    return this.parent.lex(this.childId);
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
    this.datum = new datumVar(this.s, this.t);
    this.datum_computed = true;
    return this.datum;
  }

  /* GETTING OWN INHERITED ATTRIBUTES */

  public ArrayList<Scope<? extends haschild_Scope<?>>> var() {
    return this.parent.var(this.childId);
  }

  public ArrayList<Scope<? extends haschild_Scope<?>>> mod() {
    return this.parent.mod(this.childId);
  }

  public ArrayList<Scope<? extends haschild_Scope<?>>> imp() {
    return this.parent.imp(this.childId);
  }

  public ArrayList<Scope<? extends haschild_Scope<?>>> lex() {
    return this.parent.lex(this.childId);
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