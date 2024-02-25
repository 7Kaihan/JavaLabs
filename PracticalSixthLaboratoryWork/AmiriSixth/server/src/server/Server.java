package server;

import collection.CollectionManager;
import command.Command;
import command.CommandManager;
import command.SaveCommand;
import connection.Request;
import connection.Response;
import file.FileManager;
import modules.Color;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private int port;
    private CommandManager commandManager;
    private CollectionManager collectionManager;
    private FileManager fileManager;
    private SaveCommand saveCommand;
    public Server(CollectionManager collectionManager, CommandManager commandManager,FileManager fileManager, int port) {
        this.commandManager = commandManager;
        this.port = port;
        saveCommand = new SaveCommand(fileManager,collectionManager);
    }

    public void run() {
        RequestReceiver receiver = null;
        try {
            DatagramSocket socket = new DatagramSocket(port);
            while (true) {
                try {
                    System.out.println("Waiting for a client to be connected");
                    receiver = new RequestReceiver(socket);
                    Request request = receiver.receive();

                    System.out.println("Client with address of ["+receiver.getAddress()+"] has been connected");
                    System.out.println("#["+request.getCommandName()+"]# has been received from the client");

                    RequestProcessor processor = new RequestProcessor(commandManager,request);
                    Response response =  processor.process();


                    ResponseSender sender = new ResponseSender(socket,response, receiver.getAddress());
                    sender.send();
                    saveCommand.execute();
                    System.out.println("-------------------------------------------------------------------");

                } catch (Exception exception) {
                    ResponseSender sender = new ResponseSender(socket,new Response("\u001B[41m"+exception.getMessage()+"\u001B[0m"), receiver.getAddress());
                    sender.send();
                }
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

}

class RequestReceiver {
    private static final int BATCH = 1024;
    private final Map<SocketAddress, byte[]> clientData;

    private DatagramSocket socket;
    private SocketAddress address;
    public RequestReceiver(DatagramSocket socket) {
        this.socket = socket;
        this.clientData = new HashMap<>();
    }


    public Request receive() {
        Map.Entry<SocketAddress, Request> pair = null;
        try {
            pair = receiver();
            address = pair.getKey();
            Request request = pair.getValue();
            return request;
        } catch (IOException e) {
            System.err.println(e);
        }
        return null;
    }

    public SocketAddress getAddress() {
        return address;
    }

    private Map.Entry<SocketAddress, Request> receiver() throws IOException {


        byte[] batch = new byte[BATCH];
        DatagramPacket dp = new DatagramPacket(batch, batch.length);
        while (true) {
            socket.receive(dp);
            SocketAddress address = dp.getSocketAddress();
            byte[] value = clientData.getOrDefault(address, new byte[]{});
            ByteArrayOutputStream data = new ByteArrayOutputStream();
            data.write(value);
            data.write(Arrays.copyOf(batch, BATCH - 1));
            clientData.put(address, data.toByteArray());
            if (dp.getData()[BATCH - 1] == (byte) 1) {
                return requestProvider(Map.entry(address, clientData.remove(address)));
            }
        }
    }
    private Map.Entry<SocketAddress, Request> requestProvider (Map.Entry<SocketAddress, byte[]> pair) {
        ByteArrayInputStream byteStream = new ByteArrayInputStream(pair.getValue());
        try {
            ObjectInputStream objectStream = new ObjectInputStream(byteStream);
            var request = Map.entry(pair.getKey(), (Request) objectStream.readObject());

            System.out.printf("Request [%s] received from \"%s\"\n", request.getValue().getClass().getSimpleName(), request.getKey());
            return request;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Invalid request format!");
        }
        return null;
    }
}

class RequestProcessor {
    private Request requestToProcess;
    private CommandManager commandManager;
    public RequestProcessor(CommandManager commandManager,Request requestToProcess) {
        this.requestToProcess = requestToProcess;
        this.commandManager = commandManager;
    }

    public Response process() throws Exception {
        try {
            Command c = commandManager.getCommandMap().get(requestToProcess.getCommandName());
            c.setPerson(requestToProcess.getPerson());
            c.setArg(requestToProcess.getCommandArg());
            c.execute();
            return new Response(c.getResult());
        } catch (Exception exception) {
            throw new Exception("No such command");
        }
    }
}

class ResponseSender {
    private final DatagramSocket socket;
    private SocketAddress address;
    private static final int BATCH = 1024;
    private Response response;
    public ResponseSender (DatagramSocket socket, Response response, SocketAddress address){
        this.socket = socket;
        this.address = address;
        this.response = response;
    }
    public void send(){
        if (address == null) throw new RuntimeException("no client address found");
        try {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
            objectStream.writeObject(response);
            byte[] data = byteStream.toByteArray();
            int n = (int) Math.ceil((double) data.length / (BATCH - 1));

            for (int i = 0; i < n; i++) {
                byte[] batch = new byte[BATCH];
                System.arraycopy(data, i * (BATCH - 1), batch, 0, Math.min(data.length - i * (BATCH - 1), BATCH - 1));
                batch[BATCH - 1] = (byte) ((i + 1 == n) ? 1 : 0);
                DatagramPacket dp = new DatagramPacket(batch, batch.length, address);
                socket.send(dp);
                //System.out.printf("Batch %s/%s has been sent\n", i + 1, n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}