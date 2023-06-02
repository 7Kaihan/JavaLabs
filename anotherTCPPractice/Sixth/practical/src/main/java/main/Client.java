package main;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String [] args){
        Socket socket = null;
        BufferedWriter writer = null;
        BufferedReader reader = null;
        Scanner input = new Scanner(System.in);
        try{
            socket = new Socket("localhost",1234);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while(true){
                System.out.println("Enter something = ");
                String s = input.nextLine();

                if(s.equals("exit"))
                    break;
                writer.write(s);
                writer.newLine();
                writer.flush();

                String text = reader.readLine();

                System.out.println(text);
            }
        }catch (IOException exception){
            exception.printStackTrace();
        }finally {
            try {
                if (socket != null)
                    socket.close();
                if(writer != null)
                    writer.close();
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }
}
