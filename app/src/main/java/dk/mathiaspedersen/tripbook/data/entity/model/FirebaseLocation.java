package dk.mathiaspedersen.tripbook.data.entity.model;

import com.google.android.gms.maps.model.LatLng;

public class FirebaseLocation {
    public String latitude;
    public String longitude;

    public FirebaseLocation() {
    }

    public FirebaseLocation(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public LatLng getMarker() {
        return new LatLng(Double.valueOf(latitude), Double.valueOf(longitude));
    }
}