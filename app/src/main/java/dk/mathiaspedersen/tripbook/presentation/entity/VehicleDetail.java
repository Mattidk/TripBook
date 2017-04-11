package dk.mathiaspedersen.tripbook.presentation.entity;

import org.parceler.Parcel;

import java.util.Map;

@Parcel
public class VehicleDetail {

    public String make;
    public String model;
    public String year;
    public Map<String, Long> odometer;

    public VehicleDetail() {}

    public VehicleDetail(String make, String model, String year, Map<String, Long> odometer) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.odometer = odometer;
    }

}