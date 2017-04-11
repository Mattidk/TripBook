package dk.mathiaspedersen.tripbook.data.entity.model;

public class FirebaseTrip {

    public String path;
    public String simplepath;
    public String vehicle;
    public String purpose;
    public long distance;
    public FirebaseLocation departure;
    public FirebaseLocation destination;

    public FirebaseTrip() {}

    public FirebaseTrip(String path, String simplepath, String vehicle, String purpose, long distance, FirebaseLocation departure, FirebaseLocation destination) {
        this.path = path;
        this.simplepath = simplepath;
        this.vehicle = vehicle;
        this.purpose = purpose;
        this.distance = distance;
        this.departure = departure;
        this.destination = destination;
    }
}