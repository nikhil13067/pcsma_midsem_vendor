package com.example.nikhil.aircanteen_vendor;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OrderActivity extends AppCompatActivity {

    ListView listView ;
    ArrayList<Item> selectedItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        selectedItems = PendingOrdersCardActivity.selectedOrder.items;
        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if(getIntent().getExtras().getString("ActivityName").equals("CompleteOrdersActivity")){
            fab.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), getIntent().getExtras().getString("ActivityName") , Toast.LENGTH_LONG).show();
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get prompts.xml view
                AlertDialog.Builder builder = new AlertDialog.Builder(OrderActivity.this, R.style.MyAlertDialogStyle);
                builder.setTitle("Attention!");
                builder.setMessage("Mark the order as complete");
                builder.setPositiveButton("OK",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {
                        PendingOrdersCardActivity.selectedOrder.type = "COMPLETE";
                        PendingOrdersCardActivity.IncompleteOrders.remove(PendingOrdersCardActivity.selectedOrder);
                        PendingOrdersCardActivity.flag_data_changed = true;
                        finish();
                    }                });
                builder.setNegativeButton("Cancel", null);
                builder.show();

            }
        });

        // Defined Array values to show in ListView
        String[] values = new String[] {
                "Burger" , "Sandwich" , "Pizza" , "Sandwich" , "Pizza" , "Sandwich" , "Pizza"
        };

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView.setAdapter(new CustomAdapter(this, selectedItems));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String  itemValue = (String) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();

            }

        });

        setTotalPrice();
    }

    private void sendOrdertoServer() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //get current date time with Date()
        Date date = new Date();
        //Order order = new Order("dsds",selectedItems, date ,"Deliver");
        //MainActivity.orders.add(order);

    }

    private void setTotalPrice() {
        double total = 0.0;
        for(Item item:selectedItems){
            total += (item.getPrice() * item.getQuantity());
        }
        TextView total_price = (TextView)findViewById(R.id.cart_total_price);
        total_price.setText(Double.toString(total));
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
}
