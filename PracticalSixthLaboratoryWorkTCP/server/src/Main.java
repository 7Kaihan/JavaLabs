import collection.CollectionManager;
import colorizedConsole.ConsoleColor;
import command.CommandManager;
import deserializer.Deserializer;
import file.FileManager;
import server.Server;

public class Main {
    private final static String FILE = System.getenv("file");

    public static void main(String[] args) {
        CollectionManager collectionManager = new CollectionManager();
        FileManager fileManager = new FileManager(FILE);
        CommandManager commandManager = new CommandManager(collectionManager, fileManager);
        String content = fileManager.read();
        if (!content.equals("")) {
            Deserializer deserializer = new Deserializer();
            collectionManager.setCollection(deserializer.deserialize(content));
            collectionManager.setIds(deserializer.getIds());
            if (collectionManager.getCollection().size() > 0)
                System.out.println(ConsoleColor.GREEN_BACKGROUND + "Collection has been successfully filled with data from the file." + ConsoleColor.RESET);
            else System.out.println(ConsoleColor.YELLOW_BACKGROUND+ "File didn't contain Person records so Collection is empty."+ConsoleColor.RESET);
        } else System.out.println(ConsoleColor.MAGENTA_BACKGROUND + "File was empty." + ConsoleColor.RESET);
        Server server = new Server(collectionManager, commandManager, fileManager, 8921);
        server.run();
    }
}