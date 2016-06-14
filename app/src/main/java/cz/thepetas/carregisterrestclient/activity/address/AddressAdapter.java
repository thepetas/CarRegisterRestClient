package cz.thepetas.carregisterrestclient.activity.address;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import cz.thepetas.carregisterrestclient.R;
import cz.thepetas.carregisterrestclient.data.Address;
import cz.thepetas.carregisterrestclient.data.Person;

public class AddressAdapter extends ArrayAdapter<Address> {

    private final Context mContext;
    private final int mResourceId;

    static class ViewHolder {
        public TextView idTextView;
        public TextView streetTextView;
        public TextView houseNumberTextView;
        public TextView cityTextView;
        public TextView zipCodeTextView;
    }

    public AddressAdapter(Context context, int resource, Address[] addresses) {
        super(context, resource, addresses);
        mContext = context;
        mResourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        Address a = getItem(position);
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.list_item_address, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.idTextView = (TextView) convertView.findViewById(R.id.addressId);
            viewHolder.streetTextView = (TextView) convertView.findViewById(R.id.addressStreet);
            viewHolder.houseNumberTextView = (TextView) convertView.findViewById(R.id.addressHouseNumber);
            viewHolder.cityTextView = (TextView) convertView.findViewById(R.id.addressCity);
            viewHolder.zipCodeTextView = (TextView) convertView.findViewById(R.id.addressZipCode);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.idTextView.setText("ID: " + a.getId());
        viewHolder.streetTextView.setText("Street: " + a.getStreet());
        viewHolder.houseNumberTextView.setText("No. house: " + a.getHouseNumber());
        viewHolder.cityTextView.setText("City: " + a.getCity());
        viewHolder.zipCodeTextView.setText("ZIP: " + a.getZipCode());

        return convertView;
    }
}
