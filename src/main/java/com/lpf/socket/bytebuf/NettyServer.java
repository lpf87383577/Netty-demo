package com.lpf.socket.bytebuf;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * <p>
 *
 * </P>
 *
 * @author 18030213
 * @since 2021/3/9
 **/
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {

        // 创建两个线程组 bossGroup 和 workerGroup ，含有的子线程 NioEventLoop 的个数默认是CPU核数的两倍
        // bossGroup 只处理连接请求 真正和客户端业务处理的是 workerGroup
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(8);

        try {
            // 创建服务器启动对象
            ServerBootstrap bootstrap = new ServerBootstrap();

            // 使用链式编程来配置参数
            bootstrap.group(bossGroup,workerGroup)
                    // 使用 NioServerSocketChannel 来作为服务器的通道实现
                    .channel(NioServerSocketChannel.class)
                    // 初始化队列的大小，服务器处理客户端请求是顺序执行的，同一时间只能处理 workerGroup 线程组大小的请求，其他请求放在队列里面等待处理
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) {
                            // 对 workerGroup 的 SocketChannel 设置处理器
                            ch.pipeline().addLast(new ProtobufDecoder());
                        }
                    });


            System.out.println("netty server start");
            //绑定一个端口并且同步, 生成了一个ChannelFuture异步对象，通过isDone()等方法可以判断异步事件的执行情况
            //启动服务器(并绑定端口)，bind是异步操作，sync方法是等待异步操作执行完毕
            ChannelFuture cf = bootstrap.bind(9000).sync();

            //对通道关闭进行监听，closeFuture是异步操作，监听通道关闭
            // 通过sync方法同步等待通道关闭处理完毕，这里会阻塞等待通道关闭完成
            cf.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully().sync();
            workerGroup.shutdownGracefully().sync();
        }
    }

}
