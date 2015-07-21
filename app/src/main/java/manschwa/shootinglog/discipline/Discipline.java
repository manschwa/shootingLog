package manschwa.shootinglog.discipline;

import java.io.Serializable;

/**
 * Created by Manuel on 04.07.15.
 */
public class Discipline implements Serializable{

    /** Distinct ID for the database. */
    private int id;

    /** Discipline name. */
    private String name;

    /** Number of total shots for this discipline. */
    private int totalShots;

    /** Number of passes (e.g. 60 total shots, 6 passes => 10 shots per pass). */
    private int numberOfPasses;

    /** Competition time in minutes. */
    private int timeInMinutes;

    /** Target distance in meters. */
    // TODO possibility of conversion, meters in yards
    private int distanceInMeters;

    /** Modification information form another position. */
    private String infos;

    public Discipline() {
    }

    public Discipline(int id, String name, int totalShots, int numberOfPasses, int timeInMinutes, int distanceInMeters, String infos) {
        this.id = id;
        this.name = name;
        this.totalShots = totalShots;
        this.numberOfPasses = numberOfPasses;
        this.timeInMinutes = timeInMinutes;
        this.distanceInMeters = distanceInMeters;
        this.infos = infos;
    }

    public Discipline(String name, int totalShots, int numberOfPasses, int timeInMinutes, int distanceInMeters, String infos) {
        this.name = name;
        this.totalShots = totalShots;
        this.numberOfPasses = numberOfPasses;
        this.timeInMinutes = timeInMinutes;
        this.distanceInMeters = distanceInMeters;
        this.infos = infos;
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

    public int getTotalShots() {
        return totalShots;
    }

    public void setTotalShots(int totalShots) {
        this.totalShots = totalShots;
    }

    public int getNumberOfPasses() {
        return numberOfPasses;
    }

    public void setNumberOfPasses(int numberOfPasses) {
        this.numberOfPasses = numberOfPasses;
    }

    public int getTimeInMinutes() {
        return timeInMinutes;
    }

    public void setTimeInMinutes(int timeInMinutes) {
        this.timeInMinutes = timeInMinutes;
    }

    public int getDistanceInMeters() {
        return distanceInMeters;
    }

    public void setDistanceInMeters(int distanceInMeters) {
        this.distanceInMeters = distanceInMeters;
    }

    public String getInfos() {
        return infos;
    }

    public void setInfos(String infos) {
        this.infos = infos;
    }
}
