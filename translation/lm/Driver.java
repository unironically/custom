package lm;
import lm.*;

import java.util.ArrayList;

public class Driver {

  public static void main(String[] args) {
    Main m = program3();
    printBinds(m.binds());
  }

  public static void printBinds(ArrayList<Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>>> bnds) {
      
    if (bnds.isEmpty()) return;

    System.out.println("- Resolutions:");

    for (Pair<Ref, ArrayList<Scope<? extends haschild_Scope<?>>>> p: bnds) {
      Ref r = p.fst();
      ArrayList<Scope<? extends haschild_Scope<?>>> scopes = p.snd();

      String scopesStr = "";
      for (Scope<? extends haschild_Scope<?>> s: scopes) {
         scopesStr += s.pp() + ", ";
      }

      System.out.println("\t- " + r.pp() + 
         " |-> " + 
         scopesStr
      );
      
    }

  }

   /* PROGRAMS */

  public static Main program1() {

    /* LM program:

       def a = 1
       def b = a

     * Tree:                 Names below:

       main                  main
         dclsCons            ds1
           dclBind           d1
             "a"      
             expInt          e1
               1
           dclsCons          ds2
             dclBind         b2
               "b"
               expRef        e2
                 vref        r
                   "a"
             dclsNil

     */

    Exp<bnd<dclBind<dclsCons<main>>>> e1 = 
      new expInt<bnd<dclBind<dclsCons<main>>>>(1);

    Bind<dclBind<dclsCons<main>>> b1 = 
      new bnd<dclBind<dclsCons<main>>>("a", new intType(), e1);

    Dcl<dclsCons<main>> d1 = 
      new dclBind<dclsCons<main>>(b1); 

    VarRef<expRef<bnd<dclBind<dclsCons<dclsCons<main>>>>>> r = 
      new vref<expRef<bnd<dclBind<dclsCons<dclsCons<main>>>>>>("a");

    Exp<bnd<dclBind<dclsCons<dclsCons<main>>>>> e2 = 
      new expRef<bnd<dclBind<dclsCons<dclsCons<main>>>>>(r);

    Bind<dclBind<dclsCons<dclsCons<main>>>> b2 = 
      new bnd<dclBind<dclsCons<dclsCons<main>>>>("b", new intType(), e2);

    Dcl<dclsCons<dclsCons<main>>> d2 = 
      new dclBind<dclsCons<dclsCons<main>>>(b2);

    Dcls<dclsCons<main>> ds2 = 
      new dclsCons<dclsCons<main>>
         (d2, new dclsNil<dclsCons<dclsCons<main>>>());

    Dcls<main> ds1 = new dclsCons<main>(d1, ds2);
      
    Main m = new main(ds1);

    return m;

  }

  public static Main program2() {

    /* LM program:

      def a = 1
      mod B {
        def c = a
      }

    * Tree:                 Names below:

      main                  main
        dclsCons            ds1
          dclBind           d1
            "a"      
            expInt          e1
              1
          dclsCons          ds2
            dclMod          d2
              "B"
              dclsCons      ds3
                dclBind     d3
                  "c"
                  expRef    e3
                    vref    r
                      "a"
                dclsNil
            dclsNil

    */

    Exp<bnd<dclBind<dclsCons<main>>>> e1 = 
      new expInt<>(1);
    Bind<dclBind<dclsCons<main>>> b1 = 
      new bnd<>("a", new intType(), e1);
    Dcl<dclsCons<main>> d1 = 
      new dclBind<>(b1);


    VarRef<expRef<bnd<dclBind<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>>> r = 
      new vref<>("a");
    Exp<bnd<dclBind<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>> e3 = 
      new expRef<>(r);
    Bind<dclBind<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>> b3 = 
      new bnd<>("c", new intType(), e3);
    Dcl<dclsCons<dclMod<dclsCons<dclsCons<main>>>>> d3 = 
      new dclBind<>(b3);
    Dcls<dclMod<dclsCons<dclsCons<main>>>> ds3 = 
      new dclsCons<>
         (d3, new dclsNil<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>());
    Dcl<dclsCons<dclsCons<main>>> d2 = 
      new dclMod<>("A", ds3);


    Dcls<dclsCons<main>> ds2 = 
      new dclsCons<>
         (d2, new dclsNil<dclsCons<dclsCons<main>>>());
    Dcls<main> ds1 = new dclsCons<>(d1, ds2);
      
    Main m = new main(ds1);

    return m;

  }

  public static Main program3() {
    /* LM program:

      mod A {
        def a = 1
      }
      mod B {
        import A
        def c = a
      }

    * Tree:                         Names below:

      main                          main
        dclsCons
          dclMod
            "A"
            dclsCons
              dclBind
                "a"
                expInt
                  1
              dclsNil
          dclsCons
            dclMod
              dclsCons
                dclImp
                  mref
                    "A"
                dclsCons
                  dclBind
                    "c"
                    expRef
                      vref
                        "a"
                  dclsNil
            dclsNil

    */

    VarRef<expRef<bnd<dclBind<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>>>> r2 = new vref<>("a");
    Exp<bnd<dclBind<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>>> e2 =
      new expRef<>(r2);
    Bind<dclBind<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>> b2 =
      new bnd<>("c", new intType(), e2);

    Dcl<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>> d5 =
      new dclBind<>(b2);
    Dcls<dclsCons<dclMod<dclsCons<dclsCons<main>>>>> ds5 = new dclsCons<>(d5, new dclsNil<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>());


    ModRef<dclImp<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>> mr = 
      new mref<>("A");
    Dcl<dclsCons<dclMod<dclsCons<dclsCons<main>>>>> d4 = new dclImp<>(mr);

    Dcls<dclMod<dclsCons<dclsCons<main>>>> ds4 = new dclsCons<>(d4, ds5);
    Dcl<dclsCons<dclsCons<main>>> d3 = new dclMod<>("B", ds4);

    Dcls<dclsCons<main>> ds2 = 
      new dclsCons<>(d3, new dclsNil<dclsCons<dclsCons<main>>>());

    Exp<bnd<dclBind<dclsCons<dclMod<dclsCons<main>>>>>> e1 = new expInt<>(1);
    Bind<dclBind<dclsCons<dclMod<dclsCons<main>>>>> b1 = 
      new bnd<>("a", new intType(), e1);
    Dcl<dclsCons<dclMod<dclsCons<main>>>> d2 = new dclBind<>(b1);
    Dcls<dclMod<dclsCons<main>>> ds3 = 
      new dclsCons<>(d2, new dclsNil<dclsCons<dclMod<dclsCons<main>>>>());
    Dcl<dclsCons<main>> d1 = new dclMod<>("A", ds3);

    Dcls<main> ds1 = new dclsCons<>(d1, ds2);

    Main m = new main(ds1);

    return m;

  }

}

