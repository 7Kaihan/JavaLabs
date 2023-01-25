package Action;

import Characters.Character;
import Characters.*;
import Place.Place;

import java.util.ArrayList;
import java.util.List;

public class Speech implements CharacterAdder {
    private Character character;

    public void setCharacter(Character character) {
        this.character = character;
    }

    private List<Character> list = new ArrayList<>();

    @Override
    public void addCharacter(Character character) {
        list.add(character);
    }

    private Character rabbit = new Rabbit();

    @Override
    public void allCharacters() {
        if (character.name == rabbit.name) {
            System.out.println("Rabbit is talking to :");
            for (Character character1 : list) {
                System.out.print(character1.name);
            }
        } else {
            System.out.println(character.name + " is talking to :");
            for (Character character1 : list) {
                if (character1.name == rabbit.name) {
                    System.out.println(character1.name);
                    new Saying().Saying(character1);
                }else{
                    System.out.print(character1.name+" ");
                }
            }
        }
    }
                        //Inner Class Non-Static
    public class Saying {
        private List<String> said = new ArrayList<>();

       public void Saying(Character character) {
            if (character.name == rabbit.name) {
                System.out.println("Roo says:");
                said.add("Yes Rabbit");
                said.add("Good Rabbit");
                for (String say : said) {
                    System.out.println(say);
                }
                System.out.println(character.name + " is the best in " + Place.FOREST);
            } else {
                System.out.println(character.name + " is not saying anything ");
            }
        }
    }
}


