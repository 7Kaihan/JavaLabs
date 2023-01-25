package Action;

import Characters.Character;
import Place.Place;

public class Look implements Objective{
    @Override
    public String Objective(Character character1, Character character2) {
        return character1.name+" is looking at "+character2.name;
    }
    @Override
    public String Objective(Character character, Place place) {
        return character.name+" is looking at "+place;
    }
}
