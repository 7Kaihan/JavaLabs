package server;

import colorizedConsole.ConsoleColor;
import commands.AbstractCommand;
import commands.CommandManager;
import dataBase.*;
import modules.Person;
import networkConnection.Request;
import networkConnection.Response;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.Set;

public class Server {
    private ServerSocket serverSocket;
    private CommandManager commandManager;
    private static String hostName;
    private static String databaseName;
    private static String userName;
    private static String password;

    public Server(int port, CommandManager commandManager) {
        initialize(port);
        setDatabaseFields();
        this.commandManager = commandManager;
    }

    private void setDatabaseFields() {
        hostName = "localhost";
        databaseName = "person";
        userName = "postgres";
        password = "1234";
    }

    private void initialize(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (Exception exception) {
            System.out.println(ConsoleColor.RED_BACKGROUND + exception.getMessage() + ConsoleColor.RESET);
        }
    }

    public void run() {
        ConnectionHandler handler = new ConnectionHandler(hostName, databaseName, userName, password);
        Connection connection = handler.getConnection();
        TablesCreater tablesCreater = TablesCreater.getInstance();
        tablesCreater.createPersonTable(connection);
        EntityDeserializer deserializer = new EntityDeserializer(connection);
        Set<Person> personSet = deserializer.deserialize();
        if (personSet.isEmpty()) {
            System.out.println(ConsoleColor.YELLOW_BACKGROUND + "Database was empty" + ConsoleColor.RESET);
        } else {
            commandManager.getCollectionManager().setCollection(personSet);
            commandManager.getCollectionManager().setUniqueIds(deserializer.getUniqueIds());
            System.out.println(ConsoleColor.GREEN_BACKGROUND + "Collection has been successfully loaded from elements in database" + ConsoleColor.RESET);
        }
        PersonDataBaseManager manager = new PersonDataBaseManager(connection);
        while (true) {
            try {
                System.out.println(ConsoleColor.YELLOW_BACKGROUND + "Waiting for a client to be connected !!" + ConsoleColor.RESET);
                Socket socket = serverSocket.accept();
                System.out.println(ConsoleColor.GREEN_BACKGROUND + "Client with address of [" + socket.getLocalSocketAddress() + ConsoleColor.RESET);
                RequestReceiver receiver = new RequestReceiver(socket);
                RequestProcessor processor = new RequestProcessor(commandManager);
                ResponseSender sender = new ResponseSender(socket);
                while (true) {
                    try {
                        Request request = receiver.receive();
                        Response response = processor.process(request);
                        sender.send(response);
                        System.out.println(response.getMessage());
                    } catch (RuntimeException exception) {
                        Response response = new Response(ConsoleColor.RED_BACKGROUND + "No Such command" + ConsoleColor.RESET);
                        sender.send(response);
                        System.out.println(response.getMessage() + ConsoleColor.RED_BACKGROUND + " has been send to " + socket.getInetAddress() + ConsoleColor.RESET);
                    } catch (Exception exception) {
                        break;
                    }
                }
                manager.clear();
                commandManager.getCollectionManager().getCollection().forEach(manager::add);
            }catch (Exception exception){
                System.out.println();
            }
        }
    }
}

class RequestReceiver {
    private Socket socket;

    public RequestReceiver(Socket socket) {
        this.socket = socket;
    }

    public Request receive() throws Exception{
        try {
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Object obj = objectInputStream.readObject();
            if (obj instanceof Request) {
                return (Request) obj;
            } else throw new RuntimeException("Wrong object");
        } catch (Exception exception) {
            System.out.println(ConsoleColor.RED_BACKGROUND + exception.getMessage() + ConsoleColor.RESET);
            throw new Exception();
        }
    }
}

class RequestProcessor {
    private CommandManager commandManager;

    public RequestProcessor(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public Response process(Request request) {
        try {
            AbstractCommand c = commandManager.getCommandMap().get(request.getCommandName());
            c.setArg(request.getArg());
            c.setPerson(request.getPerson());
            c.execute();
            Response response = new Response(c.getResult());
            return response;
        } catch (Exception exception) {
            throw new RuntimeException();
        }
    }
}

class ResponseSender {
    private Socket socket;

    public ResponseSender(Socket socket) {
        this.socket = socket;
    }

    public void send(Response response) {
        try {
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(response);
        } catch (Exception exception) {
            System.out.println(ConsoleColor.RED_BACKGROUND + exception.getMessage() + ConsoleColor.RESET);
        }
    }
}
