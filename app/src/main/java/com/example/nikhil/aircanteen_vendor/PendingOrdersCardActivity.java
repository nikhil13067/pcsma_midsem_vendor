package com.example.nikhil.aircanteen_vendor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class PendingOrdersCardActivity extends AppCompatActivity {

    ArrayList<Order> orders;
    static ArrayList<Order> IncompleteOrders;
    static Order selectedOrder;
    static boolean flag_data_changed = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        orders = new ArrayList<>();
        orders = BaseNavigationDrawer.orders;
        IncompleteOrders = new ArrayList<>();
        for(Order order: orders){
            if (order.type == "INCOMPLETE"){
                IncompleteOrders.add(order);
            }
        }

        int count;
        try {
            count = IncompleteOrders.size();
        }catch (NullPointerException e){
            count=0;
        }
        if(count==0){
            Toast.makeText(getApplicationContext(),"No orders pending", Toast.LENGTH_LONG).show();
        }
        else{

            initViews(IncompleteOrders);

        }

    }

    private void getOrderfromServer(){

    }
    private void initViews(final ArrayList<Order> orders){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter adapter = new DataAdapterOrder(orders);
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
                    Toast.makeText(getApplicationContext(), orders.get(position).toString(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PendingOrdersCardActivity.this , OrderActivity.class);
                    selectedOrder = orders.get(position);
                    intent.putExtra("ActivityName","PendingOrdersCardActivity");
                    startActivity(intent);
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(flag_data_changed){
            flag_data_changed = false;
            RecyclerView recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
            recyclerView.getAdapter().notifyDataSetChanged();
        }
    }

}
