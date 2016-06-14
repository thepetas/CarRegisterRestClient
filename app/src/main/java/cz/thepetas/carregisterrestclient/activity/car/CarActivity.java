package cz.thepetas.carregisterrestclient.activity.car;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import cz.thepetas.carregisterrestclient.R;
import cz.thepetas.carregisterrestclient.data.Car;

public class CarActivity extends AppCompatActivity {


    private Car mCar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        mCar = (Car) bundle.getSerializable(CarTabFragment.CAR_OBJECT);

        ((TextView) findViewById(R.id.carId)).setText("ID: " + mCar.getId());
        ((TextView) findViewById(R.id.carIdMark)).setText("ID mark: " + mCar.getIdMark());
        ((TextView) findViewById(R.id.carBrand)).setText("Brand: " + mCar.getBrand());
        ((TextView) findViewById(R.id.carModel)).setText("Model: " + mCar.getModel());

        if (mCar.getOwner() != null) {
            ((TextView) findViewById(R.id.personId)).setText("ID: " + mCar.getOwner().getId());
            ((TextView) findViewById(R.id.personName)).setText("Name: " + mCar.getOwner().getName());
            ((TextView) findViewById(R.id.personSurname)).setText("Surname: " + mCar.getOwner().getSurname());
            ((TextView) findViewById(R.id.personBirthNumber)).setText("Birth number: " + mCar.getOwner().getBirthNumber());
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
