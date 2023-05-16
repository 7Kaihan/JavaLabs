package org.connection;


import org.modules.StudyGroup;

import java.io.Serializable;

public interface Request extends Serializable{
    public String getStringArg();
    public StudyGroup getStudyGroup();
    public String getCommandName();
}
