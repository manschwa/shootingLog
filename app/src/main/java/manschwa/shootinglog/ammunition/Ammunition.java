package manschwa.shootinglog.ammunition;

import java.io.Serializable;

import manschwa.shootinglog.manufacturer.Manufacturer;

/**
 * The Ammunition class represents the ammo with which you shot your Result to keep track of
 * batch numbers and manufacturers.
 *
 * Created by Manuel on 04.07.15.
 */
public class Ammunition implements Serializable {

    private int id;
    private int batchNumber;
    private Manufacturer manufacturer;
    private String name;

    public Ammunition(int id, int batchNumber, Manufacturer manufacturer, String name) {
        this.id = id;
        this.batchNumber = batchNumber;
        this.manufacturer = manufacturer;
        this.name = name;
    }

    public Ammunition(int batchNumber, Manufacturer manufacturer, String name) {
        this.batchNumber = batchNumber;
        this.manufacturer = manufacturer;
        this.name = name;
    }

    public Ammunition() {}

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(int batchNumber) {
        this.batchNumber = batchNumber;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
