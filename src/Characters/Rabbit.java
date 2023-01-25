package Characters;
import Action.Go;
import Action.Mood;
import Action.Speech;
import Exception.*;
import Place.Place;

public class Rabbit extends Character implements Mood {
    {
        name = "Rabbit";
        stat = "Running";
    }
    public Rabbit(){
        super.role = "Main Character";
    }
    @Override
    public String Capibility() {
        return "I am the fastest creature!!!!";
    }

    @Override
    public String jobs() {
       return "Rabbit, is commander";
    }
    public String toString (){
        return Greet();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
    @Override
    public boolean equals(Object obj) {

        return obj instanceof Rabbit;
    }
    private boolean Happy =true;
    @Override
    public boolean CharactersMooders() {
        Happy = false;
                                    //local Class
        class CharacterMoodSetter{
            public void ModChanger() {
                Happy =Happy==true?false:true;
            }

            public boolean SetMooder (){
                return Happy;
            }
        }
        CharacterMoodSetter characterMoodSetter = new CharacterMoodSetter();
        return characterMoodSetter.SetMooder();
    }
    public void RabbitGoes(boolean mode, Go go){
        if (mode){
            Place p = Place.KANGA_S_HOUSE;
            System.out.println(go.Objective(this, Place.KANGA_S_HOUSE));
            Character roo = new Roo();
            System.out.println(p.Where(roo, p));
            Speech speech = new Speech();
            speech.setCharacter(roo);
            speech.addCharacter(this);
            speech.allCharacters();
        }else{
            throw new RabbitNotGoingToKangaException("Rabbit is not happy");
        }
    }

}
