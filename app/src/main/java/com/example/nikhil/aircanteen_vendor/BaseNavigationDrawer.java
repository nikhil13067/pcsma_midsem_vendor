package com.example.nikhil.aircanteen_vendor;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class BaseNavigationDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    static ArrayList<Order> orders;
    static ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Item burger = new Item(1,"Burger",35,2,true,"VEG",R.drawable.burger);
        ArrayList<Item> arrayList = new ArrayList<>();
        arrayList.add(burger);
        orders = new ArrayList<>();
        orders.add(new Order("abcd",arrayList,"start","end","INCOMPLETE"));

        users= new ArrayList<>();
        createSampleUsers();

    }

    private void createSampleUsers(){
        users.add(new User("Nikhil", "nikhil13067@iiitd.ac.in",300));
        users.add(new User("Sameer", "sameer13086@iiitd.ac.in",100));
        users.add(new User("Kartik","kartik13040@iiitd.ac.in",200));

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.base_navigation_drawer, menu);
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

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.new_order) {
            Intent intent = new Intent(this ,MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_past_orders) {
            Intent intent = new Intent(this, PendingOrdersCardActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_complete) {
            Intent intent = new Intent(this, CompleteOrdersActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_users) {
            Intent intent = new Intent(this,UsersListActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_add_item) {
            Intent intent = new Intent(this, AddItemActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
