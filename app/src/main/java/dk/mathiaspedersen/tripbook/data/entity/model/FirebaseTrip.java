package dk.mathiaspedersen.tripbook.data.entity.model;

public class FirebaseTrip {

    public String route;
    public FirebaseMarkers markers;

    public FirebaseTrip() {}

    public FirebaseTrip(String route, FirebaseMarkers markers) {
        this.route = route;
        this.markers = markers;
    }
}
