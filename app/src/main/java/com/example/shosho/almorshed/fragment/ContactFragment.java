package com.example.shosho.almorshed.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shosho.almorshed.NavigationActivity;
import com.example.shosho.almorshed.NetworkConnection;
import com.example.shosho.almorshed.R;
import com.example.shosho.almorshed.View.ContactView;
import com.example.shosho.almorshed.model.User;
import com.example.shosho.almorshed.presenter.ContactPresenter;
import com.fourhcode.forhutils.FUtilsValidation;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment implements ContactView {

Toolbar toolbar;
ImageView iconWhats;

    ContactPresenter contactPresenter;
    EditText userName;
    EditText userEmail;
    EditText userSubject;
    EditText userMsg;
    Button sendBtn;
    public ContactFragment() {
        // Required empty public constructor
    }

View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate( R.layout.fragment_contact, container, false );
        init();

        contactPresenter = new ContactPresenter( getContext(), this );
        sendBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContactHere();
            }
        } );

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

        iconWhats.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setPackage("com.whatsapp");
                sendIntent.putExtra(Intent.EXTRA_TEXT, "");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        } );
        return view;
    }

    private void ContactHere() {
        FUtilsValidation.isEmpty( userName, getResources().getString( R.string.Pleasewriteyourname ) );
        FUtilsValidation.isEmpty( userEmail,getResources().getString( R.string.Pleasewriteyouremail ));
        FUtilsValidation.isEmpty( userSubject,getResources().getString( R.string.Pleasewriteyoursubject ));
        FUtilsValidation.isEmpty( userMsg,getResources().getString( R.string.Pleasewriteyourmsg ));

        NetworkConnection networkConnection = new NetworkConnection( getContext() );
        if (networkConnection.isNetworkAvailable( getContext() )) {
            if (!userName.getText().toString().equals( "" )
                            && !userEmail.getText().toString().equals( "" )&&
                            !userSubject.getText().toString().equals("")&&
                            !userMsg.getText().toString().equals("")&&validateEmail()) {
                User user = new User();

                user.setName( userName.getText().toString() );
                user.setEmail( userEmail.getText().toString() );
                user.setSubject( userSubject.getText().toString() );
                user.setMsg( userMsg.getText().toString() );

                contactPresenter.getContactResult( user );
            } else {
                Toast.makeText( getContext(), R.string.Pleasefullyourinformation, Toast.LENGTH_SHORT ).show();
            }

        } else {
            Toast.makeText( getContext(), R.string.Checkyourinternetconnection
                    , Toast.LENGTH_SHORT ).show();
        }
    }
    public static boolean isValidEmail(String Email)
    {
        return !TextUtils.isEmpty( Email )&&  android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches();
    }
    private Boolean validateEmail(){
        String EMAIL=userEmail.getText().toString().trim();
        if (EMAIL.isEmpty()||!isValidEmail(EMAIL)){
            userEmail.setError(getResources().getString(R.string.Invalidemail));

            return false;
        }else if(!userEmail.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
            userEmail.setError(getResources().getString(R.string.Invalidemail));
            return false;
        }
        return true;
    }

    private void init() {
        toolbar=view.findViewById( R.id.call_us_toolbar );
        iconWhats=view.findViewById( R.id.call_us_icon_whats );

        userName=view.findViewById( R.id.call_us_edit_text_name );
        userEmail=view.findViewById( R.id.call_us_edit_text_email );
        userSubject=view.findViewById( R.id.call_us_edit_text_subject );
        userMsg=view.findViewById( R.id.call_us_edit_text_msg );

        sendBtn=view.findViewById( R.id.call_us_btn_send );
    }

    @Override
    public void showContactResult(String contact_name, String Email, String Subject, String Msg) {
        Toast.makeText( getContext(),"تم ارسال البيانات", Toast.LENGTH_LONG ).show();
    }

    @Override
    public void showError(String Error) {
        Toast.makeText( getContext(), Error, Toast.LENGTH_SHORT ).show();
    }
}
