package route;

import com.google.maps.DirectionsApiRequest;
import com.google.maps.StaticMapsRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import org.apache.commons.math3.analysis.function.Add;
import org.checkerframework.checker.units.qual.A;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static route.getRoute.makeRoute;

public class Demo {

    public static void menu(Scanner scnr,RouteList userList,RouteList userHistory) throws IOException, URISyntaxException, InterruptedException, ApiException {
        int input;
        boolean cont = true;
        Route selectedRoute = new Route();

            System.out.println("Hello! Welcome to the route reservation demo. Enter a number to continue.");
            System.out.println("1. Create a route");
            System.out.println("2. Select a saved route.");
            System.out.println("3. Modify a saved route.");
            System.out.println("4. View previously booked routes.");
            System.out.println("5. Exit");
            ;
            input = scnr.nextInt();
            if (input == 1) { //MAKE A ROUTE
                System.out.println("Great! lets make a route.");
                selectedRoute = createRoute(scnr, userList);
            } else if (input == 2) { //SELECT A SAVED ROUTE
                System.out.println("Great! lets select one of your saved routes.");
                selectedRoute = selectFromList(scnr, userList);

            } else if (input == 3) { //MODIFY A SAVED ROUTE
                System.out.println("First, selected a saved route.");
                selectedRoute = selectFromList(scnr, userList);
                System.out.println("Great! how would you like to modify " + selectedRoute.getName() + "?");
                modifyRoute(selectedRoute, scnr);

            } else if (input == 4) { //VIEW ROUTE HISTORY
                System.out.println("Route History: Enter a corresponding number to see details");
                selectHistory(scnr,userHistory);

            } else if (input == 5) { //EXIT
                System.out.println("Are you sure you would like to exit?");
                System.out.println("ENTER 1 TO CONFIRM EXIT. ENTER ANYTHING ELSE TO CONTINUE.");
                input = scnr.nextInt();
                if (input == 1) {
                    System.out.println("Now exiting. See you next time!");
                    return;
                } else {
                    menu(scnr, userList,userHistory);
                }

            } else {
                System.out.println("Unknown input detected. please try again.");
                menu(scnr, userList, userHistory);
            }

            System.out.println("Your current route is: " + selectedRoute.getName());
        System.out.println("Enter a number");
        System.out.println("1: Confirm your ride.");
        System.out.println("2: Create/Select/Modify another route");
        System.out.println("Other: EXIT");

        input = scnr.nextInt();
        if (input == 1){
            selectedRoute.convertToReq();
            selectedRoute.convertToRes();
            System.out.println("Route booked!");
            userHistory.addToList(selectedRoute);

            System.out.println("Would you like printed directions? Enter 1 for yes, anything else to return to menu.");
            input = scnr.nextInt();
            if (input == 1){
                selectedRoute.showDirections();
            }
            menu(scnr,userList,userHistory);
        }
        else if (input == 2){
            menu(scnr,userList,userHistory);
        }
        else{
            System.out.println("Thank you for trying the route reservation demo!");
            return;
        }
        }


    public static Route createRoute(Scanner scnr,RouteList userList){
        int input, inc;
        inc = 0;
        Route rte = new Route();
        ArrayList<address> wypts = new ArrayList<address>();
        address orig = new address();
        address desti = new address();
        address buffer = new address();

        System.out.println("Create a route: please enter the following for maximum accuracy.");
        System.out.println("Enter information on your pick up point");
        inputAddress(orig,scnr);
        System.out.println("Great! now enter information on the destination.");
        inputAddress(desti,scnr);

        System.out.println("Great! would you like to add any stops to your ride? Enter 1 for yes.");
        input = scnr.nextInt();
        if (input == 1){
            boolean cont = true;
            while (cont){
                System.out.println("Enter the waypoint address.");
                inputAddress(buffer,scnr);
                wypts.set(inc, buffer);
                inc++;
                System.out.println("Enter 1 if you would like to enter another waypoint. If not, press anything else.");
                input = scnr.nextInt();
                if (input != 1){
                    cont = false;
                }
            }
        }
        else{
            System.out.println("Okay, lets continue.");
        }
        rte.setOrigin(orig);
        rte.setDestination(desti);
        rte.setWaypoints(wypts);

        System.out.println("Enter 1 if you would like to modify your route.");
        input = scnr.nextInt();
        if (input == 1){
            rte = modifyRoute(rte,scnr);
        }

        System.out.println("Would you like to save your route for future use? Enter 1 if yes.");
        input = scnr.nextInt();
        if (input == 1){
            System.out.println("Enter a name for your route.");
            rte.setName(scnr.nextLine());
            userList.addToList(rte);
        }
        return rte;
    }

