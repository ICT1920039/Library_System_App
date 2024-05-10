package com.example.mylibrary;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewBranch extends AppCompatActivity {

    private MyDatabaseHelper dbHandler;
    private ListView listViewBranch;
    private ArrayList<String> branchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_branch);

        dbHandler = new MyDatabaseHelper(ViewBranch.this);
        listViewBranch = findViewById(R.id.listViewBranch);
        branchList = new ArrayList<>();

        displayBranch();
    }

    private void displayBranch() {
        Cursor cursor = dbHandler.getReadableDatabase().rawQuery("SELECT * FROM " + MyDatabaseHelper.TABLE_BRANCH, null);
        if (cursor.moveToFirst()) {
            do {
                String branchName = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.PUBLISHER_NAME_COL));
                String branchAddress = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.PUBLISHER_ADDRESS_COL));
                branchList.add("Branch Name: " +  branchName + "\nBranch Address: " + branchAddress);
            } while (cursor.moveToNext());
        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, branchList);
        listViewBranch.setAdapter(adapter);
    }
}
