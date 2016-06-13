package cz.thepetas.carregisterrestclient.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Address {

    private Long id;

    private String street;

    private String houseNumber;

    private String zipCode;

    private String city;

    private List<Person> persons;


    public Address(String jsonString) throws JSONException {
        JSONObject json = new JSONObject(jsonString);
        id = json.getLong("id");
        street = json.getString("street");
        houseNumber = json.getString("houseNumber");
        zipCode = json.getString("zipCode");
        city = json.getString("city");

        if (!json.isNull("persons")) {
            JSONArray array = json.getJSONArray("persons");
            persons = new ArrayList<Person>();
            for (int i = 0; i < array.length(); i++) {
                persons.add(new Person(array.get(i).toString()));
            }

        }
    }


    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }


    public boolean compareDetails(Address address) {
        return street.equals(address.street) && houseNumber.equals(address.houseNumber)
                && zipCode.equals(address.zipCode) && city.equals(address.city);
    }

}

