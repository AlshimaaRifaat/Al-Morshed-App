package com.example.shosho.almorshed.fragment;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shosho.almorshed.NavigationActivity;
import com.example.shosho.almorshed.R;
import com.example.shosho.almorshed.SplashActivity;
import com.example.shosho.almorshed.View.Saredata;
import com.example.shosho.almorshed.adapter.PartSpinnerAdapter;
import com.example.shosho.almorshed.adapter.SearchAdapter;
import com.example.shosho.almorshed.adapter.SouretSpinnerAdapter;
import com.example.shosho.almorshed.database.DatabaseHelper;
import com.example.shosho.almorshed.model.Quran;
import com.facebook.CallbackManager;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.ShareOpenGraphObject;
import com.facebook.share.widget.ShareDialog;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements Saredata {
    Typeface customFontMedium,customFontRoman;
    Toolbar toolbar;
    TextView textViewHello;
    TextView textViewSearch;

    Cursor c = null;


    Spinner spinnerSourt,spinnerPart;
    ArrayList<String> spinnerValueSourt=new ArrayList<>(  );

    ArrayList<String> spinnerValuePart=new ArrayList<>(  );

    EditText editTextSearch;
    ImageView imageView;
    RecyclerView recyclerView;
    SearchAdapter searchAdapter;



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


       /* textViewSearch.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Quran> quranArrayList=SplashActivity.databaseHelper.getData( "رب" );
                Toast.makeText(getContext(), quranArrayList.get(0).getMeaning(), Toast.LENGTH_SHORT).show();
            }
        });*/


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
                    //ArrayList<Quran> quranArrayList=SplashActivity.databaseHelper.getData( spinnerSourt.getSelectedItem().toString() );
                    // databaseHelper.getData( "ٱلۡعَٰلَمِينَ" );
                    //Toast.makeText(getContext(), spinner.getSelectedItem().toString(), Toast.LENGTH_LONG).show();


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        /*ArrayList<Quran> autoCompleteList=SplashActivity.databaseHelper.
                getAutoCompleteSearchableResult( editTextSearch.getText().toString());
        Toast.makeText( getContext(), autoCompleteList.get( 0 ).getWord1(), Toast.LENGTH_SHORT ).show();*/

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
                    //  ArrayList<Quran> quranArrayList=SplashActivity.databaseHelper.getData( spinnerPart.getSelectedItem().toString() );
                    // databaseHelper.getData( "ٱلۡعَٰلَمِينَ" );
                    //Toast.makeText(getContext(), spinner.getSelectedItem().toString(), Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        imageView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ShareLinkContent content = new ShareLinkContent.Builder()
//                        .setContentUrl(Uri.parse("https://developers.facebook.com"))
//                        .setQuote("Connect on a global scale.")
//                        .build();
                /*ShareOpenGraphObject object = new ShareOpenGraphObject.Builder()
                        .putString("og:type", "fitness.course")
                        .putString("og:title", "Sample Course")
                        .putString("og:description", "This is a sample course.")
                        .build();
                ShareOpenGraphAction action = new ShareOpenGraphAction.Builder()
                        .setActionType("fitness.runs")
                        .putObject("fitness:course", object)
                        .build();
                ShareOpenGraphContent contens = new ShareOpenGraphContent.Builder()
                        .setPreviewPropertyName("fitness:course")
                        .setAction(action)
                        .build();
                ShareLinkContent linkContent = new ShareLinkContent.Builder()
                        .setContentUrl(Uri.parse("http://developers.facebook.com/android"))
                        .build();
                shareDialog.show(linkContent);*/

                if(editTextSearch.getText().toString().equals( "" ))
                    Toast.makeText( getContext(), "من فضلك ادخل كلمة البحث", Toast.LENGTH_SHORT ).show();
               else {
                   ArrayList<Quran> quranArrayList;
                    quranArrayList = SplashActivity.databaseHelper.getData( spinnerSourt.getSelectedItem().toString(), spinnerPart.getSelectedItem().toString()
                           , editTextSearch.getText().toString() );

                   if (quranArrayList.size() == 0) {
                      Toast.makeText( getContext(), "لا توجد نتائج لهذا البحث", Toast.LENGTH_SHORT ).show();
                    }

                    searchAdapter = new SearchAdapter( getContext(), quranArrayList );
                    searchAdapter.onclicks( HomeFragment.this );
                    recyclerView.setLayoutManager( new LinearLayoutManager( getContext() ) );
                    recyclerView.setAdapter( searchAdapter );
                    try {
                        InputMethodManager inputMethodManager=(InputMethodManager)getActivity().getSystemService( Context.INPUT_METHOD_SERVICE );
                        inputMethodManager.hideSoftInputFromWindow( getActivity().getCurrentFocus().getWindowToken(),0 );
                    }catch (Exception e)
                    {

                    }
                }




            }
        } );
        return view;
    }

    private void init() {

        toolbar=view.findViewById( R.id.home_toolbar );
        textViewHello=view.findViewById( R.id.home_text_view_hello );
        textViewSearch=view.findViewById( R.id.home_text_view_search );
        spinnerSourt =(Spinner)view.findViewById(R.id.home_spinner_sora);
        spinnerPart=view.findViewById( R.id.home_spinner_part );
        editTextSearch=view.findViewById( R.id.home_edit_text_search );
        imageView=view.findViewById( R.id.home_icon_search );
        recyclerView=view.findViewById( R.id.home_recyler );


    }


    @Override
    public void listquran(Quran list) {
//        ShareLinkContent content = new ShareLinkContent.Builder()
//                .setContentUrl(Uri.parse("https://developers.facebook.com"))
//                .build();
    }
}
