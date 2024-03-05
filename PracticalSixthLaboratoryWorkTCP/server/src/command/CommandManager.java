package command;

import collection.CollectionManager;
import file.FileManager;

import java.util.HashMap;

public class CommandManager {
    private CollectionManager collectionManager;
    private FileManager fileManager;
    private HashMap<String, AbstractCommand> commandMap;

    public CommandManager(CollectionManager collectionManager, FileManager fileManager) {
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
        commandMap = new HashMap<>();
        commandMap.put("add",new AddCommand(collectionManager));
        commandMap.put("add_if_min",new AddIfMinCommand(collectionManager));
        commandMap.put("clear",new ClearCommand(collectionManager));
        commandMap.put("execute_script",new ExecuteScriptCommand(fileManager,this));
        commandMap.put("filter_by_birthday",new FilterByBirthdayCommand(collectionManager));
        commandMap.put("help",new HelpCommand(this));
        commandMap.put("show",new ShowCommand(collectionManager));
        commandMap.put("print_field_descending_birthday", new PrintFieldDescendingBirthdayCommand(collectionManager));
        commandMap.put("remove_all_by_hair_color",new RemoveAllByHairColorCommand(collectionManager));
        commandMap.put("remove_by_id",new RemoveByIdCommand(collectionManager));
        commandMap.put("remove_greater",new RemoveGreaterCommand(collectionManager));
        commandMap.put("remove_lower",new RemoveLowerCommand(collectionManager));
        commandMap.put("update",new UpdateCommand(collectionManager));
        commandMap.put("info",new InfoCommand(collectionManager));
    }

    public HashMap<String, AbstractCommand> getCommandMap() {
        return commandMap;
    }

}
