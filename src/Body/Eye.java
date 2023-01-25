package Body;
import Characters.*;
import Characters.Character;
public class Eye implements Openable,Closable,Rotatable{
       private String name = "Eyes";
    @Override
    public String Open( Character character) {
        return character.name+" opens his "+name;
    }
    @Override
    public String Close( Character character) {
        return character.name+" closes his "+name;
    }

    @Override
    public void rotate(Character character) {
        System.out.println(character.name+" 's eyes are rotating ");
    }
}
