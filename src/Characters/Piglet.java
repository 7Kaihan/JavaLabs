package Characters;

import Action.GetTask;

public class Piglet extends Character{
    {
        name = "Piglet";
        stat = "Singing";
    }
    public Piglet(){
        super.role = "Supporting character";
    }
    @Override
    public String Capibility() {
        return "I am the kindest creature!!!!";
    }

    @Override
    public String jobs() {
        return "Piglet is obedient";
    }

    @Override
    public String toString() {
        return Greet();
    }
    @Override
    public int hashCode(){
        return super.hashCode() + 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj instanceof Piglet;
    }
}
