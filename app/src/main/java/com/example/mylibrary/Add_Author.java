package com.example.mylibrary;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Add_Author extends AppCompatActivity {

    private EditText NameEdt;
    private Button button;
    private MyDatabaseHelper dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_author);

        NameEdt = findViewById(R.id.editTextText12);
        button = findViewById(R.id.button8);

        dbHandler = new MyDatabaseHelper(Add_Author.this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = NameEdt.getText().toString();

                if (Name.isEmpty()) {
                    Toast.makeText(Add_Author.this, "Please enter all the data.", Toast.LENGTH_SHORT).show();
                } else {
                    dbHandler.addNewAuthors(Name);

                    Log.d("Add_Author", "Author added to database: " + Name);

                    Intent intent = new Intent(Add_Author.this, ViewAuthor.class);
                    startActivity(intent);

                    Log.d("Add_Author", "Navigating to ViewAuthor activity");
                }
            }
        });
    }
}
