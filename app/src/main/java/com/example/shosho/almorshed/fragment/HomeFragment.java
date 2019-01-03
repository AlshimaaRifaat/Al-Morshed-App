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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shosho.almorshed.NavigationActivity;
import com.example.shosho.almorshed.R;
import com.example.shosho.almorshed.SplashActivity;
import com.example.shosho.almorshed.adapter.PartSpinnerAdapter;
import com.example.shosho.almorshed.adapter.SouretSpinnerAdapter;
import com.example.shosho.almorshed.database.DatabaseHelper;
import com.example.shosho.almorshed.model.Quran;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    Typeface customFontMedium,customFontRoman;
    Toolbar toolbar;
    TextView textViewHello;
    TextView textViewSearch;

    Cursor c = null;


    Spinner spinnerSourt,spinnerPart;
   ArrayList<String> spinnerValueSourt=new ArrayList<>(  );

   ArrayList<String> spinnerValuePart=new ArrayList<>(  );

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
                ArrayList<Quran> quranArrayList=SplashActivity.databaseHelper.getData( "رب" );
                Toast.makeText(getContext(), quranArrayList.get(0).getWord1(), Toast.LENGTH_SHORT).show();
            }
        });


        SouretSpinnerAdapter souretSpinnerAdapter = new SouretSpinnerAdapter(getContext(), android.R.layout.simple_list_item_1);
        spinnerValueSourt= SplashActivity.databaseHelper.getSourtList();
       souretSpinnerAdapter.addAll(spinnerValueSourt);
        souretSpinnerAdapter.add("بحث بالسورة");
        spinnerSourt.setAdapter(souretSpinnerAdapter);
        spinnerSourt.setSelection(souretSpinnerAdapter.getCount());

        spinnerSourt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub

                if(spinnerSourt.getSelectedItem() == "بحث بالسورة")
                {

                    //Do nothing.
                }
                else{
                   // databaseHelper.getData( "ٱلۡعَٰلَمِينَ" );
                    //Toast.makeText(getContext(), spinner.getSelectedItem().toString(), Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });



        PartSpinnerAdapter partSpinnerAdapter = new PartSpinnerAdapter(getContext(), android.R.layout.simple_list_item_1);
        spinnerValuePart= SplashActivity.databaseHelper.getPartList();
        partSpinnerAdapter.addAll(spinnerValuePart);
        partSpinnerAdapter.add("بحث بالجزء");
        spinnerPart.setAdapter(partSpinnerAdapter);
        spinnerPart.setSelection(partSpinnerAdapter.getCount());

        spinnerPart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub

                if(spinnerPart.getSelectedItem() == "بحث بالجزء")
                {

                    //Do nothing.
                }
                else{
                    // databaseHelper.getData( "ٱلۡعَٰلَمِينَ" );
                    //Toast.makeText(getContext(), spinner.getSelectedItem().toString(), Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        return view;
    }

    private void init() {

        toolbar=view.findViewById( R.id.home_toolbar );
        textViewHello=view.findViewById( R.id.home_text_view_hello );
        textViewSearch=view.findViewById( R.id.home_text_view_search );
        spinnerSourt =(Spinner)view.findViewById(R.id.home_spinner_sora);
        spinnerPart=view.findViewById( R.id.home_spinner_part );


    }

}
