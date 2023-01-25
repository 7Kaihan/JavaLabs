package Characters;

public abstract class  Character {
    public String name;
    public String role;
    public String stat;
    public String Greet(){
        return name+" joins the story as "+role;
    }
    public abstract String Capibility();
    public abstract String jobs();
}
