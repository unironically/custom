package lm;

import java.util.ArrayList;

interface inh_scope { public Scope scope(int child); }

interface inh_lex { public ArrayList<Scope> lex(int child); }
interface inh_var { public ArrayList<Scope> var(int child); }
interface inh_mod { public ArrayList<Scope> mod(int child); }
interface inh_imp { public ArrayList<Scope> imp(int child); }