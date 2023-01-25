package Action;

import Characters.Character;
import Characters.*;
import Place.Place;

public class Go implements Objective{
    private Place place;
    public Place getPlace() {
        return place;
    }
    public void setPlace(Place place){
        this.place = place;
    }
    @Override
    public String Objective(Character character1, Character character2) {
        return character1.name+" is on way to go to "+character2.name;
    }
    @Override
    public String Objective(Character character, Place place) {
        if(character.name == new Rabbit().name && this.place == place) {
            return character.name+" is coming out of "+place;
        }
        return place+" is near to "+character.name+" 's house\n"+character.name+" is going to "+place;
    }

}
