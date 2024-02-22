package modules;

import java.time.ZonedDateTime;
import java.util.Date;

public class Person implements Comparable<Person>{
    private Integer id;
    private String name;
    private Coordinates coordinates;
    private ZonedDateTime creationDate;
    private Long height;
    private Date birthday;
    private Float weight;
    private Color hairColor;
    private Location location;
    public Person(String name,Coordinates coordinates,ZonedDateTime creationDate,
                  Long height, Date birthday,Float weight,Color hairColor,Location location){
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.height = height;
        this.birthday = birthday;
        this.weight = weight;
        this.hairColor = hairColor;
        this.location = location;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Color getHairColor() {
        return hairColor;
    }

    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (!id.equals(person.id)) return false;
        if (!name.equals(person.name)) return false;
        if (!coordinates.equals(person.coordinates)) return false;
        if (!creationDate.equals(person.creationDate)) return false;
        if (!height.equals(person.height)) return false;
        if (!birthday.equals(person.birthday)) return false;
        if (!weight.equals(person.weight)) return false;
        if (hairColor != person.hairColor) return false;
        return location.equals(person.location);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + coordinates.hashCode();
        result = 31 * result + creationDate.hashCode();
        result = 31 * result + location.hashCode();
        return result;
    }

    @Override
    public String toString() {
        String s = "";
        if(height != null && weight != null && birthday!=null && hairColor !=null)
        s =  "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", height=" + height +
                ", birthday=" + birthday +
                ", weight=" + weight +
                ", hairColor=" + hairColor +
                ", location=" + location +
                '}';
        if(height == null || weight == null || birthday == null || hairColor == null){
            s =  "Person{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", coordinates=" + coordinates +
                    ", creationDate=" + creationDate;
            if(height == null && weight != null && birthday != null && hairColor != null){
                s += ",weight=" + weight +
                        ",birthday="+ birthday+
                        ", hairColor=" + hairColor;
            }
            if(height == null && weight == null && birthday != null && hairColor != null){
                s +=
                        ",birthday="+ birthday+
                        ", hairColor=" + hairColor;
            }
            if(height == null && weight == null && birthday == null && hairColor != null){
                s +=
                                ", hairColor=" + hairColor;
            }
            if(height == null && weight == null && birthday == null && hairColor == null){
                s +=
                        ",birthday="+ birthday+
                                ", hairColor=" + hairColor;
            }

        }
        return s+",location="+ location+
            "}";
    }

    @Override
    public int compareTo(Person o) {
        return Long.compare(this.height,o.getHeight());
    }
}
