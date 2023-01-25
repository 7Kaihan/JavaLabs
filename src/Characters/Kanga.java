package Characters;

public class Kanga extends Character{
    {
        name = "Kanga";
        stat = "Cooking";
    }
    public Kanga(){
        super.role = "Supporting character";
    }
    @Override
    public String Capibility() {
        return null;
    }

    @Override
    public String jobs() {
        return null;
    }

    @Override
    public String toString() {
        return Greet();
    }
}
