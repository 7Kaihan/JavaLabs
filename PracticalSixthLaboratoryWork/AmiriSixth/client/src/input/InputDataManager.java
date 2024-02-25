package input;

import modules.Color;
import modules.Coordinates;
import modules.Location;
import modules.Person;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Scanner;

public class InputDataManager {
    private Scanner scanner;

    public InputDataManager() {
        scanner = new Scanner(System.in);
    }

    private String readName() {
        String name;
        do {
            System.out.println("Enter name (Name can't be empty or null ) : ");
            name = scanner.nextLine();
        } while (name.equals(""));
        return name;
    }

    private Long readCoordinateX() {
        Long x;
        while (true) {
            try {
                System.out.println("Enter coordinate [X] (Should be less than 827) : ");
                String x1 = scanner.nextLine();
                x = Long.parseLong(x1);
                if (x > 827) System.err.println("Should be less than [827]");
                else break;
            } catch (NumberFormatException exception) {
                System.err.println("Should be a number");
            }
        }
        return x;
    }

    private int readCoordinateY() {
        int y = 0;
        while (true) {
            try {
                System.out.println("Enter coordinate [Y] : ");
                String y1 = scanner.nextLine();
                y = Integer.parseInt(y1);
                break;
            } catch (NumberFormatException exception) {
                System.err.println("Should be a number");
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
                System.out.println("Enter height (Should be greater than 0, Can be null) : ");
                String h1 = scanner.nextLine();
                if (h1.equals("")) break;
                h = Long.parseLong(h1);
                if(h<=0) throw new RuntimeException("Should be greater than 0");
                break;
            } catch (NumberFormatException exception) {
                System.err.println("Should be a number");
            } catch (RuntimeException exception){
                System.err.println(exception.getMessage());
            }
        }
        return h;
    }
    private Date readBirthday (){
        Date date;
        while(true){
            System.out.println("Enter birthday [yyyy-mm-dd] (Can be null) : ");
            String birthday = scanner.nextLine();
            if(birthday.equals(""))break;
            try{
                String[] birth = birthday.trim().split("-");
                int y = Integer.parseInt(birth[0]);
                if(y>9999) throw new RuntimeException("Year can't be greater than 9999");
                int m = Integer.parseInt(birth[1]);
                if(m>12) throw new RuntimeException("Month can't be greater than ");
                int d = Integer.parseInt(birth[2]);
                if(d > 31) throw new RuntimeException("Day can't be greater than 31");
                date = new Date(y,m,d);
                return date;
            }catch (NumberFormatException exception){
                System.err.println("Invalid input");
            }catch (RuntimeException exception){
                System.err.println(exception.getMessage());
            }
        }
        return null;
    }
    private Float readWeight(){
        Float weight;
        while (true){
            System.out.println("Enter weight (Should be greater than 0, Can be null) : ");
            String w = scanner.nextLine();
            if (w.equals(""))break;
            try{
               weight = Float.parseFloat(w);
               if(weight<=0) throw new RuntimeException("Should be greater than 0");
               return weight;
            }catch (NumberFormatException exception){
                System.err.println("Should be a number");
            }catch (RuntimeException exception){
                System.err.println(exception.getMessage());
            }
        }
        return null;
    }
    private Color readHairColor (){
        Color color = null;
        while (true){
            System.out.println("Enter hair color [RED, BLUE, BROWN] (Can be null ): ");
            String c = scanner.nextLine();
            if (c.equals("")) break;
            try{
                color = Color.valueOf(c.toUpperCase());
                break;
            }catch (IllegalArgumentException exception){
                System.err.println("No such color");
            }
        }
        return color;
    }
    private int readLocationX (){
        while (true){
            System.out.println("Enter location [X] : ");
            String xS = scanner.nextLine();
            try{
                int x = Integer.parseInt(xS);
                return x;
            }catch (NumberFormatException exception){
                System.err.println("Should be a number");
            }
        }
    }
    private double readLocationY(){
        while(true){
            System.out.println("Enter location [Y] : ");
            String yS = scanner.nextLine();
            try{
                double y = Double.parseDouble(yS);
                return y;
            }catch (NumberFormatException exception ){
                System.err.println("Should be a number");
            }
        }
    }
    private Long readLocationZ (){
        while (true){
            System.out.println("Enter location [Z] : ");
            String zS = scanner.nextLine();
            try{
                Long z = Long.parseLong(zS);
                return z;
            }catch (NumberFormatException exception ){
                System.err.println("Should be a number");
            }
        }
    }
    private Coordinates readCoordinates (){
        System.out.println("Enter coordinates : ");
        return new Coordinates(readCoordinateX(),readCoordinateY());
    }
    private Location readLocation(){
        System.out.println("Enter location : ");
        return new Location(readLocationX(),readLocationY(),readLocationZ());
    }
    public Person readPerson (){
        return new Person(readName(),readCoordinates(),readCreationDate(),readHeight(),readBirthday(),readWeight(),readHairColor(),readLocation());
    }
}
