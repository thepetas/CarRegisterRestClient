package cz.thepetas.carregisterrestclient.activity;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class TaskFragment extends Fragment {

    private TaskCallbacks mCallbacks;
    private GetJSONAsyncTask mTask;
    private boolean mIsRunning = false;

    public boolean isRunning() {
        return mIsRunning;
    }

    public interface TaskCallbacks {
        void onPreExecute();

        void onPostExecute(String values[]);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mCallbacks = (TaskCallbacks) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void executeTask(String url) {
        mTask = new GetJSONAsyncTask();
        mTask.execute(url);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    public class GetJSONAsyncTask extends AsyncTask<String, Void, String[]> {


        @Override
        protected void onPreExecute() {
            if (mCallbacks != null) {
                mCallbacks.onPreExecute();
                mIsRunning = true;
            }
        }

        @Override
        protected void onPostExecute(String values[]) {
            if (mCallbacks != null) {
                mCallbacks.onPostExecute(values);
                mIsRunning = false;
            }
        }

        @Override
        protected String[] doInBackground(String... params) {
            SystemClock.sleep(1 * 1000);
            String tmp = "";
            try {

                URL url = new URL(params[0]);
                URLConnection connection = url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;
                while ((line = in.readLine()) != null) {
                    tmp += line;
                }
            } catch (Exception e) {
                tmp = "";
            }
            String res[] = new String[2];
            res[0] = params[0];
            res[1] = tmp;
            Log.i("TASK_F", "IN: '" + params[0] + "'");
            Log.i("TASK_F", "OUT: '" + tmp + "'");
            return res;
        }
    }
}
