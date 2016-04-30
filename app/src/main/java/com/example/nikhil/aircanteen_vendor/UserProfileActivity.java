package com.example.nikhil.aircanteen_vendor;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserProfileActivity extends AppCompatActivity {
    User currentUser;
    TextView user_name,user_email,user_balance;
    Button update_balance;
    private String m_Text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        currentUser = UsersListActivity.selectedUser;
        user_name = (TextView)findViewById(R.id.user_name);
        user_email = (TextView)findViewById(R.id.user_email);
        user_balance = (TextView)findViewById(R.id.user_balance);
        update_balance = (Button)findViewById(R.id.button_update_balance);
        Integer integer = new Integer(currentUser.getBalance());
        user_name.setText(currentUser.getName());
        user_email.setText(currentUser.getEmail());
        user_balance.setText("Balance: "+integer.toString());

        update_balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

    }

    private void openDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter new Balance");

// Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_NUMBER );
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
                currentUser.setBalance(Integer.parseInt(m_Text));
                user_balance.setText("Balance: " + m_Text);
                UsersListActivity.update_flag = true;
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
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