    public static Route modifyRoute(Route rte, Scanner scnr){
        int input;
        ArrayList<address> empty = new ArrayList<>();
        boolean cont = true;
        address buffer = new address();
        while (cont) {
            System.out.println("Enter a number:");
            System.out.println("1: change origin");
            System.out.println("2: change destination");
            System.out.println("3: add a waypoint");
            System.out.println("4: clear all waypoints");
            System.out.println("OTHER: exit/continue without modifying");
            input = scnr.nextInt();
            if (input == 1) {
                System.out.println("Enter new origin:");
                inputAddress(buffer, scnr);
                rte.setOrigin(buffer);

            } else if (input == 2) {
                System.out.println("Enter new destination:");
                inputAddress(buffer, scnr);
                rte.setDestination(buffer);

            } else if (input == 3) {
                System.out.println("Enter a new waypoint");
                inputAddress(buffer,scnr);
                rte.getWaypoints().add(buffer);

            } else if (input == 4) {
                System.out.println("Are you sure you would like to clear ALL waypoints? Enter 1 to confirm.");
                input = scnr.nextInt();
                if (input == 1){
                    rte.setWaypoints(empty);
                }
                else{
                    System.out.println("Waypoints have not been modified.");
                }

            } else {
                System.out.println("Now returning.");
                cont = false;
            }
        }
        return rte;
    }
    public static Route selectFromList(Scanner scnr, RouteList userList) {
        int input;
        Route buffRte = new Route();
        System.out.println("Enter the corresponding number to your desired route.");
        userList.displayList();
        input = scnr.nextInt();
        input = input - 1;
        for (int i = 0; i < userList.getRouteList().size(); i++) {
            if (input == i) {
                System.out.println("You selected: " + userList.getRouteList().get(i).getName());
                return userList.getRouteList().get(i);
            }
        }

        System.out.println("Route not recognized. Enter 1 to retry. Enter anything else to return to the main menu.");
        input = scnr.nextInt();
        if (input == 1) {
            selectFromList(scnr, userList);
        } else {
            return buffRte;
        }

        return buffRte;
    }

    public static void selectHistory(Scanner scnr, RouteList userList){
        int input;
        String d;
        Route buffRte = new Route();
        System.out.println("Enter the corresponding number to see route info.");
        userList.displayList();
        input = scnr.nextInt();
        input--;
        for (int i = 0; i < userList.getRouteList().size(); i++){
            if (input == i){
                userList.getRouteList().get(i).printSummary();
                System.out.println("Press anything to return.");
                d = scnr.nextLine();
                return;
            }
            else {
                System.out.println("Route not recognized. Enter 1 to retry. Enter anything else to return to the main menu.");
                input = scnr.nextInt();
                if (input == 1){
                    selectFromList(scnr,userList);
                }
                else{
                    return;
                }
            }
        }
        return;
    }

    public static address inputAddress(address addy, Scanner scnr){//INPUT ADDRESS FROM TEXT
        System.out.println("Enter the following parts of your desired address:");
        System.out.println("street address");
        addy.setStreet(scnr.nextLine());
        System.out.println("city");
        addy.setCity(scnr.next());
        System.out.println("state");
        addy.setState(scnr.next());
        System.out.println("zipcode");
        addy.setZip(scnr.nextInt());
        System.out.println("Thanks.");
        return addy;
    }

    public static RouteList loadDemoRoutes(){ //SAMPLE ROUTES FOR DEMO
        ArrayList<Route> demoRoutes = new ArrayList<>();
        address demo1 = new address("1834 Wake Forest Rd", "Winston-Salem", "NC");
        address demo2 = new address("515 Rock Glen Dr", "Wynnewood", "Pennsylvania");
        address demo3 = new address("1000 Ted Johnson Pkwy", "Greensboro", "NC");
        address demo4 = new address("11 W 53rd St", "New York", "NY");
        address demo5 = new address("2800 E Observatory Rd", "Los Angeles", "CA");
        address demo6 = new address("10 Broadway St", "Asheville", "NC");

        Route demoRte1 = new Route(demo1,demo3, "wake to airport");
        Route demoRte2 = new Route(demo1,demo6, "wake to asheville");
        Route demoRte3 = new Route(demo2,demo4, "my house to new york");
        Route demoRte4 = new Route(demo1,demo2, "wake to my house");
        Route demoRte5 = new Route(demo4,demo5, "what is wrong with me");
        Route demoRte6 = new Route(demo3,demo6, "airport to asheville");
        demoRoutes.add(demoRte1);
        demoRoutes.add(demoRte2);
        demoRoutes.add(demoRte3);
        demoRoutes.add(demoRte4);
        demoRoutes.add(demoRte5);
        demoRoutes.add(demoRte6);
        RouteList demoList = new RouteList(demoRoutes);
        return demoList;
    }

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException, ApiException {
       Scanner scnr = new Scanner(System.in);
       RouteList demoList = loadDemoRoutes();
       ArrayList<Route> emptHistory = new ArrayList<>();
       RouteList history = new RouteList(emptHistory);

       menu(scnr,demoList,history);


    }
}
