package com.example.mylibrary;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Add_Publisher extends AppCompatActivity {

    private EditText NameEdt,AddressEdt,PhoneEdt;
    private Button button;
    private MyDatabaseHelper dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_publisher);


        EditText NameEdt = findViewById(R.id.editTextText3);
        EditText AddressEdt = findViewById(R.id.editTextText5);
        EditText PhoneEdt = findViewById(R.id.editTextText6);
        button = findViewById(R.id.button10);

        dbHandler = new MyDatabaseHelper(Add_Publisher.this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = NameEdt.getText().toString();
                String Address = AddressEdt.getText().toString();
                String Phone = PhoneEdt.getText().toString();

                if (Name.isEmpty() || Address.isEmpty() || Phone.isEmpty() ) {
                    Toast.makeText(Add_Publisher.this, "Please enter all the data.", Toast.LENGTH_SHORT).show();
                } else {
                    dbHandler.addNewPublisher(Name, Address,Phone);

                    Log.d("Add_Publisher", "Publisher added to database: " + Name + ", " + Address +"," +Phone);

                    Intent intent = new Intent(Add_Publisher.this, ViewPublisher.class);
                    startActivity(intent);

                    // Debugging: Log message to verify intent navigation
                    Log.d("Add_Publisher", "Navigating to ViewPublisher activity");
                }
            }
        });
    }
}
