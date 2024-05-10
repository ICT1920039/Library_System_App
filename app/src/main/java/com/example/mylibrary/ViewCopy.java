package com.example.mylibrary;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewCopy extends AppCompatActivity {

    private MyDatabaseHelper dbHandler;
    private ListView listViewCopy;
    private ArrayList<String> copyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_copy);

        dbHandler = new MyDatabaseHelper(ViewCopy.this);
        listViewCopy = findViewById(R.id.listViewCopy);
        copyList = new ArrayList<>();

        displayCopy();
    }

    private void displayCopy() {
        Cursor cursor = dbHandler.getReadableDatabase().rawQuery("SELECT * FROM " + MyDatabaseHelper.TABLE_COPY, null);
        if (cursor.moveToFirst()) {
            do {
                String access = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.ACCESS_NO_COL));
                copyList.add("Access No: " +  access);
            } while (cursor.moveToNext());
        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, copyList);
        listViewCopy.setAdapter(adapter);
    }
}
