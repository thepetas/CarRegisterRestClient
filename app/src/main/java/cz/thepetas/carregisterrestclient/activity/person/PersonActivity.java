package cz.thepetas.carregisterrestclient.activity.person;

import android.app.FragmentManager;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import cz.thepetas.carregisterrestclient.R;
import cz.thepetas.carregisterrestclient.activity.TaskFragment;
import cz.thepetas.carregisterrestclient.activity.car.CarAdapter;
import cz.thepetas.carregisterrestclient.activity.car.CarTabFragment;
import cz.thepetas.carregisterrestclient.data.Car;
import cz.thepetas.carregisterrestclient.data.Person;

public class PersonActivity extends AppCompatActivity {


    private Person mPerson;
    private CarListFragment mCarListFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        mPerson = (Person) bundle.getSerializable(PersonTabFragment.PERSON_OBJECT);


        ((TextView) findViewById(R.id.personId)).setText("ID: " + mPerson.getId());
        ((TextView) findViewById(R.id.personName)).setText("Name: " + mPerson.getName());
        ((TextView) findViewById(R.id.personSurname)).setText("Surname: " + mPerson.getSurname());
        ((TextView) findViewById(R.id.personBirthNumber)).setText("Birth number: " + mPerson.getBirthNumber());
        ((TextView) findViewById(R.id.personCntCars)).setText("Count of cars: " + mPerson.getCntCars());
        ((TextView) findViewById(R.id.addressText)).setText(mPerson.getAddress().getStreet() + " " +
                mPerson.getAddress().getHouseNumber() + ", " + mPerson.getAddress().getCity() +
                " - " + mPerson.getAddress().getZipCode());


        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mCarListFragment = new CarListFragment();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, mCarListFragment)
                    .commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        Car cars[] = new Car[mPerson.getVehicles().size()];
        mPerson.getVehicles().toArray(cars);
        mCarListFragment.updateData(cars);
    }
}
