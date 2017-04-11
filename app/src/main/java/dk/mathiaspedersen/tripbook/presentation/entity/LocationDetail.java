package dk.mathiaspedersen.tripbook.presentation.entity;

import com.google.android.gms.maps.model.LatLng;

import org.parceler.Parcel;

@Parcel
public class LocationDetail {

    public String location;
    public String latitude;
    public String longitude;
    public long timestamp;

    public LocationDetail() {}

    public LocationDetail(String location, String latitude, String longitude, long timestamp) {
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
    }

    public LatLng getLocation() {
        return new LatLng(Double.valueOf(latitude), Double.valueOf(longitude));
    }

}