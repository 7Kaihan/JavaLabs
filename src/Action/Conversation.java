package Action;
import Characters.Character;
import java.util.Vector;
public class Conversation implements CharacterAdder {
    private Vector<Character> characters = new Vector<>();
    @Override
    public void addCharacter(Character character) {
        characters.add(character);
    }

    @Override
    public void allCharacters() {
        System.out.print("Characters engaged in disscussion are : ");
        for (Character rolePlayer : characters){
            System.out.print(rolePlayer.name + ", ");
        }
        System.out.println();
    }

    @Override
    public String toString() {
        allCharacters();
        return "Engaging in conversation ";
    }

    @Override
    public int hashCode() {
        return super.hashCode()+10;
    }
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Conversation;
    }
}
