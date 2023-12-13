package route;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.model.TravelMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

public class Route {

    public Route(address Origin, address Destination, String name){
        this.Origin = Origin;
        this.Destination = Destination;
        this.name = name;

    }
    public Route(){
        Origin = null;
        Destination = null;
        name = "empty address" ;
    }
    private address Origin;
    private address Destination;
    private ArrayList<address> waypoints = new ArrayList<address>();
    private TravelMode mode;
    private String name;

    public void setDestination(address destination) {
        Destination = destination;
    }

    public void setOrigin(address origin) {
        Origin = origin;
    }

    public void setMode(TravelMode mode) {
        this.mode = mode;
    }

    public void setWaypoints(ArrayList<address> waypoints) {
        this.waypoints = waypoints;
    }

    public address getDestination() {
        return Destination;
    }

    public address getOrigin() {
        return Origin;
    }

    public ArrayList<address> getWaypoints() {
        return waypoints;
    }

    public TravelMode getMode() {
        return mode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
