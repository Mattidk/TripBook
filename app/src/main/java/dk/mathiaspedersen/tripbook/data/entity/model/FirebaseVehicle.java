package dk.mathiaspedersen.tripbook.data.entity.model;

import java.util.Map;

public class FirebaseVehicle {

    public String make;
    public String model;
    public String year;
    public Map<String, Long> odometer;

    public FirebaseVehicle() {}

    public FirebaseVehicle(String make, String model, String year, Map<String, Long> odometer) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.odometer = odometer;
    }

}