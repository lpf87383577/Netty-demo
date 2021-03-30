package com.lpf.socket.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * <p>
 *
 * </P>
 *
 * @author 18030213
 * @since 2021/3/19
 **/
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 当客户端连接服务器完成就会触发该方法
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        for (int i = 0; i < 20; i++) {
            //Thread.sleep(100);
            ByteBuf buf = Unpooled.copiedBuffer("lpf", CharsetUtil.UTF_8);
            ctx.writeAndFlush(buf);
        }
/*
        ByteBuf buf = Unpooled.copiedBuffer("0测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码1测试代码测试代码测试代码测试代码" +
                "测试代码测试代码测试代码测试代码2测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码0测试代码测试代码测试代码测试代码测" +
                "试代码测试代码测试代码测试代码测试代码测试代码1测试代码测试代码测试代码测试代码\" +\n" +
                "                    \"测试代码测试代码测试代码测试代码2测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码" +
                "0测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码1测试代码测试代码测试代码测试代码\" +\n" +
                "                    \"测试代码测试代码测试代码测试代码2测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码" +
                "0测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码1测试代码测试代码测试代码测试代码\" +\n" +
                "                    \"测试代码测试代码测试代码测试代码2测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码" +
                "0测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码1测试代码测试代码测试代码测试代码\" +\n" +
                "                    \"测试代码测试代码测试代码测试代码2测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码" +
                "0测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码1测试代码测试代码测试代码测试代码\" +\n" +
                "                    \"测试代码测试代码测试代码测试代码2测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码" +
                "0测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码1测试代码测试代码测试代码测试代码\" +\n" +
                "                    \"测试代码测试代码测试代码测试代码2测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码" +
                "0测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码1测试代码测试代码测试代码测试代码\" +\n" +
                "                    \"测试代码测试代码测试代码测试代码2测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码", CharsetUtil.UTF_8);

        ctx.writeAndFlush(buf);*/

    }

    //当通道有读取事件时会触发，即服务端发送数据给客户端
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
