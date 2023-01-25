package Action;

import Characters.Character;

import java.util.ArrayList;
import java.util.List;

public class Smile implements CharacterAdder{
    private List<Character> list=new ArrayList<>();
    @Override
    public void addCharacter(Character character) {
        list.add(character);
    }
    @Override
    public void allCharacters() {
        System.out.println("Characters who smiled :");
        for(Character s : list){
            System.out.print(s.name+", ");
        }
        System.out.println();
    }
}
