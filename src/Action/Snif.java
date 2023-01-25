package Action;

import Characters.Character;

import java.util.ArrayList;
import java.util.List;

public class Snif implements CharacterAdder{
    private List<Character> myList = new ArrayList<>();
    @Override
    public void addCharacter(Character character) {
        myList.add(character);
    }
    @Override
    public void allCharacters() {
        for(Character character : myList){
            System.out.print(character.name+" is snifing fresh air, ");
        }
        System.out.println();
    }
}
