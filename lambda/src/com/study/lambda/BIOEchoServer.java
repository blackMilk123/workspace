package com.study.lambda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: workspace
 * @description:
 * @author: Y-J
 * @create: 2021-01-27 22:00
 **/
public class BIOEchoServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9972);

        System.out.println("服务启动成功......");

        while (true){
            Socket socket = serverSocket.accept();
            System.out.println("用户["+socket.getPort()+"]进入了房间。。。");

            new Thread(()->{
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String msg;
                    while ((msg = reader.readLine() )!= null){
                        System.out.println("["+socket.getPort()+"]发送消息:"+msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
