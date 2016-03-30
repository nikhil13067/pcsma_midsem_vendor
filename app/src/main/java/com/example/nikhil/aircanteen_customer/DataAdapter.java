package com.example.nikhil.aircanteen_customer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<Item> items;

    public DataAdapter(ArrayList<Item> items) {
        this.items = items;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DataAdapter.ViewHolder viewHolder, int i) {

        String foodtype = items.get(i).getFoodType();
        viewHolder.item_name.setText(items.get(i).getName());
        viewHolder.item_price.setText(Double.toString(items.get(i).getPrice()));
        viewHolder.item_time.setText(Integer.toString(items.get(i).getTime()) + " minutes");
        viewHolder.button_increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence currentquantity = viewHolder.item_quantity.getText();
                int qty = Integer.parseInt(currentquantity.toString());
                if(qty>=0){
                    qty++;
                    currentquantity = Integer.toString(qty);
                    viewHolder.item_quantity.setText(currentquantity);
                }

            }
        });
        viewHolder.button_decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence currentquantity = viewHolder.item_quantity.getText();
                int qty = Integer.parseInt(currentquantity.toString());
                if(qty>0){
                    qty--;
                    currentquantity = Integer.toString(qty);
                    viewHolder.item_quantity.setText(currentquantity);
                }

            }
        });



        if(foodtype=="NON-VEG"){
            viewHolder.veg_image.setImageResource(R.drawable.nonveg);
        }
        else{
            viewHolder.veg_image.setImageResource(R.drawable.veg);
        }

        viewHolder.item_image.setImageResource(items.get(i).getImage());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView item_name, item_price, item_time,item_quantity;
        private Button button_decrement,button_increment;
        private ImageView item_image,veg_image;
        public ViewHolder(View view) {
            super(view);
            item_name = (TextView)view.findViewById(R.id.card_item_name);
            item_price = (TextView)view.findViewById(R.id.item_price);
            item_time = (TextView)view.findViewById(R.id.item_time);
            item_quantity = (TextView)view.findViewById(R.id.quantity_text_view);
            button_decrement = (Button)view.findViewById(R.id.button_decrement);
            button_increment = (Button)view.findViewById(R.id.button_increment);
            item_image = (ImageView)view.findViewById(R.id.card_tem_image);
            veg_image = (ImageView)view.findViewById(R.id.item_veg);
        }
    }

}