package com.example.shosho.almorshed.fragment;


import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shosho.almorshed.NavigationActivity;
import com.example.shosho.almorshed.R;
import com.example.shosho.almorshed.database.DatabaseHelper;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    Typeface customFontMedium,customFontRoman;
    Toolbar toolbar;
    TextView textViewHello;
    TextView textViewSearch;

    Cursor c = null;
    public HomeFragment() {
        // Required empty public constructor
    }



    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate( R.layout.fragment_home, container, false );
        init();
        NavigationActivity.toggle = new ActionBarDrawerToggle(
                getActivity(), NavigationActivity.drawer, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        NavigationActivity.drawer.addDrawerListener(NavigationActivity.toggle);
        NavigationActivity.toggle.syncState();

        NavigationActivity.toggle.setDrawerIndicatorEnabled(false);
        toolbar.setNavigationIcon(R.drawable.nav  );

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (NavigationActivity.drawer.isDrawerOpen(GravityCompat.START)) {
                    NavigationActivity.drawer.closeDrawer(GravityCompat.START);
                } else {
                    NavigationActivity.drawer.openDrawer(GravityCompat.START);
                }
            }
        });
        customFontMedium=Typeface.createFromAsset( getContext().getAssets(),"Fonts/SST Arabic Medium.ttf" );
        textViewHello.setTypeface( customFontMedium );

        customFontRoman=Typeface.createFromAsset( getContext().getAssets(),"Fonts/SST Arabic Roman.ttf" );
        textViewSearch.setTypeface( customFontRoman );


        textViewSearch.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper myDbHelper = new DatabaseHelper(getContext());
                try {
                    myDbHelper.createDataBase();
                } catch (IOException ioe) {
                    throw new Error("Unable to create database");
                }
                try {
                    myDbHelper.openDataBase();
                } catch (SQLException sqle) {
                    throw sqle;
                }
                Toast.makeText(getContext(), "Successfully Imported", Toast.LENGTH_SHORT).show();
                c = myDbHelper.query("part15", null, null, null, null, null, null);
                if (c.moveToFirst()) {
                    do {
                        Toast.makeText(getContext(),
                                "_id: " + c.getString(0) + "\n" +
                                        "DATE: " + c.getString(1) + "\n" +
                                        "TIME: " + c.getString(2) + "\n" +
                                        "HEIGHT:  " + c.getString(3),
                                Toast.LENGTH_LONG).show();
                    } while (c.moveToNext());
                }
            }
        });
        return view;
    }

    private void init() {

        toolbar=view.findViewById( R.id.home_toolbar );
        textViewHello=view.findViewById( R.id.home_text_view_hello );
        textViewSearch=view.findViewById( R.id.home_text_view_search );

    }

}
