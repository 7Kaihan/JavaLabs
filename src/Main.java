import Characters.*;
import Characters.Character;
import Action.*;
import Body.*;
import java.util.Random;

import Exception.*;
import Meal.*;
import Place.Place;
import Time.*;

public class Main {
    static int[] items = new int[]{1, 2};
    static Random rand = new Random();
    public static void main(String[] args) {
        Character tigger = new Tigger();
        System.out.println(tigger);
        Eye eye = new Eye();
        System.out.println(eye.Open(tigger));
        System.out.println(eye.Close(tigger));
        Mouth mouth = new Mouth();
        mouth.Open(tigger);
                                        //Anonymous Class
        Mouth.Tongue tongue = new Mouth.Tongue() {
            int time = rand.nextInt(100);
            String how = " Circules";

            @Override
            public void rotate(Character character) {
                System.out.println(character.name + " s tongue begun to walk around in " + how + " around the mouth in " + time + " Seconds");
            }

        };
        tongue.rotate(tigger);
        mouth.Close(tigger);
        Objective look = new Look();
        System.out.println(look.Objective(tigger, Place.HOUSE_S_CIELING));
        CharacterAdder smile = new Smile();
        smile.addCharacter(tigger);
        smile.allCharacters();
        Food f = Food.FISH_OIL;
        Place []places=new Place[]{Place.KANGA_S_HOUSE,Place.RABBIT_S_HOUSE,Place.HOUSE_S_CIELING,Place.FOREST};
        try {
            System.out.println(f.Eat(tigger, Meal.BREAKFAST,places[rand.nextInt(places.length)]));
        } catch (EatingBadFoodException | LocationException e) {
            System.err.println("Type of error :"+e.getMessage());
            e.printStackTrace();
            System.err.println(e);
        }

        Rabbit rabbit = new Rabbit();
        System.out.println(rabbit);
        Character po = new Pooh();
        System.out.println(po);
        Character pig = new Piglet();
        System.out.println(pig);
        Character owl = new Owl();
        System.out.println(owl);
        Character kanga = new Kanga();
        System.out.println(kanga);
        GetTask task = new GetTask();
        task.addCharacter(rabbit);
        task.addCharacter(po);
        task.addCharacter(pig);
        task.addCharacter(owl);
        task.allCharacters();
        CharacterAdder conversation = new Conversation();
        conversation.addCharacter(rabbit);
        conversation.addCharacter(po);
        conversation.allCharacters();

        if (getRandArrayElement() == 1) {
            Time t = Time.DAY;
            System.out.println(t.WorkingDay(Days.MONDAY, rabbit));
            task.Task();
        } else {
            System.out.println("In the nights :");
            Time t = Time.NIGHT;
            System.out.println(t.Nights(rabbit));
            System.out.println(t.Nights(po));
            System.out.println(t.Nights(pig));
            System.out.println(t.Nights(owl));
        }

        Go go = new Go();
        go.setPlace(Place.RABBIT_S_HOUSE);
        System.out.println(go.Objective(rabbit, Place.RABBIT_S_HOUSE));
        CharacterAdder snif = new Snif();
        snif.addCharacter(rabbit);
        snif.allCharacters();
        CharacterMoods characterMoods = new CharacterMoods();
        boolean mode = characterMoods.CharactersMooders();
        try {
            rabbit.RabbitGoes(mode, go);
        } catch (RabbitNotGoingToKangaException RNGTKE) {
            System.out.println();
            System.err.println("********************");
            RNGTKE.printStackTrace();

        }
        Place p = Place.KANGA_S_HOUSE;
        System.out.println(p.Where(tigger, p));
    }

    public static int getRandArrayElement() {
        return items[rand.nextInt(items.length)];
    }
}
