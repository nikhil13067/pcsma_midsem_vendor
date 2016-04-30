package com.example.nikhil.aircanteen_vendor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class UsersListActivity extends AppCompatActivity {
    ListView list;
    ArrayList<User> users;
    static User selectedUser;
    static boolean update_flag = false;
    CustomList adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        users = BaseNavigationDrawer.users;
        String[] names = new String[users.size()];
        int i=0;
        for(User user:users){
            names[i] = users.get(i).getName();
            i++;
        }


        adapter = new
                CustomList(this, names,users);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(), "You Clicked at ", Toast.LENGTH_SHORT).show();
                selectedUser = users.get(position);
                Intent intent = new Intent(getApplicationContext() , UserProfileActivity.class);
                startActivity(intent);
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
        if(update_flag == true){
            adapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext() , "Updating..." , Toast.LENGTH_LONG).show();
            update_flag = false;
        }
    }
}
