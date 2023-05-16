package org.main;


import org.exceptions.ConnectionException;
import org.exceptions.InvalidPortException;
import org.exceptions.InvalidProgramEnviromentArgumentException;
import org.server.Server;

public class Main {
    private final static String FILE = System.getenv("file");
    private final static String PORT = System.getenv("port");

    public static void main(String[] args) {
        int port = 0;
        String strPort = "";
        String path = "Data.csv";
        try {
            if(!FILE.isEmpty()&&!PORT.isEmpty()) {
                path = FILE;
                strPort = PORT;
            }
            if(FILE.isEmpty()&&PORT.isEmpty()) throw new InvalidProgramEnviromentArgumentException("no address passed by System.getenv()");
            try {
                port = Integer.parseInt(strPort);
            } catch (NumberFormatException e){
                throw new InvalidPortException();
            }
            Server server = new Server(port, path);
            server.start();
            server.consoleMode();

        }
        catch (InvalidProgramEnviromentArgumentException | ConnectionException e){
            System.err.println("Either you didn't pass argument or something is invalid ");
        }
    }
}