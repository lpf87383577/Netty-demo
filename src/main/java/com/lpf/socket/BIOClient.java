package com.lpf.socket;


import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class BIOClient {
    public static void main(String[] args) throws Exception{

        Socket socket = new Socket("localhost", 9000);

        try {

            // 发送数据hello
            socket.getOutputStream().write("hello".getBytes());

            System.out.println("发送数据hello");

            byte[] bytes = new byte[1024];

            int read = socket.getInputStream().read(bytes);

            if (read != -1) {
                System.out.println("接收服务端回应： " + new String(bytes));
            }
            socket.getOutputStream().flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}