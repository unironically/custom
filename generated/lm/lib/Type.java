package lm;

abstract class Type<T extends hasChild_Type<T>> extends TreeNode<T> {

}

class intType<T extends hasChild_Type<T>> extends Type<T> {

  public intType() {

  }

}

class boolType<T extends hasChild_Type<T>> extends Type<T> {

  public boolType() {

  }

}

class listType<T extends hasChild_Type<T>> extends Type<T> {

  public listType() {

  }

}

class errType<T extends hasChild_Type<T>> extends Type<T> {

  public errType() {

  }

}