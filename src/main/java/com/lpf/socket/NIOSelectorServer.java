package com.lpf.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @Description
 * @Author 18030213
 * @Date 2021/2/9
 * @Version v1.0
 **/
public class NIOSelectorServer {


    public static void main(String[] args) throws IOException {

        // 创建NIO的链接
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(9000));

        // 设置为非阻塞，如果设置为true，serverSocket.accept()会和BIO一样阻塞起来
        serverSocket.configureBlocking(false);

        // 打开selector处理serverSocket，即创建epoll
        Selector selector = Selector.open();

        // 将serverSocket注册到selector上面，并且selector对客户端 accept 连接感兴趣
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("服务启动成功");

        while (true) {

            // 阻塞等待事件发生，如果没有事件发生，这里会阻塞住，直到有需要处理的事件到来
            selector.select();

            // 获取selector注册中的全部事件的 selectedKeys 实例
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()) {

                SelectionKey key = iterator.next();

                // 如果是 accept 事件，进行连接获取和事件注册
                if (key.isAcceptable()){

                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();

                    SocketChannel socketChannel = serverSocketChannel.accept();

                    socketChannel.configureBlocking(false);

                    // 将 socketChannel 读事件 注册到 selector 中，后续的该 socketChannel 读事件才会在 selector 有所响应
                    socketChannel.register(selector,SelectionKey.OP_READ);

                    System.out.println("客户端连接成功");
                } // 如果是可读事件，进行读取操作
                else if (key.isReadable()){

                    SocketChannel socketChannel = (SocketChannel)key.channel();

                    ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                    //  设置为非阻塞,read不会阻塞
                    int len = socketChannel.read(byteBuffer);
                    // 如果数据长度 > 1 说明有数据，打印数据，如果 == 0 说明连接还在，但是暂时没有数据传输过来
                    if (len > 0) {
                        System.out.println("接收到消息：" + new String(byteBuffer.array()));
                    }// == -1 断开连接的标志
                    else if (len == -1) {
                        // 断开连接，关闭socket
                        socketChannel.close();
                        System.out.println("客户端端口连接");
                    }
                }

                // 移除本次事件，防止下次重复执行
                iterator.remove();
            }
        }
    }
}
