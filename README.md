This code currently contains basic methods for creating a route and generating directions from the Google Maps API

NOTE: this is my first time using Github, Maven, and basically all other project development tools, so if there are any errors/changes that would benefit you outside of the code itself please
email me.

pom.xml contains the list of dependencies for this project

getRoute.java contains the following methods:

all locations are entered by strings; enter in the format of "1834 Wake Forest Rd Winston-Salem NC 27109" for accuracy

makeRoute 
- parameters: origin and destination strings
- returns: DirectionsApiRequest Object for chain calling.
- creates route request between the two locations to be sent to the google maps API

confirmRoute
-Paramters: DirectionsApiRequest
-returns: DirectionsResult object - contains information on the route from the API

addWaypoint
-Parameters: DirectionsApiRequest, Waypont (String)
-Returns: DirectionsApiRequest for chain calls.
- adds waypoint to the route request

removeWaypoints
-Parameters: DirectionsApiRequest
-Returns: DirectionsApiRequest for chain calls.
-Removes all waypoints from the route.

modifyOrigin
-Parameters: DirectionsApiRequest, Origin (String)
-Returns: DirectionsApiRequest for chain calls.
-Changes route origin

modifyDestination
-Parameters: DirectionsApiRequest, Destination (String)
-Returns: DirectionsApiRequest for chain calls.
-Changes route destination.

*currently all routes currently are calculated using driving and have the departure time set when compiled

the DirectionsResult object contains all the route information, including:
- 

