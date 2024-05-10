package com.example.mylibrary;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewPublisher extends AppCompatActivity {

    private MyDatabaseHelper dbHandler;
    private ListView listViewPublisher;
    private ArrayList<String> publisherList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_publisher);

        dbHandler = new MyDatabaseHelper(ViewPublisher.this);
        listViewPublisher = findViewById(R.id.listViewPublisher);
        publisherList = new ArrayList<>();

        displayPublisher();
    }

    private void displayPublisher() {
        Cursor cursor = dbHandler.getReadableDatabase().rawQuery("SELECT * FROM " + MyDatabaseHelper.TABLE_PUBLISHERS, null);
        if (cursor.moveToFirst()) {
            do {
                String publisherName = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.PUBLISHER_NAME_COL));
                String publisherAddress = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.PUBLISHER_ADDRESS_COL));
                String publisherPhone = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.PUBLISHER_CONTACT_COL));
                publisherList.add("Publisher Name: " +  publisherName + "\nPublisher Address: " + publisherAddress + "\nPublisher Phone:" + publisherPhone);
            } while (cursor.moveToNext());
        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, publisherList);
        listViewPublisher.setAdapter(adapter);
    }
}
