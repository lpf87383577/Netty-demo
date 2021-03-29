package com.lpf.socket.netty;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.nio.ByteBuffer;

/**
 * <p>
 *
 * </P>
 *
 * @author 18030213
 * @since 2021/3/9
 **/
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 客户端连接时触发方法
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("有新的客户端连接");
    }

    /**
     * 接收到客户端消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("接收到客户端消息" + byteBuf.toString(CharsetUtil.UTF_8));
    }



}
