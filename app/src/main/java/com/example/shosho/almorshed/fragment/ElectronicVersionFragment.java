package com.example.shosho.almorshed.fragment;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shosho.almorshed.NavigationActivity;
import com.example.shosho.almorshed.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ElectronicVersionFragment extends Fragment {

Toolbar toolbar;
Typeface customFontMedium;
TextView textViewDownload,textViewPlzShare;
ImageView iconWhats,iconInsta,iconFacebook;
    public ElectronicVersionFragment() {
        // Required empty public constructor
    }

View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate( R.layout.fragment_electronic_version, container, false );

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
        textViewDownload.setTypeface( customFontMedium );

        textViewPlzShare.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iconWhats.setVisibility( View.VISIBLE );
                iconInsta.setVisibility( View.VISIBLE );
                iconFacebook.setVisibility( View.VISIBLE );

            }
        } );
        return view;
    }

    private void init() {

        toolbar=view.findViewById( R.id.elec_version_toolbar );
        textViewDownload=view.findViewById( R.id.elec_version_text_view_download );

        textViewPlzShare=view.findViewById( R.id.elec_version_btn_plz_share );
        iconWhats=view.findViewById( R.id.elec_version_icon_whats);
        iconInsta=view.findViewById( R.id.elec_version_icon_insta );
        iconFacebook=view.findViewById( R.id.elec_version_icon_face );
    }

}
