package commands;

import connection.Request;
import connection.Response;

public interface Command {
     Response run();
     String getName();
     CommandType getType();
     void setArgument(Request a);
}
