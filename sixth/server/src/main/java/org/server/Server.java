package org.server;


import org.collection.StudyGroupCollectionManager;
import org.commands.CommandType;
import org.commands.Commandable;
import org.commands.ServerCommandManager;
import org.connection.*;
import org.exceptions.ConnectionException;
import org.exceptions.FileException;
import org.file.FileManager;
import org.exceptions.*;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AlreadyBoundException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * server class
 */
public class Server extends Thread implements SenderReceiver {

    private StudyGroupCollectionManager collectionManager;
    private FileManager fileManager;
    private ServerCommandManager commandManager;
    private int port;
    private InetSocketAddress clientAddress;
    private DatagramChannel channel;

    private volatile boolean running;

    private void init(int p, String path) throws ConnectionException, FileException {
        running = true;
        port = p;
        collectionManager = new StudyGroupCollectionManager();
        fileManager = new FileManager(path);
        commandManager = new ServerCommandManager(this);
        collectionManager.csvDeserializer(fileManager.read());
        host(port);
        setName("server thread");
        //Log.logger.trace("starting server");
    }

    private void host(int p) throws ConnectionException {
        try {
            if (channel != null && channel.isOpen()) channel.close();
            channel = DatagramChannel.open();
            channel.bind(new InetSocketAddress(port));
        } catch (AlreadyBoundException e) {
            throw new PortAlreadyInUseException();
        } catch (IllegalArgumentException e) {
            throw new InvalidPortException();
        } catch (IOException e) {
            throw new ConnectionException("something went wrong during server initialization");
        }
    }

    public Server(int p, String path) throws ConnectionException {
        try {
            init(p, path);
        }catch (FileException exception){

        }
    }

    /**
     * receives request from client
     *
     * @return
     * @throws ConnectionException
     * @throws InvalidDataException
     */
    public Request receive() throws ConnectionException, InvalidDataException {
        ByteBuffer buf = ByteBuffer.allocate(BUFFER_SIZE);
        try {
            clientAddress = (InetSocketAddress) channel.receive(buf);
            //Log.logger.trace("received request from " + clientAddress.toString());
        } catch (ClosedChannelException e) {
            throw new ClosedConnectionException();
        } catch (IOException e) {
            throw new ConnectionException("something went wrong during receiving request");
        }
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(buf.array()));
            Request req = (Request) objectInputStream.readObject();
            return req;
        } catch (ClassNotFoundException | ClassCastException | IOException e) {
            throw new InvalidReceivedDataException();
        }

    }

    /**
     * sends response
     *
     * @param response
     * @throws ConnectionException
     */
    public void send(Response response) throws ConnectionException {
        if (clientAddress == null) throw new InvalidAddressException("no client address found");
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(BUFFER_SIZE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(response);
            channel.send(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()), clientAddress);
            //Log.logger.trace("sent response to " + clientAddress.toString());
        } catch (IOException e) {
            throw new ConnectionException("something went wrong during sending response");
        }
    }

    /**
     * runs server
     */
    public void run() {
        while (running) {
            AnswerMsg answerMsg = new AnswerMsg();
            try {
                try {
                    Request commandMsg = receive();
                    if (commandMsg.getStudyGroup() != null) {
                        commandMsg.getStudyGroup().setCreationDate(ZonedDateTime.now());
                    }
                    if (commandManager.getCommand(commandMsg).getType() == CommandType.SERVER_ONLY) {
                        throw new ServerOnlyCommandException();
                    }
                    answerMsg = commandManager.runCommand(commandMsg);
                    if (answerMsg.getStatus() == Status.EXIT) {
                        close();
                    }
                } catch (CommandException e) {
                    answerMsg.error(e.getMessage());
                    //Log.logger.error(e.getMessage());
                }
                send(answerMsg);
            } catch (ConnectionException | InvalidDataException e) {
                //Log.logger.error(e.getMessage());
            }
        }
    }

    public void consoleMode() {
        commandManager.consoleMode();
    }

    /**
     * close server and connection
     */
    public void close() {
        try {
            running = false;
            channel.close();
        } catch (IOException e) {
            //Log.logger.error("cannot close channel");
        }
    }

    public Commandable getCommandManager() {
        return commandManager;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public StudyGroupCollectionManager getCollectionManager() {
        return collectionManager;
    }

}