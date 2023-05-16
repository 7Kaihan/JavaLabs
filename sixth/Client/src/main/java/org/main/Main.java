package org.main;

import org.client.Client;
import org.exceptions.ConnectionException;
import org.exceptions.InvalidPortException;
import org.exceptions.InvalidProgramEnviromentArgumentException;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Main {
    private final static String HOST = System.getenv("host");
    private final static String PORT = System.getenv("port");
    public static void main(String [] args) throws UnsupportedEncodingException {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        String addr  = "";
        int port = 0;
        try {
            if (HOST.isEmpty()) throw new InvalidProgramEnviromentArgumentException("no address passed by System.getenv()");
            addr = HOST;
            try {
                port = Integer.parseInt(PORT);
            } catch (NumberFormatException e){
                throw new InvalidPortException();
            }
            Client client = new Client(addr,port);
            client.start();
        }
        catch (InvalidProgramEnviromentArgumentException | ConnectionException e){
            System.out.println(e.getMessage());
        }
    }
}
