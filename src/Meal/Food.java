package Meal;
import Characters.Character;
import Place.Place;
import Exception.*;
import java.util.Random;

public enum Food {
    FISH_OIL,
    MEDICINE,
    PORRIDGE,
    VEGETABLES;
    public String Eat (Character character, Meal meal, Place place) throws EatingBadFoodException {
        Food []foods= new Food[]{FISH_OIL,MEDICINE,PORRIDGE};
        Food food = foods[new Random().nextInt(foods.length)];
        if (place == Place.KANGA_S_HOUSE &&food!=VEGETABLES){
            if (meal==Meal.BREAKFAST||meal == Meal.LUNCH || meal == Meal.DINNER){
                    return character.name+" has got "+Food.FISH_OIL+" for "+meal;
                }
            else if(meal== Meal.SNACK){
                return character.name+ "ate snacks";
            }
            else{
                return character.name+" ate a spoonful or two of "+ Food.PORRIDGE+" instead of "+Food.MEDICINE;
            }
        }else if(place!=Place.KANGA_S_HOUSE){
            throw new LocationException(character.name+" is not in "+Place.KANGA_S_HOUSE+" "+character.name+" is in "+place,new RuntimeException());
        }
        else if(food==VEGETABLES){
            throw new EatingBadFoodException(new RuntimeException());
        }
        return character.name+ " is in his home and he is not eating";
    }
}
