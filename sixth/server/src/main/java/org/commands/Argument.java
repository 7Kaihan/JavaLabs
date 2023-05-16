package org.commands;

import org.connection.Request;
import org.modules.StudyGroup;

public class Argument implements Request {
    private String arg;
    private StudyGroup studyGroup;
    public Argument(String s, StudyGroup st){
        arg = s;
        studyGroup = st;
    }
    public String getStringArg(){
        return arg;
    }
    public StudyGroup getStudyGroup(){
        return studyGroup;
    }
    public String getCommandName(){
        return "";
    }
}
