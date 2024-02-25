package client;

import connection.Request;
import connection.Response;
import input.InputDataManager;
import modules.Person;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Client {
    private int port;
    private DatagramChannel channel;
    private SocketAddress socketAddress;

    public Client(int port) {
        this.port = port;
        initialize();
    }

    private void initialize() {
        try {
            channel = DatagramChannel.open();
            channel.socket().setSoTimeout(3000);
            socketAddress = new InetSocketAddress(InetAddress.getByName("localhost"), port);
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
        }
    }

    public void run() {
        while (true){
            RequestCreator creator = new RequestCreator();
            Request request = creator.create();

            RequestSender sender = new RequestSender(request,channel,socketAddress);
            sender.send();

            ResponseReceiver receiver = new ResponseReceiver(channel);
            receiver.receive().forEach(System.out::println);
        }
    }

}

class RequestCreator {
    private InputDataManager dataManager;
    private Scanner scanner;

    public RequestCreator() {
        dataManager = new InputDataManager();
        scanner = new Scanner(System.in);
    }

    public Request create() {
        System.out.println("Enter a command (help to get help) :");
        String input = scanner.nextLine();
        String command = "";
        String arg = "";
        Person person = null;
        String[] spilt = input.trim().split(" ", 2);
        command = spilt[0];
        if (spilt.length > 1) {
            arg = spilt[1];
        }
        if (command.equals("add") || command.equals("add_if_min") || command.equals("update")
                || command.equals("remove_greater") || command.equals("remove_lower")) {
            person = dataManager.readPerson();
        }
        return new Request(command, arg, person);
    }
}

class RequestSender {
    private static final int BATCH = 1024;
    private Request request;
    private SocketAddress address;
    private DatagramChannel channel;

    public RequestSender(Request request, DatagramChannel channel, SocketAddress address) {
        this.request = request;
        this.address = address;
        this.channel = channel;
    }

    public void send() {
        try {
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            ObjectOutputStream objOutput2 = new ObjectOutputStream(byteArrayOutputStream2);
            objOutput2.writeObject(request);
            byte[] data = byteArrayOutputStream2.toByteArray();
            int n = (int) Math.ceil((double) data.length / (BATCH - 1));
            for (int i = 0; i < n; i++) {
                byte[] batch = new byte[BATCH];
                System.arraycopy(data, i * (BATCH - 1), batch, 0, Math.min(data.length - i * (BATCH - 1), BATCH - 1));

                batch[BATCH - 1] = (byte) ((i + 1 == n) ? 1 : 0);
                channel.send(ByteBuffer.wrap(batch), address);
            }
            byteArrayOutputStream2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ResponseReceiver {
    private static final int BATCH = 1024;
    private DatagramChannel channel;

    public ResponseReceiver(DatagramChannel channel) {
        this.channel = channel;
    }

    public List<Response> receive() {
        ByteBuffer buffer = ByteBuffer.allocate(BATCH);
        List<Response> responses = new ArrayList<>();
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        SocketAddress address;
        while (true) {
            try {
                address = channel.receive(buffer);
                data.write(Arrays.copyOf(buffer.array(), BATCH - 1));
                if (buffer.array()[BATCH - 1] == (byte) 1) {
                    responses.add(responseDeserializer(data.toByteArray()));
                    break;
                }
                address = null;
                buffer.clear();
            } catch (IOException exception) {
                System.err.println("trying to reconnect to server :");
                for (int j = 15; j > 0; j = j - 2) {
                    System.err.print("\r" + "\uD83D\uDFE5".repeat(j - 1));
                    receive();
                    break;
                }
                System.err.print("\rServer is unavailable".toUpperCase());
            }
        }
        return responses;
    }
    private Response responseDeserializer (byte [] response ){
        try{
            ByteArrayInputStream inputStream = new ByteArrayInputStream(response);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            return (Response) objectInputStream.readObject();
        }catch (Exception exception){
            System.err.println(exception.getMessage());
        }
        return null;
    }
}

