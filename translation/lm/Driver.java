package lm;
import lm.*;

import java.util.ArrayList;

public class Driver {

  public static void main(String[] args) {
    Main m = program4();
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

  public static Main program4() {

    Dcls<dclsCons<dclsCons<dclsCons<main>>>> ds77 = new dclsNil<dclsCons<dclsCons<dclsCons<main>>>>();
    Dcls<dclsCons<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<dclsCons<main>>>>>>>> ds72 = new dclsNil<dclsCons<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<dclsCons<main>>>>>>>>();
    VarRef<expRef<bnd<dclBind<dclsCons<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<dclsCons<main>>>>>>>>>>> vref_76 = new vref<expRef<bnd<dclBind<dclsCons<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<dclsCons<main>>>>>>>>>>>("x");
    Exp<bnd<dclBind<dclsCons<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<dclsCons<main>>>>>>>>>> expRef_75 = new expRef<bnd<dclBind<dclsCons<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<dclsCons<main>>>>>>>>>>(vref_76);
    Type intType_74 = new intType();
    Bind<dclBind<dclsCons<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<dclsCons<main>>>>>>>>> bnd_73 = new bnd<dclBind<dclsCons<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<dclsCons<main>>>>>>>>>("y", intType_74, expRef_75);
    Dcl<dclsCons<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<dclsCons<main>>>>>>>> d71 = new dclBind<dclsCons<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<dclsCons<main>>>>>>>>(bnd_73);
    Dcls<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<dclsCons<main>>>>>>> ds69 = new dclsCons<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<dclsCons<main>>>>>>>(d71, ds72);
    ModRef<dclImp<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<dclsCons<main>>>>>>>> mref_70 = new mref<dclImp<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<dclsCons<main>>>>>>>>("B");
    Dcl<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<dclsCons<main>>>>>>> d68 = new dclImp<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<dclsCons<main>>>>>>>(mref_70);
    Dcls<dclsCons<dclMod<dclsCons<dclsCons<dclsCons<main>>>>>> ds66 = new dclsCons<dclsCons<dclMod<dclsCons<dclsCons<dclsCons<main>>>>>>(d68, ds69);
    ModRef<dclImp<dclsCons<dclMod<dclsCons<dclsCons<dclsCons<main>>>>>>> mref_67 = new mref<dclImp<dclsCons<dclMod<dclsCons<dclsCons<dclsCons<main>>>>>>>("A");
    Dcl<dclsCons<dclMod<dclsCons<dclsCons<dclsCons<main>>>>>> d65 = new dclImp<dclsCons<dclMod<dclsCons<dclsCons<dclsCons<main>>>>>>(mref_67);
    Dcls<dclMod<dclsCons<dclsCons<dclsCons<main>>>>> ds64 = new dclsCons<dclMod<dclsCons<dclsCons<dclsCons<main>>>>>(d65, ds66);
    Dcl<dclsCons<dclsCons<dclsCons<main>>>> d63 = new dclMod<dclsCons<dclsCons<dclsCons<main>>>>("C", ds64);
    Dcls<dclsCons<dclsCons<main>>> ds78 = new dclsCons<dclsCons<dclsCons<main>>>(d63, ds77);
    Dcls<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>> ds56 = new dclsNil<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>();
    Dcls<dclsCons<dclMod<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>>> ds59 = new dclsNil<dclsCons<dclMod<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>>>();
    Exp<bnd<dclBind<dclsCons<dclMod<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>>>>> expInt_62 = new expInt<bnd<dclBind<dclsCons<dclMod<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>>>>>(4);
    Type intType_61 = new intType();
    Bind<dclBind<dclsCons<dclMod<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>>>> bnd_60 = new bnd<dclBind<dclsCons<dclMod<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>>>>("x", intType_61, expInt_62);
    Dcl<dclsCons<dclMod<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>>> d58 = new dclBind<dclsCons<dclMod<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>>>(bnd_60);
    Dcls<dclMod<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>> ds57 = new dclsCons<dclMod<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>>(d58, ds59);
    Dcl<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>> d55 = new dclMod<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>("A", ds57);
    Dcls<dclsCons<dclMod<dclsCons<dclsCons<main>>>>> ds51 = new dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>(d55, ds56);
    Exp<bnd<dclBind<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>> expInt_54 = new expInt<bnd<dclBind<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>>(3);
    Type intType_53 = new intType();
    Bind<dclBind<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>> bnd_52 = new bnd<dclBind<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>("x", intType_53, expInt_54);
    Dcl<dclsCons<dclMod<dclsCons<dclsCons<main>>>>> d50 = new dclBind<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>(bnd_52);
    Dcls<dclMod<dclsCons<dclsCons<main>>>> ds49 = new dclsCons<dclMod<dclsCons<dclsCons<main>>>>(d50, ds51);
    Dcl<dclsCons<dclsCons<main>>> d48 = new dclMod<dclsCons<dclsCons<main>>>("B", ds49);
    Dcls<dclsCons<main>> ds79 = new dclsCons<dclsCons<main>>(d48, ds78);
    Dcls<dclsCons<dclsCons<dclMod<dclsCons<main>>>>> ds41 = new dclsNil<dclsCons<dclsCons<dclMod<dclsCons<main>>>>>();
    Dcls<dclsCons<dclMod<dclsCons<dclsCons<dclMod<dclsCons<main>>>>>>> ds44 = new dclsNil<dclsCons<dclMod<dclsCons<dclsCons<dclMod<dclsCons<main>>>>>>>();
    Exp<bnd<dclBind<dclsCons<dclMod<dclsCons<dclsCons<dclMod<dclsCons<main>>>>>>>>> expInt_47 = new expInt<bnd<dclBind<dclsCons<dclMod<dclsCons<dclsCons<dclMod<dclsCons<main>>>>>>>>>(2);
    Type intType_46 = new intType();
    Bind<dclBind<dclsCons<dclMod<dclsCons<dclsCons<dclMod<dclsCons<main>>>>>>>> bnd_45 = new bnd<dclBind<dclsCons<dclMod<dclsCons<dclsCons<dclMod<dclsCons<main>>>>>>>>("x", intType_46, expInt_47);
    Dcl<dclsCons<dclMod<dclsCons<dclsCons<dclMod<dclsCons<main>>>>>>> d43 = new dclBind<dclsCons<dclMod<dclsCons<dclsCons<dclMod<dclsCons<main>>>>>>>(bnd_45);
    Dcls<dclMod<dclsCons<dclsCons<dclMod<dclsCons<main>>>>>> ds42 = new dclsCons<dclMod<dclsCons<dclsCons<dclMod<dclsCons<main>>>>>>(d43, ds44);
    Dcl<dclsCons<dclsCons<dclMod<dclsCons<main>>>>> d40 = new dclMod<dclsCons<dclsCons<dclMod<dclsCons<main>>>>>("B", ds42);
    Dcls<dclsCons<dclMod<dclsCons<main>>>> ds36 = new dclsCons<dclsCons<dclMod<dclsCons<main>>>>(d40, ds41);
    Exp<bnd<dclBind<dclsCons<dclMod<dclsCons<main>>>>>> expInt_39 = new expInt<bnd<dclBind<dclsCons<dclMod<dclsCons<main>>>>>>(1);
    Type intType_38 = new intType();
    Bind<dclBind<dclsCons<dclMod<dclsCons<main>>>>> bnd_37 = new bnd<dclBind<dclsCons<dclMod<dclsCons<main>>>>>("x", intType_38, expInt_39);
    Dcl<dclsCons<dclMod<dclsCons<main>>>> d35 = new dclBind<dclsCons<dclMod<dclsCons<main>>>>(bnd_37);
    Dcls<dclMod<dclsCons<main>>> ds34 = new dclsCons<dclMod<dclsCons<main>>>(d35, ds36);
    Dcl<dclsCons<main>> d33 = new dclMod<dclsCons<main>>("A", ds34);
    Dcls<main> ds80 = new dclsCons<main>(d33, ds79);
    Main m = new main(ds80);

    return m;

  }

}

