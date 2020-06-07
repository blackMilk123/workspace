package com.imooc.sell;

 class initData {

    public static void main(String[] args) {

        Card card = new Card();
        card.func();
    }
}
class Tag{
    Tag(String flag){
        System.out.println("tag构造函数"+flag);
    }
}

class Card{
    Tag t1 = new Tag("tag1");
    Card(){
        System.out.println("Card构造函数");
         t3 = new Tag("tag300");
    }
    Tag t2 = new Tag("tag2");
    void func(){
        System.out.println("成员方法");
    }
    Tag t3 = new Tag("tag3");
}

