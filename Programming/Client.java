import java.util.Scanner;
import java.net.*;
import java.io.*;

public class Client{
      public static void main (String [] args){
          Scanner input = new Scanner (System.in);
          try{
              Socket socket = new Socket ("localhost",1924);
              InputStream inputStream  = socket.getInputStream();
              OutputStream outputStream = socket.getOutputStream();
              ObjectInputStream objectInputStream = new ObjectInputStream (inputStream);
              ObjectOutputStream objectOutputStream = new ObjectOutputStream (outputStream);
              while (true){
                System.out.println ("Enter text to echo =");
                String messageToEcho = input.nextLine ();
                objectOutputStream.wirteObject (messageToEcho);
              }
          }
      }
              
