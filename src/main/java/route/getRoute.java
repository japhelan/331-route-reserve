package route;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.*;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;
import okhttp3.Address;
import org.apache.commons.math3.stat.inference.TestUtils;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import com.google.maps.DirectionsApi.RouteRestriction;
import com.google.maps.errors.NotFoundException;
import com.google.maps.model.LatLng;
import com.google.maps.model.TrafficModel;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class getRoute {


//    Creates object GeoApiContext which allows use of GoogleMaps API with my key
    private static GeoApiContext getGeoContext(){
        GeoApiContext GAC = new GeoApiContext.Builder().apiKey("ENTER YOUR GOOGLE MAPS API KEY HERE").build();
        return GAC;
    }


    public static DirectionsApiRequest makeRoute(String origin, String destination) throws IOException, InterruptedException, ApiException, URISyntaxException {
        GeoApiContext GAC = getGeoContext();
        DirectionsApiRequest req = new DirectionsApiRequest(GAC);
        req = DirectionsApi.newRequest(GAC);

    req.mode(TravelMode.DRIVING);
    req.origin(origin);
    req.destination(destination);
    req.departureTimeNow();

    return req;
    }

    public static DirectionsApiRequest modifyOrigin(DirectionsApiRequest req, String origin){
        req.origin(origin);
        return req;
    }

    public static DirectionsApiRequest addWaypoint(DirectionsApiRequest req, String waypoint){
        req.waypoints(waypoint);
        return req;
    }

    public static DirectionsApiRequest removeWaypoints(DirectionsApiRequest req, String waypoint){
        req.waypoints("");
        return req;
    }

    public static void cancelRouteRequest(DirectionsApiRequest req){
        req.cancel();
    }
    public static DirectionsApiRequest modifyDestination(DirectionsApiRequest req, String destination){
        req.destination(destination);
        return req;
    }

    public static DirectionsResult ConfirmRoute(DirectionsApiRequest req) throws IOException, InterruptedException, ApiException {
        DirectionsResult result = req.await();
        return result;
    }

    public static int getRouteTime(DirectionsResult result){
        Duration buffer;
        int timeMinutes;
        buffer = result.routes[0].legs[0].duration;
        timeMinutes = (int) buffer.inSeconds;
        timeMinutes = timeMinutes/60;

        return timeMinutes;
    }

    public static void displaySummary(DirectionsResult result){
        int numLegs,numSteps;
         numLegs = result.routes[0].legs.length;
        for (int i = 0; i < numLegs; i++){
            numSteps = result.routes[0].legs[i].steps.length;
            for (int j = 0; j < numSteps; j++){
                System.out.println(result.routes[0].legs[i].steps[j].htmlInstructions);
            }
        }
    }



}
