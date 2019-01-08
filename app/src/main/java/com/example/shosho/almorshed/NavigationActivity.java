package com.example.shosho.almorshed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.shosho.almorshed.fragment.ContactFragment;
import com.example.shosho.almorshed.fragment.ElectronicVersionFragment;
import com.example.shosho.almorshed.fragment.HomeFragment;
import com.example.shosho.almorshed.fragment.WayOfSearchFragment;
import com.example.shosho.almorshed.fragment.WhoAreWeFragment;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
Fragment fragment;
private int currentSelectedPosition=0;
NavigationView navigationView;
DrawerLayout drawerLayout;
public static Toolbar toolbar;
public static ActionBarDrawerToggle toggle;
public  static DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_navigation );
      //  toolbar = (Toolbar) findViewById( R.id.toolbar );

        navigationView=findViewById( R.id.nav_view );
        onNavigationItemSelected(navigationView.getMenu().getItem( 0 )  );


      //  setSupportActionBar( toolbar );



      drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
         toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.addDrawerListener( toggle );
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener( this );
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        if (drawer.isDrawerOpen( GravityCompat.START )) {
            drawer.closeDrawer( GravityCompat.START );
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.navigation, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected( item );
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()){
            case  R.id.nav_home:
                currentSelectedPosition=0;
                fragment=new HomeFragment();
                break;

            case  R.id.nav_way_of_search:
                currentSelectedPosition=1;
                fragment=new WayOfSearchFragment();
                break;

            case  R.id.nav_who_are_we:
                currentSelectedPosition=2;
                fragment=new WhoAreWeFragment();
                break;
            case  R.id.nav_call_us:
                currentSelectedPosition=3;
                fragment=new ContactFragment();
                break;

            case  R.id.nav_electronic_version:
                currentSelectedPosition=4;
                fragment=new ElectronicVersionFragment();
                break;
                default:
                    currentSelectedPosition=0;
        }

if (item.isChecked())
{
    item.setChecked( false );
}else
{
    item.setChecked( true );
}
        FragmentManager fragmentManager=  getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace( R.id.content_navigation,fragment ).addToBackStack( null )
        .commit();
        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        drawer.closeDrawer( GravityCompat.START );
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content_navigation);
        fragment.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult( requestCode, resultCode, data );
    }
}
