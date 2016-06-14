package cz.thepetas.carregisterrestclient.activity.person;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cz.thepetas.carregisterrestclient.R;
import cz.thepetas.carregisterrestclient.activity.TaskFragment;
import cz.thepetas.carregisterrestclient.data.MyJsonParser;
import cz.thepetas.carregisterrestclient.data.Person;

public class PersonTabFragment extends ListFragment {

    private static final String TASK_FRAGMENT = "TaskFragment";

    private PersonAdapter mPersonAdapter;
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
        mPersonAdapter = new PersonAdapter(getActivity(), R.layout.list_item_person, new Person[0]);
        setListAdapter(mPersonAdapter);


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
        Person persons[] = MyJsonParser.getPersonsFromJSON(newJson);
        if (persons != null) {
            mPersonAdapter = null;
            mPersonAdapter = new PersonAdapter(getActivity(), R.layout.list_item_person, persons);
            setListAdapter(mPersonAdapter);
            mPersonAdapter.notifyDataSetChanged();
        }
    }

}
