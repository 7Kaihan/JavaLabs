package Action;
import Characters.Character;
import Place.Place;

public interface Objective {
    String Objective (Character character1, Character character2);
    String Objective (Character charcter, Place place);
}
