package com.black.random;

  class ReadThread extends Thread {

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()){
            if (flag){ //1
                System.out.println(num+num); //2
            }
            System.out.println("读线程。。。。。。");
        }
    }

    public static  int num = 0;
    public static  boolean flag = false;


     static class WriteTheed extends  Thread{
         @Override
         public void run() {
             num = 2; //3
             flag = true; //4
         }
     }


      public static void main(String[] args) throws InterruptedException {
          ReadThread readThread = new ReadThread();
          readThread.start();

          WriteTheed wirteThred = new WriteTheed();
          wirteThred.start();

          Thread.sleep(1000);
          readThread.interrupt();
          System.out.println("主函数退出");

      }
}

