import client.Client;

public class Main {
    public static void main(String[] args) {
        Client client  = new Client(8934,"localhost");
        client.run();
    }
}