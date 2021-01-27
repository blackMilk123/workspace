package com.study.lambda;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @program: workspace
 * @description:
 * @author: Y-J
 * @create: 2021-01-27 22:13
 **/
public class NIOEchoServer {
    public static void main(String[] args) throws IOException {
        //创建一个selector
        Selector selector = Selector.open();

        //创建一个ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //绑定端口
        serverSocketChannel.bind(new InetSocketAddress(8002));
        //设置为非阻塞模式
        serverSocketChannel.configureBlocking(false);
        //将channel注册到selector上 ,并注册accept事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("服务器启动");
        while (true){
            selector.select();

            //查找就绪的channel
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            //遍历selectkeys
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                //如果是accept事件
                if (selectionKey.isAcceptable()){
                    ServerSocketChannel ssc = (ServerSocketChannel)selectionKey.channel();
                    SocketChannel socketChannel = ssc.accept();

                    System.out.println("接收到新连接"+socketChannel.getRemoteAddress());
                    socketChannel.configureBlocking(false);
                    //将socketChannel注册到selector上 ,并注册read事件
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }else if (selectionKey.isReadable()){
                   SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                   //创建buffer读取数据
                    ByteBuffer buffer = ByteBuffer.allocate(1024);

                    int length = socketChannel.read(buffer);
                    if (length>0){
                        buffer.flip();
                        byte[] bytes = new byte[buffer.remaining()];

                        buffer.get(bytes);

                        //处理换行符
                        String msg = new String(bytes, "UTF-8").replace("\r\n", "");
                        System.out.println("接收到消息"+msg);
                    }
                }
                iterator.remove();
            }
        }

    }
}
