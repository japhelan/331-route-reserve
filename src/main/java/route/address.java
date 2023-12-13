package route;

public class address {
    String street;
    String city;
    String state;
    int zip;

    public address(){
        street = "";
        city = "";
        state = "";
        zip = -1;
    }
    public address(String street, String city, String state){
        this.street = street;
        this.city = city;
        this.state = state;
    }
    public address(String street, String city, String state,int zip){
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getStreet() {
        return street;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public int getZip() {
        return zip;
    }
    public String returnAddress(){
        String address = street;
        address = address.concat(" " + city);
        address = address.concat(" " + state);

        if (this.zip != -1){
            address = address.concat(" " + zip);
        }

        return address;
    }



}
