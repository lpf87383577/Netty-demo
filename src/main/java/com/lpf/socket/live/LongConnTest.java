package com.lpf.socket.live;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Scanner;

/**
 * <p>
 *
 * </P>
 *
 * @author 18030213
 * @since 2021/3/26
 **/
public class LongConnTest {

    private Logger logger = LoggerFactory.getLogger(LongConnTest.class);

    String host = "localhost";
    int port = 9000;

    public void testLongConn() throws Exception {
        logger.debug("start");
        final Socket socket = new Socket();
        socket.connect(new InetSocketAddress(host, port));
        Scanner scanner = new Scanner(System.in);
        new Thread(() -> {
            while (true) {
                try {
                    byte[] input = new byte[64];
                    int readByte = socket.getInputStream().read(input);
                    logger.debug("readByte " + readByte);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        int code;
        while (true) {
            code = scanner.nextInt();
            logger.debug("input code:" + code);
            if (code == 0) {
                break;
            } else if (code == 1) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(5);
                byteBuffer.put((byte) 1);
                byteBuffer.putInt(0);
                socket.getOutputStream().write(byteBuffer.array());
                logger.debug("write heart finish!");
            } else if (code == 2) {
                byte[] content = ("hello, I'm" + hashCode()).getBytes();
                ByteBuffer byteBuffer = ByteBuffer.allocate(content.length + 5);
                byteBuffer.put((byte) 2);
                byteBuffer.putInt(content.length);
                byteBuffer.put(content);
                socket.getOutputStream().write(byteBuffer.array());
                logger.debug("write content finish!");
            }
        }
        socket.close();
    }

    // 因为Junit不支持用户输入,所以用main的方式来执行用例
    public static void main(String[] args) throws Exception {
        new LongConnTest().testLongConn();
    }
}
