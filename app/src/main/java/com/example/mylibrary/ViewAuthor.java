package com.example.mylibrary;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewAuthor extends AppCompatActivity {

    private MyDatabaseHelper dbHandler;
    private ListView listViewAuthor;
    private ArrayList<String> authorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_author);

        dbHandler = new MyDatabaseHelper(ViewAuthor.this);
        listViewAuthor = findViewById(R.id.listViewAuthor);
        authorList = new ArrayList<>();

        displayAuthor();
    }

    private void displayAuthor() {
        Cursor cursor = dbHandler.getReadableDatabase().rawQuery("SELECT * FROM " + MyDatabaseHelper.TABLE_AUTHOR, null);
        if (cursor.moveToFirst()) {
            do {
                String authorName = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.AUTHOR_NAME_COL));
                authorList.add("Author Name: " +  authorName);
            } while (cursor.moveToNext());
        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, authorList);
        listViewAuthor.setAdapter(adapter);
    }
}
