package dk.mathiaspedersen.tripbook.data.entity.model;

public class FirebaseTrip {

    public String route;
    public FirebaseMarkers markers;
    public String time;

    public FirebaseTrip() {}

    public FirebaseTrip(String route, FirebaseMarkers markers, String time) {
        this.route = route;
        this.markers = markers;
        this.time = time;
    }
}
