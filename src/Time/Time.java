package Time;
import Characters.Character;

public enum Time {
    DAY,
    NIGHT;

    public String WorkingDay(Days day, Character character) {
        String task="";
        if (day == Days.MONDAY || day == Days.TUSEDAY || day == Days.WEDENSDAY || day == Days.FRIDAY ||day == Days.THURSDAY) {
            task = "Everyone works and they are very busy\n"+character.jobs();
        }else if (day == Days.SATURDAY || day == Days.SUNDAY){
            task = " no one is working because it is holiday!!!!";
        }
        return task;
    }
    public String Nights(Character character){
        return character.name+" "+character.stat;
    }
}
