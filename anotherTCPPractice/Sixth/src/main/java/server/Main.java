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
    public static void main(String[] args) throws IOException {
        CollectionManager<Student> collectionManager = new StudentCollectionManager();
        try{
            ServerSocket serverSocket = new ServerSocket(9000);

            while(true){
                System.out.println("Waiting for a Client");
                Socket socket = serverSocket.accept();
                System.out.println("A client with IP of "+socket.getInetAddress()+" has been connected...");

                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                while(true) {

                    Request request = (Request) objectInputStream.readObject();

                    if (request.getCommandName().equals("add")) {
                        Command add = new AddCommand(collectionManager);

                        add.setObjectArgument(Optional.of(request.getObject()));

                        Response response = new ResponseMessage(add.execute());

                        objectOutputStream.writeObject(response);
                    }
                    if (!socket.isConnected())
                        continue;
                    if (request.getCommandName().equals("exit"))
                        break;
                }
            }
        }catch (Exception exception){
            System.err.println("something went wrong");
        }
    }
}
