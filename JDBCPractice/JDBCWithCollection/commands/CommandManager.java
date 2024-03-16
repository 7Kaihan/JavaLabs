package JDBC.JDBCWithCollection.commands;

import JDBC.JDBCWithCollection.collectionManager.PersonManager;
import JDBC.JDBCWithCollection.modules.Person;

import java.util.LinkedHashMap;
import java.util.Map;

public class CommandManager {
    private PersonManager<Person,Integer> collectionManager;
    private Map<String,AbstractCommand> commandMap;
    public CommandManager(PersonManager<Person,Integer> collectionManager){
        this.collectionManager = collectionManager;
        commandMap = new LinkedHashMap<>();
        commandMap.put("help",new HelpCommand(this));
        commandMap.put("add",new AddCommand(collectionManager));
        commandMap.put("clear",new ClearCommand(collectionManager));
        commandMap.put("show",new ShowCommand(collectionManager));
        commandMap.put("remove_by_id",new RemoveByIdCommand(collectionManager));
    }
    public Map<String, AbstractCommand> getCommandMap() {
        return commandMap;
    }
}
