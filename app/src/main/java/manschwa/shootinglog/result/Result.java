package manschwa.shootinglog.result;

import java.util.Date;

import manschwa.shootinglog.weapon.Weapon;
import manschwa.shootinglog.ammunition.Ammunition;
import manschwa.shootinglog.discipline.Discipline;
import manschwa.shootinglog.event.Event;
import manschwa.shootinglog.location.Location;

/**
 * Created by Manuel on 04.07.15.
 */
public class Result {

    private int id;
    private Date date;
    private Discipline discipline;
    private int[] passes;
    private int result;
    private Event event;
    private Location location;
    private Weapon weapon;
    private Ammunition ammunition;
    private String notes;

//     TODO incorperate picture/photography of the result
//     private Object picture;

    public Result(int id, Date date, Discipline discipline, int[] passes, int result, Event event, Location location, Weapon weapon, Ammunition ammunition, String notes) {
        this.id = id;
        this.date = date;
        this.discipline = discipline;
        this.passes = passes;
        this.result = result;
        this.event = event;
        this.location = location;
        this.weapon = weapon;
        this.ammunition = ammunition;
        this.notes = notes;
    }

    public Result(Date date, Discipline discipline, int[] passes, int result, Event event, Location location, Weapon weapon, Ammunition ammunition, String notes) {
        this.date = date;
        this.discipline = discipline;
        this.passes = passes;
        this.result = result;
        this.event = event;
        this.location = location;
        this.weapon = weapon;
        this.ammunition = ammunition;
        this.notes = notes;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public int[] getPasses() {
        return passes;
    }

    public void setPasses(int[] passes) {
        this.passes = passes;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Ammunition getAmmunition() {
        return ammunition;
    }

    public void setAmmunition(Ammunition ammunition) {
        this.ammunition = ammunition;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
