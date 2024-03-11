package client;

import colorizedConsole.ConsoleColor;
import connection.Request;
import connection.Response;
import input.InputDataManager;
import modules.Person;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Client {
    private int port;
    private SocketChannel socketChannel;

    public Client(int port) {
        this.port = port;
        intialize(port);
    }

    private void intialize(int port) {
        try {
            socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("localhost", port));
        } catch (Exception exception) {
            System.out.println(ConsoleColor.RED_BACKGROUND + "Couldn't connect to server" + ConsoleColor.RESET);
        }
    }

    public void run() {
        while (true) {
            if (!socketChannel.isConnected()) break;
            RequestCreator creator = new RequestCreator();
            Request request = creator.create();

            if (request.getCommandName().equals("exit")) break;

            RequestSender requestSender = new RequestSender(socketChannel, request);
            requestSender.send();

            ResponseReceiver receiver = new ResponseReceiver(socketChannel);
            Response response = receiver.receive();
            if (response != null)
                System.out.println(response + "\n");
            else break;
        }
    }
}

class RequestCreator {
    private Scanner scanner;

    public RequestCreator() {
        this.scanner = new Scanner(System.in);
    }

    protected Request create() {
        System.out.println(ConsoleColor.ORANGE_BOLD + "\nEnter command (help to get help) = " + ConsoleColor.RESET);
        String input = scanner.nextLine();
        String command = "";
        String arg = "";
        Person person = null;
        String[] commandAndArg = input.trim().split(" ", 2);
        if (commandAndArg.length > 1) {
            command = commandAndArg[0];
            arg = commandAndArg[1];
        } else command = commandAndArg[0];
        if (command.equals("add") || command.equals("add_if_min") || command.equals("update")
                || command.equals("remove_greater") || command.equals("remove_lower")) {
            person = new InputDataManager().readPerson();
        }
        return new Request(command, arg, person);
    }
}

class RequestSender {
    private SocketChannel socketChannel;
    private Request request;

    public RequestSender(SocketChannel socketChannel, Request request) {
        this.socketChannel = socketChannel;
        this.request = request;
    }

    protected void send() {
        try {
            OutputStream outputStream = socketChannel.socket().getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(request);
        } catch (Exception exception) {
            System.out.println(ConsoleColor.RED_BACKGROUND + exception.getMessage() + ConsoleColor.RESET);
        }
    }

}

class ResponseReceiver {
    private SocketChannel socketChannel;

    public ResponseReceiver(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    protected Response receive() {
        try {
            InputStream inputStream = socketChannel.socket().getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            return (Response) objectInputStream.readObject();
        } catch (Exception exception) {
            System.out.println(ConsoleColor.RED_BACKGROUND + exception.getMessage() + ConsoleColor.RESET);
        }
        return null;
    }
}

