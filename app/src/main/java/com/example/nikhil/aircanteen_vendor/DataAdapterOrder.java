package com.example.nikhil.aircanteen_vendor;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class DataAdapterOrder extends RecyclerView.Adapter<DataAdapterOrder.ViewHolder> {
    private ArrayList<Order> orders;

    public DataAdapterOrder(ArrayList<Order> orders) {
        this.orders = orders;
    }

    @Override
    public DataAdapterOrder.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row_order, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DataAdapterOrder.ViewHolder viewHolder, int i) {
        final Order order = orders.get(i);
        /*String foodtype = items.get(i).getFoodType();
        viewHolder.item_name.setText(items.get(i).getName());

*/      double price=0;
        for(Item item: order.items){
            price += (item.getPrice()*item.getQuantity());
        }
        viewHolder.order_id.setText(order.orderID);
        viewHolder.order_price.setText(Double.toString(price));
        viewHolder.order_date.setText(order.orderTime.toString());
        viewHolder.toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()){
                    order.type="COMPLETE";
                }
                else {
                    order.type = "INCOMPLETE";
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView order_id;
        private TextView order_price;
        private TextView order_date;
        private ToggleButton toggleButton;

        public ViewHolder(View view){
            super(view);
            order_id = (TextView)view.findViewById(R.id.card_order_number);
            order_price = (TextView)view.findViewById(R.id.card_order_price);
            order_date = (TextView)view.findViewById(R.id.card_order_date);
            toggleButton = (ToggleButton) view.findViewById(R.id.toggleButton_order);

        }
    }

}