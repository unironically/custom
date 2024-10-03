package lm;

abstract class List<T> {

  protected T h;
  protected List<T> t;

  public List(T h, List<T> t) {
    this.h = h;
    this.t = t;
  }

  public T getHead() {
    return this.h;
  }

  public List<T> getTail() {
    return this.t;
  }

  public static <T> T head(List<T> lst) {
    return lst.getHead();
  }

  public static <T> List<T> tail(List<T> lst) {
    return lst.getTail();
  }

}

class listCons<T> extends List<T> {
  public listCons(T h, List<T> t) {
    super(h, t);
  }

}

class listNil<T> extends List<T> {
  public listNil() {
    super(null, null);
  }
}