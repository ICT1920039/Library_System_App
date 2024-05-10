package com.example.mylibrary;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Add_Copy extends AppCompatActivity {

    private EditText AccessEdt;
    private Button button;
    private MyDatabaseHelper dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_copy);

        AccessEdt = findViewById(R.id.editTextText13);
        button = findViewById(R.id.button13);

        dbHandler = new MyDatabaseHelper(Add_Copy.this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Access = AccessEdt.getText().toString();

                if (Access.isEmpty()) {
                    Toast.makeText(Add_Copy.this, "Please enter all the data.", Toast.LENGTH_SHORT).show();
                } else {
                    dbHandler.addNewCopy(Access);

                    Log.d("Add_Branch", "Copy added to database: " + Access);

                    Intent intent = new Intent(Add_Copy.this, ViewCopy.class);
                    startActivity(intent);

                    Log.d("Add_Copy", "Navigating to ViewCopy activity");
                }
            }
        });
    }
}
