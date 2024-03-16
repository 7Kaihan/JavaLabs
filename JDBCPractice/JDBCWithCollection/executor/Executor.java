package JDBC.JDBCWithCollection.executor;

import JDBC.JDBCWithCollection.collectionManager.PersonCollectionManager;
import JDBC.JDBCWithCollection.collectionManager.PersonManager;
import JDBC.JDBCWithCollection.colorizedConsole.ConsoleColor;
import JDBC.JDBCWithCollection.commands.AbstractCommand;
import JDBC.JDBCWithCollection.commands.CommandManager;
import JDBC.JDBCWithCollection.dataBase.ConnectionHandler;
import JDBC.JDBCWithCollection.dataBase.EntityDeserializer;
import JDBC.JDBCWithCollection.dataBase.PersonDataBaseManager;
import JDBC.JDBCWithCollection.dataBase.TablesCreater;
import JDBC.JDBCWithCollection.modules.Person;

import java.sql.Connection;
import java.util.Scanner;
import java.util.Set;

public class Executor {
    private ConnectionHandler connectionHandler;

    public Executor(String hostName, String databaseName, String userName, String password) {
        connectionHandler = new ConnectionHandler(hostName, databaseName, userName, password);
    }

    public void execute() {
        PersonManager<Person, Integer> collectionManager = new PersonCollectionManager();
        CommandManager commandManager = new CommandManager(collectionManager);
        Connection connection = connectionHandler.getConnection();
        TablesCreater creater = TablesCreater.getInstance();
        EntityDeserializer deserializer = new EntityDeserializer(connection);
        creater.createPersonTable(connection);
        PersonDataBaseManager manager = new PersonDataBaseManager(connection);
        Set<Person> desializedperson = deserializer.deserialize();
        if (desializedperson.size() == 0)
            System.out.println(ConsoleColor.YELLOW_BACKGROUND + "Database was empty" + ConsoleColor.RESET);
        else {
            collectionManager.setCollection(desializedperson);
            collectionManager.setUniqueIds(deserializer.getUniqueIds());
            System.out.println(ConsoleColor.GREEN_BACKGROUND + "database has been successfully deserialized" + ConsoleColor.RESET);
        }
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(ConsoleColor.CYAN_BOLD + "Enter a command (help to get help ) = " + ConsoleColor.RESET);
            String command = scanner.nextLine();
            if (command.equals("exit")) break;
            String commandd = null;
            String arg = "";
            String[] commandAndArg = command.trim().split(" ", 2);
            commandd = commandAndArg[0];
            if (command.contains(" ")) {
                arg = commandAndArg[1];
            }
            if (commandManager.getCommandMap().containsKey(commandd)) {
                AbstractCommand c = commandManager.getCommandMap().get(commandd);
                c.setArg(arg);
                c.execute();
            } else System.out.println(ConsoleColor.RED_BACKGROUND + "No such command" + ConsoleColor.RESET);
        }
        manager.clear();
        collectionManager.getCollection().forEach(manager::add);
    }
}
