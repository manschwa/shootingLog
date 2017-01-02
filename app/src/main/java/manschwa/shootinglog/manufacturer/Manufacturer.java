package manschwa.shootinglog.manufacturer;

import java.io.Serializable;

/**
 * The Manufacturer class represents a manufacturer for either Weapons or Ammunition.
 *
 * Created by Manuel on 04.07.15.
 */
public class Manufacturer implements Serializable {

    private int id;
    private String name;

    public Manufacturer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Manufacturer(String name) {
        this.name = name;
    }

    public Manufacturer() {

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
}
