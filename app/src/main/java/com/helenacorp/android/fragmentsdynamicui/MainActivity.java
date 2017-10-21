package com.helenacorp.android.fragmentsdynamicui;

import android.content.res.Configuration;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import com.helenacorp.android.fragmentsdynamicui.CountryListFragment.OnCountrySelectedListener;

public class MainActivity extends AppCompatActivity implements OnCountrySelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
        if (findViewById(R.id.frameLayoutDynamicUi) != null) {
            final CountryListFragment listFragment = new CountryListFragment();
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.frameLayoutDynamicUi, listFragment)
                    .commit();
        }

        }
    }

    public void onCountrySelected(int position) {
        if (findViewById(R.id.frameLayoutDynamicUi) == null) {
            // If we are in landscape mode, we show the flag in the second fragment.
            final CountryDetailsFragment detailsFragment = (CountryDetailsFragment) getFragmentManager().findFragmentById(R.id.fragmentDetails);
            detailsFragment.updateCountry(position);
        } else {
            // Else, we show the other fragment in portrait mode.
            final CountryDetailsFragment detailsFragment = CountryDetailsFragment.newInstance(position);
            final android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayoutDynamicUi,detailsFragment );
            ft.addToBackStack(null);
            ft.commit();
        }
    }

}
