package cz.thepetas.carregisterrestclient.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private Long id;

    private String name;

    private String surname;

    private String birthNumber;

    private Address address;

    private List<Vehicle> vehicles;


    public Person(String jsonString) throws JSONException {
        JSONObject json = new JSONObject(jsonString);
        id = json.getLong("id");
        name = json.getString("name");
        surname = json.getString("surname");
        birthNumber = json.getString("birthNumber");

        if (!json.isNull("address")) {
            address = new Address(json.get("address").toString());
        }
        if (!json.isNull("vehicles")) {
            JSONArray array = json.getJSONArray("vehicles");
            vehicles = new ArrayList<Vehicle>();
            for (int i = 0; i < array.length(); i++) {
                vehicles.add(new Car(array.get(i).toString()));
            }
        }
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthNumber() {
        return birthNumber;
    }

    public void setBirthNumber(String birthNumber) {
        this.birthNumber = birthNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public Long getCntCars() {
        return (vehicles != null) ? vehicles.size() : (long) 0;
    }
}
