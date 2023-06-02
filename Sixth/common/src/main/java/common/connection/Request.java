package common.connection;

import common.modules.Student;

import java.io.Serializable;

public interface Request extends Serializable {
    String getCommandName();
    String getCommandArgument();
    Student getObject();
}
