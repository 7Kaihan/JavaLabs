package validate;

import java.util.Scanner;

public class PropertiesValidatorImplementation <T>{
    private T property;
    private PropertiesValidator propertiesValidator;
    private Scanner scanner;
    private Class<T>classType;

    public Class<T> getClassType() {
        return classType;
    }

    public PropertiesValidatorImplementation(String message, String errorMessage,String conditionError, Class<T> classType, PropertiesValidator<T> propertiesValidator){
        this.propertiesValidator = propertiesValidator;
        this.classType = classType;
        scanner = new Scanner(System.in);
        while(true){
            System.out.println(message);
            if(getClassType() == Integer.class){
                String number = scanner.nextLine();
                try{
                    Integer x = Integer.parseInt(number);
                    if(propertiesValidator.validate((T)x)){
                        property = (T)x;
                        break;
                    }else System.err.println(conditionError);
                }catch (NumberFormatException exception){
                    System.err.println(errorMessage);
                }
            }

        }
    }
    public T validated(){
        return property;
    }

}
