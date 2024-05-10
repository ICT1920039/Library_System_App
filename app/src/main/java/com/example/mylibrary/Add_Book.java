package com.example.mylibrary;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Add_Book extends AppCompatActivity {

    private EditText NameEdt, PublisherEdt;
    private Button button;
    private MyDatabaseHelper dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        NameEdt = findViewById(R.id.editTextText);
        PublisherEdt = findViewById(R.id.editTextText2);
        button = findViewById(R.id.button9);

        dbHandler = new MyDatabaseHelper(Add_Book.this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = NameEdt.getText().toString();
                String Publisher = PublisherEdt.getText().toString();

                if (Name.isEmpty() || Publisher.isEmpty()) {
                    Toast.makeText(Add_Book.this, "Please enter all the data.", Toast.LENGTH_SHORT).show();
                } else {
                    // Add book details to the database
                    dbHandler.addNewBooks(Name, Publisher);

                    // Debugging: Log message to verify database operation
                    Log.d("Add_Book", "Book added to database: " + Name + ", " + Publisher);

                    // Navigate to ViewBooks activity
                    Intent intent = new Intent(Add_Book.this, ViewBooks.class);
                    startActivity(intent);

                    // Debugging: Log message to verify intent navigation
                    Log.d("Add_Book", "Navigating to ViewBooks activity");
                }
            }
        });

    }
}
