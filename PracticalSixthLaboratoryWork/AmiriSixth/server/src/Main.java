import collection.CollectionManager;
import command.CommandManager;
import deserializer.Deserializer;
import file.FileManager;
import server.Server;

public class Main {
    private static String FILENAME = System.getenv("file");
    public static void main(String[] args) {
        CollectionManager collectionManager = new CollectionManager();
        FileManager fileManager = new FileManager(FILENAME);
        Deserializer deserializer = new Deserializer();
        CommandManager commandManager = new CommandManager(collectionManager, fileManager);
        if (!fileManager.read().equals(""))
            collectionManager.setCollection(deserializer.deserialize(fileManager.read()));
        else System.err.println("File was empty");
        Server server = new Server(collectionManager,commandManager,fileManager,8931);
        server.run();
    }
}