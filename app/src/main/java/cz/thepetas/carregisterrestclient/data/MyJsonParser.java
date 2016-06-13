package cz.thepetas.carregisterrestclient.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyJsonParser {

    public static List<Car> getCarsFromJSON(String json) {
        List<Car> cars = new ArrayList<Car>();
        JSONArray array = null;
        try {
            array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = (JSONObject) array.get(i);
                cars.add(new Car(jsonObject.toString()));
            }
        } catch (JSONException ignored) {
        }
        return cars;
    }

    public static List<Address> getAddressesFromJSON(String json) {
        List<Address> addresses = new ArrayList<Address>();
        JSONArray array = null;
        try {
            array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = (JSONObject) array.get(i);
                addresses.add(new Address(jsonObject.toString()));
            }
        } catch (JSONException ignored) {
        }
        return addresses;
    }

    public static List<Person> getPersonsFromJSON(String json) {
        List<Person> persons = new ArrayList<Person>();
        JSONArray array = null;
        try {
            array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = (JSONObject) array.get(i);
                persons.add(new Person(jsonObject.toString()));
            }
        } catch (JSONException ignored) {
        }
        return persons;
    }
}
