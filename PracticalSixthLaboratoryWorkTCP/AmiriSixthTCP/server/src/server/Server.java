package server;

import collection.CollectionManager;
import colorizedConsole.ConsoleColor;
import command.AbstractCommand;
import command.CommandManager;
import command.SaveCommand;
import connection.Request;
import connection.Response;
import file.FileManager;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;


public class Server {
    private int port;
    private CommandManager commandManager;
    private CollectionManager collectionManager;
    private FileManager fileManager;
    private SaveCommand saveCommand;
    private ServerSocketChannel socketChannel;
    public Server(CollectionManager collectionManager, CommandManager commandManager, FileManager fileManager, int port) {
        this.commandManager = commandManager;
        this.port = port;
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
        saveCommand = new SaveCommand(fileManager, collectionManager);
        initialize(port);
    }

    private void initialize(int port) {
        try {
            socketChannel = ServerSocketChannel.open();
            socketChannel.bind(new InetSocketAddress(port));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void run() {
        while (true) {
            try {
                System.out.println(ConsoleColor.YELLOW_BACKGROUND + "Waiting for a client to be connected !!!! " + ConsoleColor.RESET);
                SocketChannel socket = socketChannel.accept();
                if (socket != null && socket.isConnected()) {
                    System.out.println(ConsoleColor.GREEN_BACKGROUND + "Client with address of [" + socket.getRemoteAddress() + "] has been connected to the server" + ConsoleColor.RESET);
                    while (true) {

                        RequestReceiver receiver = new RequestReceiver(socket);
                        Request request = receiver.receive();

                        RequestProcessor processor = new RequestProcessor(request, commandManager);
                        Response response = processor.process();

                        System.out.println(ConsoleColor.GREEN_BACKGROUND + "Response send to " + ConsoleColor.RESET + ConsoleColor.MAGENTA_BOLD + socket.getRemoteAddress() + ConsoleColor.RESET + "\n" + response);

                        ResponseSender sender = new ResponseSender(socket, response);
                        sender.send();

                        System.out.println(ConsoleColor.GREEN_BOLD+"-".repeat(100)+ConsoleColor.RESET);
                    }
                }
            } catch (Exception exception) {
                SaveCommand save = new SaveCommand(fileManager, collectionManager);
                save.execute();
            }
        }
    }

}

class RequestReceiver {
    private SocketChannel socketChannel;
    public RequestReceiver(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    public Request receive() throws Exception {
        try {
            InputStream inputStream = socketChannel.socket().getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            return (Request) objectInputStream.readObject();
        } catch (Exception exception) {
            throw new Exception();
        }
    }
}

class RequestProcessor {
    private Request request;
    private CommandManager commandManager;
    public RequestProcessor(Request request, CommandManager commandManager) {
        this.request = request;
        this.commandManager = commandManager;
    }

    public Response process() {
        try {
            AbstractCommand c = commandManager.getCommandMap().get(request.getCommandName());
            c.setArg(request.getCommandArg());
            c.setPerson(request.getPerson());
            c.execute();
            return new Response(c.getResult());
        } catch (IllegalArgumentException exception){
            return new Response(ConsoleColor.RED_BACKGROUND+exception.getMessage()+ConsoleColor.RESET);
        } catch (Exception exception) {
            return new Response(ConsoleColor.RED_BACKGROUND + "[" + request.getCommandName() + "]" + " doesn't exist in command list!!!!" + ConsoleColor.RESET);
        }
    }
}

class ResponseSender {
    private SocketChannel socketChannel;
    private Response response;
    public ResponseSender(SocketChannel socketChannel, Response response) {
        this.socketChannel = socketChannel;
        this.response = response;
    }

    public void send() throws Exception {
        try {
            OutputStream outputStream = socketChannel.socket().getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(response);
        } catch (Exception exception) {
            throw new Exception();
        }
    }
}
