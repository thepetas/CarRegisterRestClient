package cz.thepetas.carregisterrestclient.activity.person;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import cz.thepetas.carregisterrestclient.R;
import cz.thepetas.carregisterrestclient.data.Person;

public class PersonAdapter extends ArrayAdapter<Person> {

    private final Context mContext;
    private final int mResourceId;

    static class ViewHolder {
        public TextView idTextView;
        public TextView nameTextView;
        public TextView surnameTextView;
        public TextView birthNumberTextView;
    }

    public PersonAdapter(Context context, int resource, Person[] persons) {
        super(context, resource, persons);
        mContext = context;
        mResourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        Person p = getItem(position);
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.list_item_person, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.idTextView = (TextView) convertView.findViewById(R.id.personId);
            viewHolder.nameTextView = (TextView) convertView.findViewById(R.id.personName);
            viewHolder.surnameTextView = (TextView) convertView.findViewById(R.id.personSurname);
            viewHolder.birthNumberTextView = (TextView) convertView.findViewById(R.id.personBirthNumber);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.idTextView.setText("ID: " + p.getId());
        viewHolder.nameTextView.setText("Name: " + p.getName());
        viewHolder.surnameTextView.setText("Surname: " + p.getSurname());
        viewHolder.surnameTextView.setText("Birth number: " + p.getBirthNumber());

        return convertView;
    }
}
