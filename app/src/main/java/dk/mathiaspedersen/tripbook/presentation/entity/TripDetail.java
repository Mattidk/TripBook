package dk.mathiaspedersen.tripbook.presentation.entity;

import org.parceler.Parcel;

@Parcel
public class TripDetail {

    String key;
    String path;
    String simplepath;
    String purpose;
    VehicleDetail vehicle;
    Long distance;
    LocationDetail departure;
    LocationDetail destination;

    public TripDetail() {}

    public TripDetail(String key, String path, String simplepath, VehicleDetail vehicle, String purpose,
                      Long distance, LocationDetail departure, LocationDetail destination) {
        this.key = key;
        this.path = path;
        this.simplepath = simplepath;
        this.vehicle = vehicle;
        this.purpose = purpose;
        this.distance = distance;
        this.departure = departure;
        this.destination = destination;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSimplepath() {
        return simplepath;
    }

    public void setSimplepath(String simplepath) {
        this.simplepath = simplepath;
    }

    public VehicleDetail getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleDetail vehicle) {
        this.vehicle = vehicle;
    }

    public Long getDistance() {
        return distance;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public LocationDetail getDeparture() {
        return departure;
    }

    public void setDeparture(LocationDetail departure) {
        this.departure = departure;
    }

    public LocationDetail getDestination() {
        return destination;
    }

    public void setDestination(LocationDetail destination) {
        this.destination = destination;
    }
}