package Body;

import Characters.Character;

public class Mouth  implements Openable,Closable{
      private String name = "Mouth";

    @Override
    public String Open(Character character) {
        return character.name +" opened his "+name;
    }

    @Override
    public String Close(Character character) {
        return character.name+" closed his "+name;
    }

                                //Inner Static Class
    public static class Tongue implements Rotatable {
        @Override
        public void rotate(Character character) {
            System.out.println(character.name+" s tonuge is shacking");
        }
        public void lick (Character character){
            System.out.println(character.name+" is licking ");
        }
    }
}
