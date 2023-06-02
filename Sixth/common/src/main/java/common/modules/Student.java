package common.modules;


import java.io.Serializable;

public class Student implements Serializable {
    private int id;
    private String name;
    private String lastName;
    public Student(int id,String name,String lastName){
        this.id = id;
        this.name = name;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setName(String name) {
        this.name = name;
    }
}
