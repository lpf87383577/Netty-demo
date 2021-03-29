package com.lpf.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description
 * @Author 18030213
 * @Date 2021/2/9
 * @Version v1.0
 **/
public class NIOServer {

    static List<SocketChannel> channelList = new ArrayList<SocketChannel>();

    public static void main(String[] args) throws IOException {

        // 创建NIO的链接
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(9000));
        // 设置为非阻塞，如果设置为true，serverSocket.accept()会和BIO一样阻塞起来
        serverSocket.configureBlocking(false);
        System.out.println("服务启动成功");

        while (true) {

            // 设置serverSocket.configureBlocking(false)后，serverSocket.accept()就不会阻塞
            SocketChannel socketChannel = serverSocket.accept();

            if (socketChannel != null) {
                System.out.println("连接成功");
                // 设置为非阻塞,sc.read(byteBuffer)这段代码不会阻塞
                socketChannel.configureBlocking(false);
                channelList.add(socketChannel);
            }
            Iterator<SocketChannel> iterator = channelList.iterator();
            while (iterator.hasNext()) {
                SocketChannel sc = iterator.next();
                ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                //  设置为非阻塞,read不会阻塞
                int len = sc.read(byteBuffer);
                // 如果数据长度 > 1 说明有数据，打印数据，如果 == 0 说明连接还在，但是暂时没有数据传输过来
                if (len > 0) {
                    System.out.println("接收到消息：" + new String(byteBuffer.array()));
                }// == -1 断开连接的标志
                else if (len == -1) {
                    // 断开连接，关闭socket
                    sc.close();
                    iterator.remove();
                    System.out.println("客户端端口连接");
                }
            }
        }
    }
}
