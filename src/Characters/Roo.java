package Characters;

public class Roo extends Character{
    {
        name = "Roo";
        stat = "Eating";
    }
    public Roo(){
        super.role = "Supporing character";
    }
    @Override
    public String Capibility() {
        return "I am very Smart";
    }

    @Override
    public String jobs() {
        return name+" is obideint";
    }

    @Override
    public String toString() {
        return Greet();
    }

}
