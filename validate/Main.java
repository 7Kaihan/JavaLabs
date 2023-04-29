package validate;

public class Main {
    public static void main(String []args){
        PropertiesValidator<Integer> ageValidate = (Integer x)-> x>18;
        String message = "Enter age : ";
        String error = " Wrong number format !!!";
        String conditionError = " Should be greater than 18!!!";
        PropertiesValidatorImplementation<Integer>implementation = new PropertiesValidatorImplementation<>(message,error,conditionError, Integer.class,ageValidate);
        System.out.println(implementation.validated());
    }
}
