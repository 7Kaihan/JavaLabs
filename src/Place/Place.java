package Place;
import Characters.Character;
public enum Place {
    HOUSE_S_CIELING,
    KANGA_S_HOUSE,
    RABBIT_S_HOUSE,
    FOREST;
    public String Where(Character character, Place place){
        if(character.name == "Roo" && place == Place.KANGA_S_HOUSE){
            return character.name+" is sitting in "+place;
        }
        return character.name+" is in "+place;
    }
}
