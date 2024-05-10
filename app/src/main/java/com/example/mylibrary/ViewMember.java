package com.example.mylibrary;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewMember extends AppCompatActivity {

    private MyDatabaseHelper dbHandler;
    private ListView listViewMember;
    private ArrayList<String> memberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_member);

        dbHandler = new MyDatabaseHelper(ViewMember.this);
        listViewMember = findViewById(R.id.listViewMember);
        memberList = new ArrayList<>();

        displayMember();
    }

    private void displayMember() {
        Cursor cursor = dbHandler.getReadableDatabase().rawQuery("SELECT * FROM " + MyDatabaseHelper.TABLE_MEMBER, null);
        if (cursor.moveToFirst()) {
            do {
                String memberName = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.MEMBER_NAME_COL));
                String memberAddress = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.MEMBER_ADDRESS_COL));
                String memberPhone = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.MEMBER_CONTACT_COL));
                String unpaidDues = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.UNPAID_DUES_COL));
                memberList.add("Member Name: " + memberName + "\nMember Address: " + memberAddress +"\nMember Phone:"+memberPhone+"\nUnpaid Dues:"+unpaidDues);
            } while (cursor.moveToNext());
        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, memberList);
        listViewMember.setAdapter(adapter);
    }
}
