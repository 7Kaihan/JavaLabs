package client;

import common.connection.Request;
import common.connection.RequestMessage;
import common.connection.Response;
import common.modules.Student;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String [] args){
        try{
            Socket socket = new Socket("localhost",9000);
            Student student = new Student(10,"John","Williams");

            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            String command = "";
            while(!command.equals("exit")) {
                System.out.println("Enter a command = ");
                command = new Scanner(System.in).nextLine();

                if(command.equals("add")) {
                    Request request = new RequestMessage("add", "", student);
                    objectOutputStream.writeObject(request);
                }
                if(command.equals("")){
                    System.err.println("fuck you, input again");
                    continue;
                }

                InputStream inputStream = socket.getInputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                Response response = (Response) objectInputStream.readObject();

                System.out.println(response.getMessage());
            }
            socket.close();
        }catch (IOException exception){
            System.err.println("some problems occurred");
        }catch (ClassNotFoundException exception){
            System.err.println("can't find the class");
        }
    }
}
