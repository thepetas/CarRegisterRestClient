package cz.thepetas.carregisterrestclient.activity.person;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import cz.thepetas.carregisterrestclient.R;
import cz.thepetas.carregisterrestclient.data.MyJsonParser;
import cz.thepetas.carregisterrestclient.data.Person;

public class PersonTabFragment extends ListFragment {

    public static final String PERSON_OBJECT = "PERSON_OBJECT";

    private PersonAdapter mPersonAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tabs, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPersonAdapter = new PersonAdapter(getActivity(), R.layout.list_item_person, new Person[0]);
        setListAdapter(mPersonAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(PERSON_OBJECT, mPersonAdapter.getItem(position));
        Intent intent = new Intent(getActivity(), PersonActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
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
