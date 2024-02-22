package command;

public abstract class Command {
    private String arg;
    public abstract void execute ();
    public abstract String description ();

    public void setArg(String arg) {
        this.arg = arg;
    }

    public String getArg() {
        return arg;
    }
}
