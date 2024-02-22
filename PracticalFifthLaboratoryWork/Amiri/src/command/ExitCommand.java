package command;

public class ExitCommand extends Command{
    public ExitCommand(){

    }
    @Override
    public void execute() {
        System.exit(0);
    }

    @Override
    public String description() {
        return "end the program (without saving to a file)";
    }
}
