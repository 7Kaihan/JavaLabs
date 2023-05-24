package connection;


import Modules.StudyGroup;

import java.io.Serializable;
public interface Request extends Serializable {
    String getStringArg();
    StudyGroup getStudyGroup();
    String getCommandName();
}
