package dk.mathiaspedersen.tripbook.presentation.model;

public class EncodedTrip {

    private String route;

    public EncodedTrip() {
    }

    public EncodedTrip(String route) {
        this.route = route;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }
}