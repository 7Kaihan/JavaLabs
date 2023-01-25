package Action;

import Characters.Character;
import java.util.Vector;

public class GetTask implements CharacterAdder,Tasks {
    private Vector<Character> characters = new Vector<>();

    @Override
    public void addCharacter(Character character) {
        characters.add(character);
    }

    @Override
    public void allCharacters() {
        System.out.print("Characters waiting in queue to get task are : ");
        for (Character rolePlayer : characters) {
            System.out.print(rolePlayer.name + ", ");
        }
        System.out.println();
    }
    private String[] rab = {"Writing a letter", "Managing", "Organising", "Explaining", "Calrifying", "Speaking"};
    private String[] po ={"Talking to piglet"};
    private String[] pig = {"Watching over Owl"};
    @Override
    public void Task() {
        System.out.println("!!!! Everyone 's tasks !!!!");
        System.out.println("Rabbit 's task :");
        for (String s : rab) {
            System.out.print(s + ", ");

        }
        System.out.println();
        System.out.println("Pooh 's task :");
        for(String s : po){
            System.out.print(s+", ");
        }
        System.out.println();
        System.out.println("Piglet 's task :");
        for (String s : pig){
            System.out.print(s+", ");
        }
        System.out.println();
    }
    @Override
    public String toString() {
        return "Tasks are given to respective characters";
    }

    @Override
    public int hashCode() {
        return super.hashCode()+10;
    }

    @Override
    public boolean equals(Object obj) {
            return obj instanceof GetTask;
    }
}
