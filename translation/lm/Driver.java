package lm;
import lm.*;

import java.util.ArrayList;

public class Driver {

  public static void main(String[] args) {
    Main m = modules_import_circ();
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

    /* LM program:

      module A {
        def x:int = 1
        module B {
          def x:int = 2
        }
      }

      module B {
        def x:int = 3
        module A {
          def x:int = 4
        }
      }

      module M {
        import A
        import B
        def y:int = x
      }

    */

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

  public static Main mophasco_4() {

    /* LM program:

      module A {
        module B {
          def x:int = 42
        }
      }

      module M {
        import B
        import A
        def y:int = x
      }

    */

    Dcls<dclsCons<dclsCons<main>>> ds49 = new dclsNil<dclsCons<dclsCons<main>>>();
    Dcls<dclsCons<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>> ds44 = new dclsNil<dclsCons<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>>();
    VarRef<expRef<bnd<dclBind<dclsCons<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>>>>> vref_48 = new vref<expRef<bnd<dclBind<dclsCons<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>>>>>("x");
    Exp<bnd<dclBind<dclsCons<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>>>> expRef_47 = new expRef<bnd<dclBind<dclsCons<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>>>>(vref_48);
    Type intType_46 = new intType();
    Bind<dclBind<dclsCons<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>>> bnd_45 = new bnd<dclBind<dclsCons<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>>>("y", intType_46, expRef_47);
    Dcl<dclsCons<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>> d43 = new dclBind<dclsCons<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>>(bnd_45);
    Dcls<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>> ds41 = new dclsCons<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>(d43, ds44);
    ModRef<dclImp<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>> mref_42 = new mref<dclImp<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>>("A");
    Dcl<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>> d40 = new dclImp<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>(mref_42);
    Dcls<dclsCons<dclMod<dclsCons<dclsCons<main>>>>> ds38 = new dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>(d40, ds41);
    ModRef<dclImp<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>> mref_39 = new mref<dclImp<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>("B");
    Dcl<dclsCons<dclMod<dclsCons<dclsCons<main>>>>> d37 = new dclImp<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>(mref_39);
    Dcls<dclMod<dclsCons<dclsCons<main>>>> ds36 = new dclsCons<dclMod<dclsCons<dclsCons<main>>>>(d37, ds38);
    Dcl<dclsCons<dclsCons<main>>> d35 = new dclMod<dclsCons<dclsCons<main>>>("M", ds36);
    Dcls<dclsCons<main>> ds50 = new dclsCons<dclsCons<main>>(d35, ds49);
    Dcls<dclsCons<dclMod<dclsCons<main>>>> ds28 = new dclsNil<dclsCons<dclMod<dclsCons<main>>>>();
    Dcls<dclsCons<dclMod<dclsCons<dclMod<dclsCons<main>>>>>> ds31 = new dclsNil<dclsCons<dclMod<dclsCons<dclMod<dclsCons<main>>>>>>();
    Exp<bnd<dclBind<dclsCons<dclMod<dclsCons<dclMod<dclsCons<main>>>>>>>> expInt_34 = new expInt<bnd<dclBind<dclsCons<dclMod<dclsCons<dclMod<dclsCons<main>>>>>>>>(42);
    Type intType_33 = new intType();
    Bind<dclBind<dclsCons<dclMod<dclsCons<dclMod<dclsCons<main>>>>>>> bnd_32 = new bnd<dclBind<dclsCons<dclMod<dclsCons<dclMod<dclsCons<main>>>>>>>("x", intType_33, expInt_34);
    Dcl<dclsCons<dclMod<dclsCons<dclMod<dclsCons<main>>>>>> d30 = new dclBind<dclsCons<dclMod<dclsCons<dclMod<dclsCons<main>>>>>>(bnd_32);
    Dcls<dclMod<dclsCons<dclMod<dclsCons<main>>>>> ds29 = new dclsCons<dclMod<dclsCons<dclMod<dclsCons<main>>>>>(d30, ds31);
    Dcl<dclsCons<dclMod<dclsCons<main>>>> d27 = new dclMod<dclsCons<dclMod<dclsCons<main>>>>("B", ds29);
    Dcls<dclMod<dclsCons<main>>> ds26 = new dclsCons<dclMod<dclsCons<main>>>(d27, ds28);
    Dcl<dclsCons<main>> d25 = new dclMod<dclsCons<main>>("A", ds26);
    Dcls<main> ds51 = new dclsCons<main>(d25, ds50);
    Main m = new main(ds51);
    return m;
  }

  public static Main mophasco_5() {

    /* LM program:

      module A {
        module A {}
      }

      module M {
        import A
      }
    
    */

    Dcls<dclsCons<dclsCons<main>>> ds20 = new dclsNil<dclsCons<dclsCons<main>>>();
    Dcls<dclsCons<dclMod<dclsCons<dclsCons<main>>>>> ds18 = new dclsNil<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>();
    ModRef<dclImp<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>> mref_19 = new mref<dclImp<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>("A");
    Dcl<dclsCons<dclMod<dclsCons<dclsCons<main>>>>> d17 = new dclImp<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>(mref_19);
    Dcls<dclMod<dclsCons<dclsCons<main>>>> ds16 = new dclsCons<dclMod<dclsCons<dclsCons<main>>>>(d17, ds18);
    Dcl<dclsCons<dclsCons<main>>> d15 = new dclMod<dclsCons<dclsCons<main>>>("M", ds16);
    Dcls<dclsCons<main>> ds21 = new dclsCons<dclsCons<main>>(d15, ds20);
    Dcls<dclsCons<dclMod<dclsCons<main>>>> ds13 = new dclsNil<dclsCons<dclMod<dclsCons<main>>>>();
    Dcls<dclMod<dclsCons<dclMod<dclsCons<main>>>>> ds14 = new dclsNil<dclMod<dclsCons<dclMod<dclsCons<main>>>>>();
    Dcl<dclsCons<dclMod<dclsCons<main>>>> d12 = new dclMod<dclsCons<dclMod<dclsCons<main>>>>("A", ds14);
    Dcls<dclMod<dclsCons<main>>> ds11 = new dclsCons<dclMod<dclsCons<main>>>(d12, ds13);
    Dcl<dclsCons<main>> d10 = new dclMod<dclsCons<main>>("A", ds11);
    Dcls<main> ds22 = new dclsCons<main>(d10, ds21);
    Main m = new main(ds22);
    return m;
  }

  public static Main modules_import_circ() {

    /* LM program:

      module A {
        import B
        def a:int = b
      }

      module B {
        import A
        def b:int = a
      }
    
    */

    Dcls<dclsCons<dclsCons<main>>> ds51 = new dclsNil<dclsCons<dclsCons<main>>>();
    Dcls<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>> ds46 = new dclsNil<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>();
    VarRef<expRef<bnd<dclBind<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>>>> vref_50 = new vref<expRef<bnd<dclBind<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>>>>("a");
    Exp<bnd<dclBind<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>>> expRef_49 = new expRef<bnd<dclBind<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>>>(vref_50);
    Type intType_48 = new intType();
    Bind<dclBind<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>> bnd_47 = new bnd<dclBind<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>>("b", intType_48, expRef_49);
    Dcl<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>> d45 = new dclBind<dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>(bnd_47);
    Dcls<dclsCons<dclMod<dclsCons<dclsCons<main>>>>> ds43 = new dclsCons<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>(d45, ds46);
    ModRef<dclImp<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>> mref_44 = new mref<dclImp<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>>("A");
    Dcl<dclsCons<dclMod<dclsCons<dclsCons<main>>>>> d42 = new dclImp<dclsCons<dclMod<dclsCons<dclsCons<main>>>>>(mref_44);
    Dcls<dclMod<dclsCons<dclsCons<main>>>> ds41 = new dclsCons<dclMod<dclsCons<dclsCons<main>>>>(d42, ds43);
    Dcl<dclsCons<dclsCons<main>>> d40 = new dclMod<dclsCons<dclsCons<main>>>("B", ds41);
    Dcls<dclsCons<main>> ds52 = new dclsCons<dclsCons<main>>(d40, ds51);
    Dcls<dclsCons<dclsCons<dclMod<dclsCons<main>>>>> ds35 = new dclsNil<dclsCons<dclsCons<dclMod<dclsCons<main>>>>>();
    VarRef<expRef<bnd<dclBind<dclsCons<dclsCons<dclMod<dclsCons<main>>>>>>>> vref_39 = new vref<expRef<bnd<dclBind<dclsCons<dclsCons<dclMod<dclsCons<main>>>>>>>>("b");
    Exp<bnd<dclBind<dclsCons<dclsCons<dclMod<dclsCons<main>>>>>>> expRef_38 = new expRef<bnd<dclBind<dclsCons<dclsCons<dclMod<dclsCons<main>>>>>>>(vref_39);
    Type intType_37 = new intType();
    Bind<dclBind<dclsCons<dclsCons<dclMod<dclsCons<main>>>>>> bnd_36 = new bnd<dclBind<dclsCons<dclsCons<dclMod<dclsCons<main>>>>>>("a", intType_37, expRef_38);
    Dcl<dclsCons<dclsCons<dclMod<dclsCons<main>>>>> d34 = new dclBind<dclsCons<dclsCons<dclMod<dclsCons<main>>>>>(bnd_36);
    Dcls<dclsCons<dclMod<dclsCons<main>>>> ds32 = new dclsCons<dclsCons<dclMod<dclsCons<main>>>>(d34, ds35);
    ModRef<dclImp<dclsCons<dclMod<dclsCons<main>>>>> mref_33 = new mref<dclImp<dclsCons<dclMod<dclsCons<main>>>>>("B");
    Dcl<dclsCons<dclMod<dclsCons<main>>>> d31 = new dclImp<dclsCons<dclMod<dclsCons<main>>>>(mref_33);
    Dcls<dclMod<dclsCons<main>>> ds30 = new dclsCons<dclMod<dclsCons<main>>>(d31, ds32);
    Dcl<dclsCons<main>> d29 = new dclMod<dclsCons<main>>("A", ds30);
    Dcls<main> ds53 = new dclsCons<main>(d29, ds52);
    Main m = new main(ds53);
    return m;
  }

  

}

