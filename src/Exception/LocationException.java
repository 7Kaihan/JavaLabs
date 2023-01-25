package Exception;

public class LocationException extends RuntimeException{
    public LocationException(String message,Throwable cause){
        super(message,cause);
    }
}
