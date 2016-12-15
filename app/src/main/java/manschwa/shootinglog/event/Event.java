package manschwa.shootinglog.event;

import java.io.Serializable;

/**
 * The Event class represents events (or categories) in which you achieved your result
 * (e.g. training, championship (local or national)).
 * An Event belongs to a Result (1:N relationship).
 *
 * Created by Manuel on 04.07.15.
 */
public class Event implements Serializable {

    /** The Event's ID. */
    private int id;

    /** The name of the Event. */
    private String name;


    public Event(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Event(String name) {
        this.name = name;
    }

    public Event() {

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
