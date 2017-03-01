package dk.mathiaspedersen.tripbook.presentation.model;

import com.google.android.gms.maps.model.LatLng;

public class DecodedTrip {

    private LatLng[] coordinates;

    public DecodedTrip(LatLng[] coordinates) {
        this.coordinates = coordinates;
    }

    public LatLng[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(LatLng[] coordinates) {
        this.coordinates = coordinates;
    }
}