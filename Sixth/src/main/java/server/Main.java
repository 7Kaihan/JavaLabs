package server;


import collection.CollectionManager;
import collection.StudentCollectionManager;
import command.AddCommand;
import command.Command;
import common.connection.Request;
import common.connection.Response;
import common.connection.ResponseMessage;
import common.modules.Student;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Optional;
public class Main {
    public static void main(String [] args){
        ServerSocket serverSocket;
        Socket socket;
        ObjectInputStream objectInputStream;
        ObjectOutputStream objectOutputStream;
        Request request;
        try{
            serverSocket = new ServerSocket(9000);
            CollectionManager<Student> collectionManager = new StudentCollectionManager();
            while(true){
                System.out.println("Waiting for a client to be connected");
                socket = serverSocket.accept();

                System.out.println("A client with Ip of "+socket.getInetAddress()+" has been connected....");

                InputStream inputStream = socket.getInputStream();
                objectInputStream = new ObjectInputStream(inputStream);
                request = (Request) objectInputStream.readObject();

                if(request.getCommandName().equals("add")){
                    Command addCommand = new AddCommand(collectionManager);

                    java.util.Optional<Student> container = Optional.of(request.getObject());

                    addCommand.setObjectArgument(container);
                    String res = addCommand.execute();

                    Response response = new ResponseMessage(res);

                    OutputStream outputStream = socket.getOutputStream();
                    objectOutputStream = new ObjectOutputStream(outputStream);
                    objectOutputStream.writeObject(response);
                    objectOutputStream.flush();
                    objectOutputStream.close();
                }

            }
        }catch (IOException exception){
            System.err.println("Something went wrong");
        }catch (ClassNotFoundException exception){
            System.err.println("can't find the student class");
        }
    }
}
