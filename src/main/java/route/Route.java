package route;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.Distance;
import com.google.maps.model.TravelMode;

import javax.print.attribute.standard.Destination;
import java.io.IOException;
import java.net.URISyntaxException;
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

    private DirectionsApiRequest routeReq;
    private DirectionsResult routeRes;

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

    public DirectionsApiRequest convertToReq() throws IOException, URISyntaxException, InterruptedException, ApiException {
        routeReq = getRoute.makeRoute(Origin.returnAddress(),Destination.returnAddress());
        return routeReq;
    }

    public DirectionsResult convertToRes() throws IOException, InterruptedException, ApiException {
        routeRes = getRoute.ConfirmRoute(routeReq);
        return routeRes;
    }

    public void showDirections(){
        int numLegs,numSteps;
        numLegs = routeRes.routes[0].legs.length;
        for (int i = 0; i < numLegs; i++){
            numSteps = routeRes.routes[0].legs[i].steps.length;
            for (int j = 0; j < numSteps; j++){
                System.out.println(routeRes.routes[0].legs[i].steps[j].htmlInstructions);
            }
        }
    }


    public void printSummary(){
        System.out.println(name + ": ");
        System.out.println(Origin.returnAddress() + " to " + Destination.returnAddress());
        System.out.println("Distance: WIP");
        System.out.println("Duration: WIP");

    }

    public DirectionsApiRequest getRouteReq() {
        return routeReq;
    }

    public DirectionsResult getRouteRes() {
        return routeRes;
    }
}
