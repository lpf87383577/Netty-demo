package com.lpf.socket;


import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class BIOServer {

    public static void main(String[] args) throws Exception {
        ServerSocket socket = new ServerSocket(9001);
        System.out.println("服务端已经启动");
        boolean flag = true;
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        while (flag) {
            Socket client = socket.accept();
            executorService.submit(new EchoClientHandler(client));
        }
        executorService.shutdown();
        socket.close();
    }

    private static class EchoClientHandler implements Runnable {

        private Socket socket;
        public EchoClientHandler(Socket socket) {
            this.socket = socket;
        }
        public void run() {
            try {
                byte[] bytes = new byte[1024];
                System.out.println("准备读数据");
                int read = socket.getInputStream().read(bytes);
                if (read != -1) {
                    System.out.println("接收客户端数据： " + new String(bytes));
                    socket.getOutputStream().write("接收到数据".getBytes());
                    socket.getOutputStream().flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}