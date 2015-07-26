package manschwa.shootinglog.location;

/**
 * Created by Manuel on 04.07.15.
 */
public class Location {

    private int id;
    private String name;
    private String address;

    public Location(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Location(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Location() {

    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
