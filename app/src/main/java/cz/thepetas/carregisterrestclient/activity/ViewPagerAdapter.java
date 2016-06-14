package cz.thepetas.carregisterrestclient.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cz.thepetas.carregisterrestclient.activity.address.AddressTabFragment;
import cz.thepetas.carregisterrestclient.activity.car.CarTabFragment;
import cz.thepetas.carregisterrestclient.activity.person.PersonTabFragment;
import cz.thepetas.carregisterrestclient.data.Address;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    private CarTabFragment mTab1;
    private PersonTabFragment mTab2;
    public AddressTabFragment mTab3;

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            mTab1 = new CarTabFragment();
            return mTab1;
        }
        if (position == 1) {
            mTab2 = new PersonTabFragment();
            return mTab2;
        }
        if (position == 2) {
            mTab3 = new AddressTabFragment();
            return mTab3;
        } else
            return new PersonTabFragment();
    }

    public CarTabFragment getCarsTab() {
        return mTab1;
    }

    public PersonTabFragment getPersonsTab() {
        return mTab2;
    }

    public AddressTabFragment getAddressesTab() {
        return mTab3;
    }




    @Override
    public int getCount() {
        return 3;
    }

}
