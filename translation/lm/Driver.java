package lm;

public class Driver {
   public static void main(String[] args) {
    
    System.out.println("Hello, World");


    /* LM program:

    def a = 1
    def b = a

    */

    Exp<bnd>   e1 = new expInt<bnd>(1);
    Bind<dclBind>  b1 = new bnd<dclBind>("a", e1);
    Dcl<dclsCons>   d1 = new dclBind<dclsCons>(b1); 

    VarRef<expRef> r = new vref<expRef>("a");
    Exp<bnd> e2 = new expRef<bnd>(r);
    Bind<dclBind> b2 = new bnd<dclBind>("b", e2);
    Dcl<dclsCons> d2 = new dclBind<dclsCons>(b2);
      
    Dcls<dclsCons> ds2 = new dclsCons<dclsCons>(d2, new dclsNil<dclsCons>());

    Dcls<main> ds1 = new dclsCons<main>(d1, ds2);
      
    Main  m = new main(ds1);

   }
}