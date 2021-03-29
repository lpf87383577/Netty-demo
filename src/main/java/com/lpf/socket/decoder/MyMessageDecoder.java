package com.lpf.socket.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.util.List;

public class MyMessageDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println();
        System.out.println("MyMessageecoder decode 被调用");

        int byteLength = in.readableBytes();
        //需要将得到二进制字节码-> MyMessageProtocol 数据包(对象)
        System.out.println(byteLength);

        byte[] content = new byte[byteLength];

        in.readBytes(content);

        //封装成MyMessageProtocol对象，传递到下一个handler业务处理
        MyMessageProtocol messageProtocol = new MyMessageProtocol();
        messageProtocol.setLen(byteLength);
        messageProtocol.setContent(content);

        out.add(messageProtocol);

    }
}
