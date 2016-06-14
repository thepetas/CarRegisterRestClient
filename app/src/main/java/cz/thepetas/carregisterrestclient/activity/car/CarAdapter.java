package cz.thepetas.carregisterrestclient.activity.car;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import cz.thepetas.carregisterrestclient.R;
import cz.thepetas.carregisterrestclient.data.Car;

public class CarAdapter extends ArrayAdapter<Car> {

    private final Context mContext;
    private final int mResourceId;

    static class ViewHolder {
        public TextView idTextView;
        public TextView idMarkTextView;
        public TextView brandTextView;
        public TextView modelTextView;
    }

    public CarAdapter(Context context, int resource, Car[] cars) {
        super(context, resource, cars);
        mContext = context;
        mResourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        Car c = getItem(position);
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.list_item_car, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.idTextView = (TextView) convertView.findViewById(R.id.carId);
            viewHolder.idMarkTextView = (TextView) convertView.findViewById(R.id.carIdMark);
            viewHolder.brandTextView = (TextView) convertView.findViewById(R.id.carBrand);
            viewHolder.modelTextView = (TextView) convertView.findViewById(R.id.carModel);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.idTextView.setText("ID: " + c.getId());
        viewHolder.idMarkTextView.setText("ID mark: " + c.getIdMark());
        viewHolder.brandTextView.setText("Brand: " + c.getBrand());
        viewHolder.modelTextView.setText("Model: " + c.getModel());

        return convertView;
    }
}
