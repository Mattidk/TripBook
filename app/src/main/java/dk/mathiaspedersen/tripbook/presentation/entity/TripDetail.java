package dk.mathiaspedersen.tripbook.presentation.entity;

import com.google.android.gms.maps.model.LatLng;

import org.parceler.Parcel;

import java.util.Date;

@Parcel
public class TripDetail {

    String key;
    Date time;
    LatLng start;
    LatLng end;
    String map;

    public TripDetail() {}

    public TripDetail(String key, Date time, LatLng start, LatLng end, String map) {
        this.key = key;
        this.time = time;
        this.start = start;
        this.end = end;
        this.map = map;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public LatLng getStart() {
        return start;
    }

    public void setStart(LatLng start) {
        this.start = start;
    }

    public LatLng getEnd() {
        return end;
    }

    public void setEnd(LatLng end) {
        this.end = end;
    }

    public String getMap() {
        return map;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setMap(String map) {
        this.map = map;
    }
}