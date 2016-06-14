package cz.thepetas.carregisterrestclient.data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyJsonParser {

    public static Car[] getCarsFromJSON(String json) {
        if (json != null) {
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

            Car carsArray[] = new Car[cars.size()];
            cars.toArray(carsArray);
            return carsArray;
        }
        return null;
    }

    public static Address[] getAddressesFromJSON(String json) {
        if (json != null) {
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
            Address addressesArray[] = new Address[addresses.size()];
            addresses.toArray(addressesArray);
            return addressesArray;
        }
        return null;
    }

    public static Person[] getPersonsFromJSON(String json) {
        if (json != null) {
            List<Person> persons = new ArrayList<Person>();
            Log.i("MY_JSON_PARSER", "VALUE: " + json);
            JSONArray array = null;
            try {
                array = new JSONArray(json);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject jsonObject = (JSONObject) array.get(i);
                    persons.add(new Person(jsonObject.toString()));
                }
            } catch (JSONException ignored) {
                Log.i("MY_JSON_PARSER", "JSON ERROR: " + ignored.getMessage());
            }
            Person personsArray[] = new Person[persons.size()];
            persons.toArray(personsArray);
            return personsArray;
        }
        return null;
    }
}
