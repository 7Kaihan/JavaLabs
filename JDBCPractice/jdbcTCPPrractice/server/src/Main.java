import collectionManager.PersonCollectionManager;
import collectionManager.PersonManager;
import commands.CommandManager;
import modules.Person;
import server.Server;

public class Main {
    public static void main(String[] args) {
        PersonManager<Person,Integer> collectionManager = new PersonCollectionManager();
        CommandManager commandManager = new CommandManager(collectionManager);
        Server server = new Server(8934,commandManager);
        server.run();
    }
}