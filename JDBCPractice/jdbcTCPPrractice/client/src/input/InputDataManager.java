package input;

import colorizedConsole.ConsoleColor;
import modules.Color;
import modules.Coordinates;
import modules.Location;
import modules.Person;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class InputDataManager {
    private Scanner scanner;

    public InputDataManager() {
        scanner = new Scanner(System.in);
    }

    private String readName() {
        String name;
        do {
            System.out.println(ConsoleColor.CYAN_BOLD+"Enter name (Name can't be empty or null ) : "+ConsoleColor.RESET);
            name = scanner.nextLine();
        } while (name.equals(""));
        return name;
    }

    private Long readCoordinateX() {
        Long x;
        while (true) {
            try {
                System.out.println(ConsoleColor.CYAN_BOLD+"Enter coordinate [X] (Should be less than 827) : "+ConsoleColor.RESET);
                String x1 = scanner.nextLine();
                x = Long.parseLong(x1);
                if (x > 827) System.out.println(ConsoleColor.RED_BACKGROUND+"Should be less than [827]"+ConsoleColor.RESET);
                else break;
            } catch (NumberFormatException exception) {
                System.out.println(ConsoleColor.RED_BACKGROUND+"Should be a number"+ConsoleColor.RESET);
            }
        }
        return x;
    }

    private int readCoordinateY() {
        int y = 0;
        while (true) {
            try {
                System.out.println(ConsoleColor.CYAN_BOLD+"Enter coordinate [Y] : "+ConsoleColor.RESET);
                String y1 = scanner.nextLine();
                y = Integer.parseInt(y1);
                break;
            } catch (NumberFormatException exception) {
                System.out.println(ConsoleColor.RED_BACKGROUND+"Should be a number"+ConsoleColor.RESET);
            }
        }
        return y;
    }

    private ZonedDateTime readCreationDate() {
        return ZonedDateTime.now();
    }

    private Long readHeight() {
        Long h = 0l;
        while (true) {
            try {
                System.out.println(ConsoleColor.CYAN_BOLD+"Enter height (Should be greater than 0, Can be null) : "+ConsoleColor.RESET);
                String h1 = scanner.nextLine();
                if (h1.equals("")) break;
                h = Long.parseLong(h1);
                if(h<=0) throw new RuntimeException(ConsoleColor.RED_BACKGROUND+"Should be greater than 0"+ConsoleColor.RESET);
                break;
            } catch (NumberFormatException exception) {
                System.out.println(ConsoleColor.RED_BACKGROUND+"Should be a number"+ConsoleColor.RESET);
            } catch (RuntimeException exception){
                System.out.println(exception.getMessage());
            }
        }
        return h;
    }
    private Date readBirthday (){
        Date date;
        while(true){
            System.out.println(ConsoleColor.CYAN_BOLD+"Enter birthday [yyyy-mm-dd] (Can be null) : "+ConsoleColor.RESET);
            String birthday = scanner.nextLine();
            if(birthday.equals(""))break;
            try{
                String[] birth = birthday.trim().split("-");
                int y = Integer.parseInt(birth[0]);
                if(y>9999) throw new RuntimeException(ConsoleColor.RED_BACKGROUND+"Year can't be greater than 9999"+ConsoleColor.RESET);
                int m = Integer.parseInt(birth[1]);
                if(m>12) throw new RuntimeException(ConsoleColor.RED_BACKGROUND+"Month can't be greater than "+ConsoleColor.RESET);
                int d = Integer.parseInt(birth[2]);
                if(d > 31) throw new RuntimeException(ConsoleColor.RED_BACKGROUND+"Day can't be greater than 31"+ConsoleColor.RESET);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                date = format.parse(birthday);
               // date = new Date(y,m,d);
                return date;
            }catch (NumberFormatException exception){
                System.out.println(ConsoleColor.RED_BACKGROUND+"Invalid input"+ConsoleColor.RESET);
            }catch (IndexOutOfBoundsException exception){
                System.out.println(ConsoleColor.RED_BACKGROUND+"Incomplete input"+ConsoleColor.RESET);
            } catch (RuntimeException exception){
                System.out.println(exception.getMessage());
            } catch (ParseException exception){
                System.out.println(ConsoleColor.RED_BACKGROUND+"Couldn't parse date"+ConsoleColor.RESET);
            }
        }
        return null;
    }
    private Float readWeight(){
        Float weight;
        while (true){
            System.out.println(ConsoleColor.CYAN_BOLD+"Enter weight (Should be greater than 0, Can be null) : "+ConsoleColor.RESET);
            String w = scanner.nextLine();
            if (w.equals(""))break;
            try{
               weight = Float.parseFloat(w);
               if(weight<=0) throw new RuntimeException(ConsoleColor.RED_BACKGROUND+"Should be greater than 0"+ConsoleColor.RESET);
               return weight;
            }catch (NumberFormatException exception){
                System.out.println(ConsoleColor.RED_BACKGROUND+"Should be a number"+ConsoleColor.RESET);
            }catch (RuntimeException exception){
                System.out.println(exception.getMessage());
            }
        }
        return null;
    }
    private Color readHairColor (){
        Color color = null;
        while (true){
            System.out.println(ConsoleColor.CYAN_BOLD+"Enter hair color ["+ConsoleColor.RESET+ConsoleColor.RED_BOLD+"RED"+ConsoleColor.RESET+ConsoleColor.CYAN_BOLD+","+ConsoleColor.RESET+ConsoleColor.BLUE_BOLD+" BLUE"+ConsoleColor.RESET+ConsoleColor.CYAN_BOLD+","+ConsoleColor.RESET+ConsoleColor.BROWN_BOLD+" BROWN"+ConsoleColor.RESET+ConsoleColor.CYAN_BOLD+"] (Can be null ): "+ConsoleColor.RESET);
            String c = scanner.nextLine();
            if (c.equals("")) break;
            try{
                color = Color.valueOf(c.toUpperCase());
                break;
            }catch (IllegalArgumentException exception){
                System.out.println(ConsoleColor.RED_BACKGROUND+"No such color"+ConsoleColor.RESET);
            }
        }
        return color;
    }
    private int readLocationX (){
        while (true){
            System.out.println(ConsoleColor.CYAN_BOLD+"Enter location [X] : "+ConsoleColor.RESET);
            String xS = scanner.nextLine();
            try{
                int x = Integer.parseInt(xS);
                return x;
            }catch (NumberFormatException exception){
                System.out.println(ConsoleColor.RED_BACKGROUND+"Should be a number"+ConsoleColor.RESET);
            }
        }
    }
    private double readLocationY(){
        while(true){
            System.out.println(ConsoleColor.CYAN_BOLD+"Enter location [Y] : "+ConsoleColor.RESET);
            String yS = scanner.nextLine();
            try{
                double y = Double.parseDouble(yS);
                return y;
            }catch (NumberFormatException exception ){
                System.out.println(ConsoleColor.RED_BACKGROUND+"Should be a number"+ConsoleColor.RESET);
            }
        }
    }
    private Long readLocationZ (){
        while (true){
            System.out.println(ConsoleColor.CYAN_BOLD+"Enter location [Z] : "+ConsoleColor.RESET);
            String zS = scanner.nextLine();
            try{
                Long z = Long.parseLong(zS);
                return z;
            }catch (NumberFormatException exception ){
                System.out.println(ConsoleColor.RED_BACKGROUND+"Should be a number"+ConsoleColor.RESET);
            }
        }
    }
    private Coordinates readCoordinates (){
        System.out.println(ConsoleColor.CYAN_BOLD+"Enter coordinates : "+ConsoleColor.RESET);
        return new Coordinates(readCoordinateX(),readCoordinateY());
    }
    private Location readLocation(){
        System.out.println(ConsoleColor.CYAN_BOLD+"Enter location : "+ConsoleColor.RESET);
        return new Location(readLocationX(),readLocationY(),readLocationZ());
    }
    public Person readPerson (){
        return new Person(readName(),readCoordinates(),readCreationDate(),readHeight(),readBirthday(),readWeight(),readHairColor(),readLocation());
    }
}
