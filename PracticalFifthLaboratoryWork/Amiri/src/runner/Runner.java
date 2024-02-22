package runner;

import command.CommandManager;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Runner {
    private static Scanner scanner = new Scanner(System.in);
    private Runner() {
    }

    public static void run (CommandManager commandManager){
        try{
            while (true){
                System.out.println("Enter a command (help to get help) : ");
                String commandArg = scanner.nextLine();
                String [] argCommandSpilt = commandArg.trim().split(" ",2);
                try{
                   commandManager.getCommandMap().get(argCommandSpilt[0]).setArg(argCommandSpilt[1]);
                   commandManager.getCommandMap().get(argCommandSpilt[0]).execute();
                }catch (IndexOutOfBoundsException exception){
                    try {
                        commandManager.getCommandMap().get(argCommandSpilt[0]).execute();
                    }catch (NullPointerException exception1){
                        System.err.println("no such command");
                    }
                }catch (NullPointerException exception1){
                    System.err.println("no such command");
                }
            }
        }catch (NoSuchElementException exception){
            System.err.println("Stream shouldn't be closed");
        }
    }

}
