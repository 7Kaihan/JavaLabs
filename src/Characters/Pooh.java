package Characters;

import Action.GetTask;

public class Pooh extends Character{
    {
        name = "Pooh";
        stat = "Eating";
    }
    public Pooh(){
        super.role = "Supporting character";
    }
    @Override
    public String Capibility() {
        return "I can eat more than any other creatures!!!!";
    }

    @Override
    public String jobs() {
        return "Pooh is obedient ";
    }

    public String toString (){
        return Greet();
    }

    @Override
    public int hashCode(){
        return super.hashCode() ;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Pooh;
    }
}
