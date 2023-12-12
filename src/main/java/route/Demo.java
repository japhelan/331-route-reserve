package route;

import com.google.maps.DirectionsApiRequest;
import com.google.maps.StaticMapsRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;

import java.io.IOException;
import java.net.URISyntaxException;

import static route.getRoute.makeRoute;

public class Demo {
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException, ApiException {
        DirectionsApiRequest demoReq = makeRoute("Los Angeles","San Diego");
        DirectionsResult demoRes = new DirectionsResult();
        demoRes = getRoute.ConfirmRoute(demoReq);

        System.out.println(getRoute.getRouteTime(demoRes));
    }
}
