package manschwa.shootinglog.weapon;

import java.io.Serializable;

import manschwa.shootinglog.manufacturer.Manufacturer;

/**
 * The Weapon class represents a Weapon in the database with all the relevant data.
 * A Weapon belongs to a Result and a Result can only have 1 Weapon.
 *
 * Created by Manuel on 04.07.15.
 */
public class Weapon implements Serializable {

    private int id;
    private int serialNumber;
    private Manufacturer manufacturer;
    private String model;

    public Weapon(int id, int serialNumber, Manufacturer manufacturer, String model) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public Weapon(int serialNumber, Manufacturer manufacturer, String model) {
        this(0, serialNumber, manufacturer, model);
    }

    public Weapon() {

    }

    public void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return this.id;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
