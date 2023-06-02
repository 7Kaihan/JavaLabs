package common.connection;

public class ResponseMessage implements Response {

    private String message;

    public ResponseMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
