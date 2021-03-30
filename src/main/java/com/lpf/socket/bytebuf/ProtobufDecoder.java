package com.lpf.socket.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

/**
 * <p>
 *
 * </P>
 *
 * @author 18030213
 * @since 2021/3/30
 **/
public class ProtobufDecoder extends ByteToMessageDecoder {

    int i = 0;

    protected void decode(ChannelHandlerContext ctx, ByteBuf in,
                          List<Object> out) throws Exception {

        i++;

        Thread.sleep(1000);
        System.out.println("接收到客户端消息" + i + in.toString(CharsetUtil.UTF_8));

        ByteBuf byteBuf = in.readBytes(in.readableBytes());

        //System.out.println("接收到客户端消息" + i + "--" + byteBuf.toString(CharsetUtil.UTF_8));

    }

}
