package com.imooc.sell;

public class StaticInitData {


    public static void main(String[] args) {
        System.out.println("t1引用前");
        t1.func("01");
        System.out.println("c1引用前");
        c1.func("01");
    }
    static  Table t1= new Table();
    static Cupboard c1 = new Cupboard();
}
class Bowl{
    Bowl(String flag){
        System.out.println("bowl构造方法"+flag);
    }

    void func(String flag){
        System.out.println("bowl成员方法"+flag);
    }
}

class Table{
    static Bowl b1 = new Bowl("bowl01");
    String flag = "---变量定义和代码块顺序的测试---";
    Table(){
        System.out.println("Table构造方法");
       b2.func("table构造方法调用");
    }
    static {
        System.out.println("Table静态代码块执行");
    }

    {
        System.out.println("Table普通代码块执行"+flag);
    }
     static  Bowl b2 = new Bowl("bowl02");
    void func(String flag){
        System.out.println("Table成员方法"+flag);
    }
}
class Cupboard{

    Bowl b3 = new Bowl("bowl03");
    static  Bowl b4  = new Bowl("bowl04");
    Cupboard(){
        System.out.println("cupboard构造方法");
        b4.func("cupboard构造方法调用");
    }
    void func(String flag){
        System.out.println("cupboard成员方法"+flag);
    }
    static  Bowl b5  = new Bowl("bowl05");
}
