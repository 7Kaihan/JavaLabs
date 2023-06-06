package main;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String []args) throws IOException {
        ServerSocket serverSocket = null;
        Socket socket;
        BufferedReader reader;
        BufferedWriter writer;
        serverSocket = new ServerSocket(1234);
        while(true){
            System.out.println("waiting for a client to be connected ");

            socket = serverSocket.accept();

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            while(true){
                String s = reader.readLine();

                System.out.println("client : "+s);

                writer.write(" your message has been received");
                writer.newLine();
                writer.flush();

                if(s.equals("exit"))
                    break;
            }

            socket.close();
            reader.close();
            writer.close();

        }
    }
}
