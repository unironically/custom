
### Nonterminals

#### Inherited attributes
Nonterminal declaration:

```
nt Dcls;
attr scope occurs on Dcls;
```

Goes to:

```Java
abstract class Dcls<T extends haschild_Dcls<T>> extends TreeNode<T>  {
  ///
}
```

Where `T` is the production type of the parent node.
`<T extends haschild_Dcls<T>>` means that the parent class must implement 
interface `haschild_Dcls<T>`, which, since `Dcls` only inherits `Scope`, is:
```Java
interface haschild_Dcls<T extends haschild_Dcls<T>> {
  public Scope<? extends haschild_Scope<?>> scope(int child);
}
```

I.e., the parent node must implement the above function. The parent node must 
also give the `Dcls` node a `childId`, so that its implementation of the above function works:

```Java
class main extends Main
  implements haschild_Dcls<main>, haschild_Scope<main> {

  public main(Dcls<main> ds) {
    this.ds = ds;
    ds.setParent(this, 0);
  }

  public Scope<main> scope(int child) {
    if (child == 0) {
      return this.s();
    }
    return null;
  }

  // ...
}
```

#### Synthesized attributes

```
nt Dcls;
attr scope occurs on Dcls;
attr vars occurs on Dcls;
attr mods occurs on Dcls;
attr imps occurs on Dcls;
attr binds occurs on Dcls;
```
Goes to
```Java
abstract class Dcls<T extends haschild_Dcls<T>> extends TreeNode<T>  {

  // scope
  Scope<? extends haschild_Scope<?>> scope = null;
  Boolean scope_computed = false;
  
  // vars
  List<Scope<? extends haschild_Scope<?>>> vars  = null;
  Boolean vars_computed  = false;
  public List<Scope<? extends haschild_Scope<?>>> vars() { return this.vars; }

  // mods
  List<Scope<? extends haschild_Scope<?>>> mods  = null;
  Boolean mods_computed  = false;
  public List<Scope<? extends haschild_Scope<?>>> mods() { return this.mods }

  // imps
  List<Scope<? extends haschild_Scope<?>>> imps  = null;
  Boolean imps_computed  = false;
  public List<Scope<? extends haschild_Scope<?>>> imps() { return this.imps; }

  // binds
  List<Pair<Ref, List<Scope<? extends haschild_Scope<?>>>>> binds = null;
  Boolean binds_computed = false;
  List<Pair<Ref, List<Scope<? extends haschild_Scope<?>>>>> binds() 
    { return null; }

}
```

### Productions

Each production becomes a class of its own, extending the `abstract` class of 
its nonterminal. Declaration:

```
prod dclsCons: Dcls ::= d:Dcl ds:Dcls {
  local s:Scope = mkScope();
  ...
}
```

Goes to:

```Java
class dclsCons<T extends haschild_Dcls<T>> extends Dcls<T> 
implements haschild_Dcl<dclsCons<T>>,   // d
           haschild_Dcls<dclsCons<T>>,  // ds
           haschild_Scope<dclsCons<T>>  // s 
{
  // ...
}
```