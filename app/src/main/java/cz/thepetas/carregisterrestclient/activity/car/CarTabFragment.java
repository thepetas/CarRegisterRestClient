package cz.thepetas.carregisterrestclient.activity.car;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cz.thepetas.carregisterrestclient.R;
import cz.thepetas.carregisterrestclient.activity.TaskFragment;
import cz.thepetas.carregisterrestclient.activity.person.PersonAdapter;
import cz.thepetas.carregisterrestclient.data.Car;
import cz.thepetas.carregisterrestclient.data.MyJsonParser;
import cz.thepetas.carregisterrestclient.data.Person;

public class CarTabFragment extends ListFragment {

    private static final String TASK_FRAGMENT = "TaskFragment";

    private CarAdapter mCarAdapter;
    private TaskFragment mTaskFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tabs, container, false);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mCarAdapter = new CarAdapter(getActivity(), R.layout.list_item_car, new Car[0]);
        setListAdapter(mCarAdapter);


        FragmentManager fm = getActivity().getFragmentManager();
        mTaskFragment = (TaskFragment) fm.findFragmentByTag(TASK_FRAGMENT);

        if (mTaskFragment == null) {
            mTaskFragment = new TaskFragment();
            fm.beginTransaction().add(mTaskFragment, TASK_FRAGMENT).commit();
        }

    }

    @Override
    public void onStart() {
        super.onStart();
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
