package dk.mathiaspedersen.tripbook.data.entity.model;

import com.google.android.gms.maps.model.LatLng;

public class FirebaseLocation {

    public String location;
    public String latitude;
    public String longitude;
    public long timestamp;

    public FirebaseLocation() {}

    public FirebaseLocation(String location, String latitude, String longitude, long timestamp) {
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
    }

    public LatLng getLocation() {
        return new LatLng(Double.valueOf(latitude), Double.valueOf(longitude));
    }
}