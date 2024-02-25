package connection;

import java.io.Serializable;

public class Response implements Serializable {
    private String responseMessage;
    public Response (String responseMessage){
        this.responseMessage = responseMessage;
    }

    @Override
    public String toString() {
        return responseMessage;
    }
}
