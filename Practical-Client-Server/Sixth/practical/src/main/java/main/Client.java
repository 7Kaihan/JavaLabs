package main;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
//        Socket socket = null;
//        BufferedWriter writer = null;
//        BufferedReader reader = null;
//        Scanner input = new Scanner(System.in);
//        try{
//            socket = new Socket("localhost",23589);
//            socket.connect(new InetSocketAddress("localhost",1234));
//            while (socket.getChannel().isConnectionPending()) {
//                var finished = socketChannel.finishConnect();
//            }
//            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//            while(true){
//                System.out.println("Enter something = ");
//                String s = input.nextLine();
//
//                if(s.equals("exit"))
//                    break;
//                writer.write(s);
//                writer.newLine();
//                writer.flush();
//
//                String text = reader.readLine();
//
//                System.out.println(text);
//            }
//        }catch (IOException exception){
//            exception.printStackTrace();
//        }finally {
//            try {
//                if (socket != null)
//                    socket.close();
//                if(writer != null)
//                    writer.close();
//            }catch (Exception exception){
//                exception.printStackTrace();
//            }
        try (var socket = start()) {

        }
    }

    private static SocketChannel socketChannel;

    synchronized static public SocketChannel start() {
        for (; ; ) {
            try {
                if (socketChannel != null && socketChannel.isOpen())
                    socketChannel.close();
                socketChannel = SocketChannel.open();
                // var port = SettingsContainer.settingsModel.localPort;
                socketChannel.socket().setReuseAddress(true);
                socketChannel.configureBlocking(false);
                socketChannel.connect(new InetSocketAddress("localhost", 1234));
                while (socketChannel.isConnectionPending()) {
                    var finished = socketChannel.finishConnect();
                }
                // if because of called finishConnect the connection got closed we are trying to make another connection
                if (!socketChannel.isOpen()) {
                    Thread.sleep(3000);
                    //logger.info("not connected, waiting..");
                    continue;
                }
                return socketChannel;
            } catch (Exception e) {
                //logger.info("TCP server: " + e.getMessage());
                try {
                    socketChannel.close();
                    Thread.sleep(3000);
                } catch (Exception e1) {
                    //logger.info("TCP server: " + e1.getMessage());
                }
            }
        }
    }
}
