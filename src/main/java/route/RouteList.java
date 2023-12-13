package route;

import java.util.ArrayList;

public class RouteList {

    public RouteList(ArrayList<Route> routes){
        routeList = routes;
    }

    private String listName;
    protected ArrayList<Route> routeList = new ArrayList<>();

    public void setListName(String listName) {
        this.listName = listName;
    }

    public void setRouteList(ArrayList<Route> routeList) {
        this.routeList = routeList;
    }

    public ArrayList<Route> getRouteList() {
        return routeList;
    }

    public String getListName() {
        return listName;
    }
    public void displayList(){
        for (int i = 0; i < routeList.size(); i++) {
            int display = i + 1;
            System.out.println(display + ": " + routeList.get(i).getName());
        }
    }

    public void addToList(Route rte){
        routeList.add(rte);
    }

    public void clearList(){
        ArrayList<Route> empty = new ArrayList<>();
        routeList = empty;
    }



}
