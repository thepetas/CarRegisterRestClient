package cz.thepetas.carregisterrestclient.activity.car;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import cz.thepetas.carregisterrestclient.R;
import cz.thepetas.carregisterrestclient.data.Car;
import cz.thepetas.carregisterrestclient.data.MyJsonParser;
import cz.thepetas.carregisterrestclient.data.Person;

public class CarTabFragment extends ListFragment {

    private CarAdapter mCarAdapter;

    public static final String CAR_OBJECT = "PERSON_OBJECT";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tabs, container, false);

    }

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
        if (cars != null) {
            mCarAdapter = null;
            mCarAdapter = new CarAdapter(getActivity(), R.layout.list_item_person, cars);
            setListAdapter(mCarAdapter);
            mCarAdapter.notifyDataSetChanged();
        }
    }
}
