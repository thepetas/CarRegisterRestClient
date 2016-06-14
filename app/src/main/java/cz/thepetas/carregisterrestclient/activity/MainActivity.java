package cz.thepetas.carregisterrestclient.activity;

import android.app.FragmentManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import cz.thepetas.carregisterrestclient.R;
import cz.thepetas.carregisterrestclient.data.Constants;

public class MainActivity extends AppCompatActivity implements TaskFragment.TaskCallbacks {

    private static final String TASK_FRAGMENT = "TaskFragment";
    private static final String MY_URL = "http://10.0.0.102:8080/rest/persons";

    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private MenuItem mRefreshMenuItem;
    private TaskFragment mTaskFragment;
    private View mProgressActionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mViewPagerAdapter);
        setSupportActionBar(mToolbar);

        final TabLayout.Tab home = mTabLayout.newTab();
        final TabLayout.Tab inbox = mTabLayout.newTab();
        final TabLayout.Tab star = mTabLayout.newTab();

        home.setText("Cars");
        inbox.setText("Persons");
        star.setText("Addresses");

        mTabLayout.addTab(home, 0);
        mTabLayout.addTab(inbox, 1);
        mTabLayout.addTab(star, 2);

        mTabLayout.setTabTextColors(ContextCompat.getColorStateList(this, R.drawable.tab_selector));
        mTabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.indicator));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));


        FragmentManager fm = getFragmentManager();
        mTaskFragment = (TaskFragment) fm.findFragmentByTag(TASK_FRAGMENT);

        if (mTaskFragment == null) {
            mTaskFragment = new TaskFragment();
            fm.beginTransaction().add(mTaskFragment, TASK_FRAGMENT).commit();
        }

        mProgressActionView = getLayoutInflater().inflate(R.layout.action_view_progress, null);

        updateAll();

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mTaskFragment.executeTask(Constants.CARS);
                        break;
                    case 1:
                        mTaskFragment.executeTask(Constants.PERSONS);
                        break;
                    case 2:
                        mTaskFragment.executeTask(Constants.ADDRESSES);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.i("PAGER", "STATE = " + state);
            }
        });

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        if (mRefreshMenuItem == null)
            mRefreshMenuItem = menu.findItem(R.id.updateItem);

        if (mTaskFragment.isRunning()) {
            setStartRefreshing();
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.updateItem:
                updateAll();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPreExecute() {
        setStartRefreshing();
    }

    @Override
    public void onPostExecute(String values[]) {
        if ((values[0].equals(Constants.CARS)) && mViewPagerAdapter.getCarsTab() != null) {
            mViewPagerAdapter.getCarsTab().updateData(values[1]);
        }
        if ((values[0].equals(Constants.PERSONS)) && mViewPagerAdapter.getPersonsTab() != null) {
            mViewPagerAdapter.getPersonsTab().updateData(values[1]);
        }
        if ((values[0].equals(Constants.ADDRESSES)) && mViewPagerAdapter.getAddressesTab() != null) {
            mViewPagerAdapter.getAddressesTab().updateData(values[1]);
        }
        setStopRefreshing();
    }


    public void setStartRefreshing() {
        mRefreshMenuItem.setActionView(mProgressActionView);
    }

    public void setStopRefreshing() {
        if (mRefreshMenuItem.getActionView() != null) {
            mRefreshMenuItem.collapseActionView();
            mRefreshMenuItem.setActionView(null);
        }
    }

    private void updateAll() {
        if (isNetworkAvailable()) {
            mTaskFragment.executeTask(Constants.CARS);
            mTaskFragment.executeTask(Constants.PERSONS);
            mTaskFragment.executeTask(Constants.ADDRESSES);
        } else {
            Toast.makeText(this, R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }

}
