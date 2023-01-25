package Characters;
import java.util.Random;
public class Tigger extends Character{
    {
        name = "Tigger";
        stat = "Jumping";
    }
    public Tigger(){
        super.role = "Supporting charcter";
    }
    @Override
    public String Capibility() {
        return "I can jump higher than any other creature.";
    }
    @Override
    public String jobs() {
        return name+" is disobidient and restless.";
    }
    @Override
    public String toString() {
        return Greet();
    }
}
