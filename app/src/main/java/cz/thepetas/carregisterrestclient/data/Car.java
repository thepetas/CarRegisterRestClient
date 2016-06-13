package cz.thepetas.carregisterrestclient.data;

import org.json.JSONException;
import org.json.JSONObject;

public class Car extends Vehicle {

    private String idMark;

    private Long id;

    private String brand;

    private String model;

    private Person owner;

    public String getIdMark() {
        return idMark;
    }

    public void setIdMark(String idMark) {
        this.idMark = idMark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }


    public Car(String jsonString) throws JSONException {

        JSONObject json = new JSONObject(jsonString);
        id = json.getLong("id");
        brand = json.getString("brand");
        model = json.getString("model");
        idMark = json.getString("idMark");


        if (!json.isNull("owner")) {
            System.out.println(json.get("owner").toString());
            owner = new Person(json.get("owner").toString());
        }
    }
}
