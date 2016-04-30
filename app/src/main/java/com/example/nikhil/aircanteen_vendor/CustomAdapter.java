package com.example.nikhil.aircanteen_vendor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<Item> data;
    private static LayoutInflater inflater = null;

    public CustomAdapter(Context context, ArrayList<Item> data) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.row, null);
        TextView item_name = (TextView) vi.findViewById(R.id.cart_item_name);
        item_name.setText(data.get(position).getName());

        String qty = Integer.toString(data.get(position).getQuantity());
        TextView item_qty = (TextView) vi.findViewById(R.id.cart_item_qty);
        item_qty.setText(qty);

        double total_price = 0.0;
        double temp = data.get(position).getQuantity() * data.get(position).getPrice();
        total_price += temp;
        TextView item_price = (TextView) vi.findViewById(R.id.cart_item_price);
        String price = Double.toString(temp);
        item_price.setText(price);


        TextView total_text = (TextView)vi.findViewById(R.id.cart_total_price);
        String total = Double.toString(total_price);
        //total_text.setText(total);
        return vi;
    }
}