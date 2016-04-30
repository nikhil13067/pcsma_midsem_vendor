package com.example.nikhil.aircanteen_vendor;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends BaseNavigationDrawer {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Item> sampleItems;
    private TextView item_name, item_price, item_time,item_quantity;
    private Button button_decrement,button_increment;
    private ImageView item_image,veg_image;
    static ArrayList<Item> selectedItems;

    static String ROOT_URL = "  ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        selectedItems = new ArrayList<>();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPostToServer();
            }
        });
        sampleItems = new ArrayList<>();
        sampleItems = createSampleData();
        initViews(sampleItems);

        item_name = (TextView)findViewById(R.id.card_item_name);
        item_price = (TextView)findViewById(R.id.item_price);
        item_time = (TextView)findViewById(R.id.item_time);
        /*item_quantity = (TextView)findViewById(R.id.quantity_text_view);
        button_decrement = (Button)findViewById(R.id.button_decrement);
        button_increment = (Button)findViewById(R.id.button_increment);*/
        item_image = (ImageView)findViewById(R.id.card_tem_image);
        veg_image = (ImageView)findViewById(R.id.item_veg);


    }

    private void sendPostToServer() {

    }
    private void getItemsFromServer(){

    }


    private ArrayList<Item> createSampleData(){
        sampleItems = new ArrayList<>();
        Item burger = new Item(1,"Burger",35,2,true,"VEG",R.drawable.burger);
        Item sandwich = new Item(2,"Sandwich",35,2,true,"VEG",R.drawable.sandwich_veg);
        Item pizza = new Item(3,"Pizza",50,20,true,"NON-VEG",R.drawable.pizza);
        Item thali1 = new Item(4,"Thali",50,10,true,"VEG",R.drawable.thali_veg);
        Item thali2 = new Item(5,"Thali",70,10,true,"NON-VEG",R.drawable.thali_non_veg);
        sampleItems.add(burger);
        sampleItems.add(sandwich);
        sampleItems.add(pizza);
        sampleItems.add(thali1);
        sampleItems.add(thali2);
        return sampleItems;
    }
    private void initViews(final ArrayList<Item> sampleItems){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter adapter = new DataAdapterItems(sampleItems);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

                @Override public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if(child != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(child);
                    Toast.makeText(getApplicationContext(), sampleItems.get(position).toString(), Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }



}
