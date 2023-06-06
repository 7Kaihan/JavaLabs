package client;


import common.connection.Request;
import common.connection.RequestMessage;
import common.connection.Response;
import common.connection.ResponseMessage;
import common.modules.Student;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String [] args){
        try{
            Socket socket = new Socket("localhost",9000);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            while(true){
                System.out.println("enter command name = ");
                String commandName = new Scanner(System.in).nextLine();
                if(commandName.equals("add")){
                    System.out.println("Enter student id = ");
                    int id = new Scanner(System.in).nextInt();
                    System.out.println("Enter student name = ");
                    String name = new Scanner(System.in).nextLine();
                    System.out.println("Enter student lastName = ");
                    String lastName = new Scanner(System.in).nextLine();

                    Student student = new Student(id,name,lastName);

                    RequestMessage request = new RequestMessage(commandName,null,student );

                    objectOutputStream.writeObject(request);

                    ResponseMessage text = (ResponseMessage)objectInputStream.readObject();

                    System.out.println(text.getMessage());
                }
               if(commandName.equals("exit"))
                   break;
            }
        }catch (Exception exception){
            System.err.println(exception.getMessage());
        }
    }
}
