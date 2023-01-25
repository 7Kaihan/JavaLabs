package Action;

import java.util.Random;
import Exception.*;
import Characters.Character;
public class CharacterMoods {
    private boolean Happy ;
    public boolean CharactersMooders() {
        Happy = false;
        class CharacterMoodSetter {
            public boolean SetMooder() {
                return Happy;
            }
        }
        CharacterMoodSetter characterMoodSetter = new CharacterMoodSetter();
        return characterMoodSetter.SetMooder();
    }
}
