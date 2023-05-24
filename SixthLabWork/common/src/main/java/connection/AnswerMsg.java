package connection;

public class AnswerMsg implements Response{
    private String message;
    private Status status;

    public AnswerMsg(){
        message = "";
        status = Status.FINE;
    }
    public AnswerMsg clear(){
        message = "";
        return this;
    }
    public AnswerMsg info (Object str){
        message = str.toString();
        return this;
    }
    public AnswerMsg error(Object str){
        message = str.toString();
        setStatus(Status.ERROR);
        return this;
    }
    public AnswerMsg setStatus(Status status){
        this.status = status;
        return this;
    }
    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Status getStatus() {
        return status;
    }
    @Override
    public String toString(){
        switch (getStatus()){
            case ERROR:
                return "Error: "+getMessage();
            default:
                return getMessage();
        }
    }
}
