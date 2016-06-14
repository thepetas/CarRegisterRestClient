package cz.thepetas.carregisterrestclient.activity.person;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import cz.thepetas.carregisterrestclient.R;
import cz.thepetas.carregisterrestclient.activity.car.CarActivity;
import cz.thepetas.carregisterrestclient.activity.car.CarAdapter;
import cz.thepetas.carregisterrestclient.data.Car;
import cz.thepetas.carregisterrestclient.data.MyJsonParser;

public class CarListFragment extends ListFragment {


    private CarAdapter mCarAdapter;

    public static final String CAR_OBJECT = "PERSON_OBJECT";

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mCarAdapter = new CarAdapter(getActivity(), R.layout.list_item_car, new Car[0]);
        setListAdapter(mCarAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(CAR_OBJECT, mCarAdapter.getItem(position));
        Intent intent = new Intent(getActivity(), CarActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void updateData(String newJson) {
        Car cars[] = MyJsonParser.getCarsFromJSON(newJson);
        updateData(cars);
    }

    public void updateData(Car cars[]) {
        if (cars != null) {
            mCarAdapter = null;
            mCarAdapter = new CarAdapter(getActivity(), R.layout.list_item_person, cars);
            setListAdapter(mCarAdapter);
            mCarAdapter.notifyDataSetChanged();
        }
    }
}
