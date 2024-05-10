package com.example.mylibrary;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Add_Member extends AppCompatActivity {

    private EditText NameEdt, AddressEdt, PhoneEdt, UnpaidEdt;
    private Button button;
    private MyDatabaseHelper dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);


        NameEdt = findViewById(R.id.editTextText8);
        AddressEdt = findViewById(R.id.editTextText9);
        PhoneEdt = findViewById(R.id.editTextText10);
        UnpaidEdt = findViewById(R.id.editTextText11);
        button = findViewById(R.id.button12);

        dbHandler = new MyDatabaseHelper(Add_Member.this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = NameEdt.getText().toString();
                String Address = AddressEdt.getText().toString();
                String Phone = PhoneEdt.getText().toString();
                String Unpaid = UnpaidEdt.getText().toString();

                if (Name.isEmpty() || Address.isEmpty() || Phone.isEmpty() || Unpaid.isEmpty()) {
                    Toast.makeText(Add_Member.this, "Please enter all the data.", Toast.LENGTH_SHORT).show();
                } else {
                    dbHandler.addNewMember(Name, Address, Phone, Unpaid);

                    Log.d("Add_Member", "Member added to database: " + Name + ", " + Address + "," + Phone + "," + Unpaid);

                    Intent intent = new Intent(Add_Member.this, ViewMember.class);
                    startActivity(intent);

                    // Debugging: Log message to verify intent navigation
                    Log.d("Add_Member", "Navigating to ViewMember activity");
                }
            }
        });

    }
}