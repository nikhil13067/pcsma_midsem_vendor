package com.example.nikhil.aircanteen_vendor;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class DataAdapterItems extends RecyclerView.Adapter<DataAdapterItems.ViewHolder> {
    private ArrayList<Item> items;

    public DataAdapterItems(ArrayList<Item> items) {
        this.items = items;
    }

    @Override
    public DataAdapterItems.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DataAdapterItems.ViewHolder viewHolder, int i) {
        final Item selectedItem = items.get(i);
        String foodtype = items.get(i).getFoodType();
        viewHolder.item_name.setText(items.get(i).getName());
        viewHolder.item_price.setText(Double.toString(items.get(i).getPrice()));
        viewHolder.item_time.setText(Integer.toString(items.get(i).getTime()) + " minutes");
        /*viewHolder.button_increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence currentquantity = viewHolder.item_quantity.getText();
                int qty = Integer.parseInt(currentquantity.toString());
                if(qty>=0){
                    qty++;
                    selectedItem.setQuantity(qty);
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
                    selectedItem.setQuantity(qty);
                    currentquantity = Integer.toString(qty);
                    viewHolder.item_quantity.setText(currentquantity);
                }

            }
        });*/
        viewHolder.toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(buttonView.isChecked()){
                        selectedItem.setAvailable(true);
                    }
                    else{
                        selectedItem.setAvailable(false);
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
        private ToggleButton toggleButton;
        public ViewHolder(View view) {
            super(view);
            item_name = (TextView)view.findViewById(R.id.card_item_name);
            item_price = (TextView)view.findViewById(R.id.item_price);
            item_time = (TextView)view.findViewById(R.id.item_time);
            //item_quantity = (TextView)view.findViewById(R.id.quantity_text_view);
            /*button_decrement = (Button)view.findViewById(R.id.button_decrement);
            button_increment = (Button)view.findViewById(R.id.button_increment);*/
            toggleButton = (ToggleButton) view.findViewById(R.id.toggleButton);
            item_image = (ImageView)view.findViewById(R.id.card_tem_image);
            veg_image = (ImageView)view.findViewById(R.id.item_veg);
        }
    }

}