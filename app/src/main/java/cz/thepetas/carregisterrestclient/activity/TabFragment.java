package cz.thepetas.carregisterrestclient.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import cz.thepetas.carregisterrestclient.R;

public class TabFragment extends Fragment {


    private TextView mTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tabs, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTextView = (TextView) view.findViewById(R.id.textView);
    }

    @Override
    public void onStart() {
        super.onStart();


        new RunAndGetJSON().execute();
    }

    private class RunAndGetJSON extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            String tmp = "";
            try {
                URL link = new URL("http://10.0.0.102:8080/rest/vehicles");
                URLConnection connection = link.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;
                while ((line = in.readLine()) != null) {
                    JSONArray ja = new JSONArray(line);

                    for (int i = 0; i < ja.length(); i++) {
                        JSONObject jo = (JSONObject) ja.get(i);
                        tmp += jo.toString();

                    }
                }
            } catch (Exception e) {
                tmp = e.getMessage();
            }
            return tmp;
        }


        @Override
        protected void onPostExecute(String s) {
            mTextView.setText(s);
        }
    }
}
