package client;

import colorizedConsole.ConsoleColor;
import input.InputDataManager;
import modules.Person;
import networkConnection.Request;
import networkConnection.Response;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    public Client(int port, String hostName) {
        initialize(port, hostName);
    }

    private void initialize(int port, String hostName) {
        try {
            socket = new Socket(hostName, port);
        } catch (Exception exception) {
            System.out.println(ConsoleColor.RED_BACKGROUND + exception.getMessage() + ConsoleColor.RESET);
        }
    }
    public void run (){
        RequestCreator requestCreator = new RequestCreator();
        RequestSender sender =  new RequestSender(socket);
        ResponseReceiver receiver = new ResponseReceiver(socket);
        while (true){
            Request request = requestCreator.create();
            if (request.getCommandName().equals("exit")) break;
            else {
                sender.send(request);
                Response response = receiver.receive();
                System.out.println(response.getMessage());
            }
        }
    }
}

class RequestCreator {
    private Scanner scanner;
    private InputDataManager inputManager;

    public RequestCreator() {
        scanner = new Scanner(System.in);
        inputManager = new InputDataManager();
    }

    public Request create() {
        System.out.println(ConsoleColor.CYAN_BOLD + "Enter a command (help to get help ) : "+ConsoleColor.RESET);
        String commandAndArg = scanner.nextLine();
        String[] spilt = commandAndArg.trim().split(" ", 2);
        String command = spilt[0];
        String arg = null;
        Person person = null;
        if (commandAndArg.contains(" ")) {
            arg = spilt[1];
        }
        if (command.equals("add") || command.equals("add_if_min") || command.equals("remove_lower")
                || command.equals("remove_greater") || command.equals("update")) {
            person = inputManager.readPerson();
        }
        return new Request (command,arg,person);
    }
}
class RequestSender {
    private Socket socket;
    public RequestSender (Socket socket){
        this.socket = socket;
    }
    public void send (Request request){
        try {
            OutputStream inputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(inputStream);
            objectOutputStream.writeObject(request);
        }catch (Exception exception){
            System.out.println(ConsoleColor.RED_BACKGROUND+exception.getMessage()+ConsoleColor.RESET);
        }
    }
}
class ResponseReceiver {
    private Socket socket;
    public ResponseReceiver (Socket socket){
        this.socket = socket;
    }
    public Response receive (){
        try{
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Object obj = objectInputStream.readObject();
            if (obj instanceof Response){
                return (Response) obj;
            } else throw new RuntimeException("");
        }catch (Exception exception){
            System.out.println(ConsoleColor.RED_BACKGROUND+exception.getMessage()+ConsoleColor.RESET);
        }
        return null;
    }
}