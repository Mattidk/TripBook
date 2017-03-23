package dk.mathiaspedersen.tripbook.data.entity.model;

public class FirebaseMarkers {
    public FirebaseLocation start;
    public FirebaseLocation end;

    public FirebaseMarkers() {
    }

    public FirebaseMarkers(FirebaseLocation start, FirebaseLocation end) {
        this.start = start;
        this.end = end;
    }
}