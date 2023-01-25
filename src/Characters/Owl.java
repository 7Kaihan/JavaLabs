package Characters;

import Action.GetTask;

public class Owl extends Character{
    {
        name = "Owl";
        stat = "Sleeping";
    }
    public Owl(){
        super.role = "Supporting character";
    }
    @Override
    public String Capibility() {
        return "My eyes are bigger than any other creature!!!!";
    }

    @Override
    public String jobs() {
        return "Owl is obedient";
    }
    @Override
    public String toString (){
        return Greet();
    }

    @Override
    public int hashCode(){
        return super.hashCode() + 1;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Owl;
    }
}
