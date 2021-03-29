package com.lpf.socket.decoder;


import com.lpf.socket.splitpacket.MyMessageProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.net.Socket;

public class BIOClient {
    public static void main(String[] args) throws Exception{

        Socket socket = new Socket("localhost", 9000);

        try {
            String msg = "hello刘鹏飞";


            // 发送数据hello
            socket.getOutputStream().write(msg.getBytes(CharsetUtil.UTF_8));

            System.out.println( msg.getBytes(CharsetUtil.UTF_8).length);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}