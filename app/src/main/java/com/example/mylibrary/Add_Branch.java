package com.example.mylibrary;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Add_Branch extends AppCompatActivity {

    private EditText NameEdt, AddressEdt;
    private Button button;
    private MyDatabaseHelper dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_branch);

        NameEdt = findViewById(R.id.editTextText4);
        AddressEdt = findViewById(R.id.editTextText7);
        button = findViewById(R.id.button11);

        dbHandler = new MyDatabaseHelper(Add_Branch.this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = NameEdt.getText().toString();
                String Address = AddressEdt.getText().toString();

                if (Name.isEmpty() || Address.isEmpty() ) {
                    Toast.makeText(Add_Branch.this, "Please enter all the data.", Toast.LENGTH_SHORT).show();
                } else {
                    dbHandler.addNewBranch(Name, Address);

                    Log.d("Add_Branch", "Branch added to database: " + Name + ", " + Address);

                    Intent intent = new Intent(Add_Branch.this, ViewBranch.class);
                    startActivity(intent);

                    Log.d("Add_Branch", "Navigating to ViewBranch activity");
                }
            }
        });
    }
}
