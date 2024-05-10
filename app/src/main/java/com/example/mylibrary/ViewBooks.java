package com.example.mylibrary;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewBooks extends AppCompatActivity {

    private MyDatabaseHelper dbHandler;
    private ListView listViewBooks;
    private ArrayList<String> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_books);

        dbHandler = new MyDatabaseHelper(ViewBooks.this);
        listViewBooks = findViewById(R.id.listViewBooks);
        bookList = new ArrayList<>();

        displayBooks();
    }

    private void displayBooks() {
        Cursor cursor = dbHandler.getReadableDatabase().rawQuery("SELECT * FROM " + MyDatabaseHelper.TABLE_BOOKS, null);
        if (cursor.moveToFirst()) {
            do {
                String bookName = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.BOOK_NAME_COL));
                String publisher = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.BOOK_PUBLISHER_COL));
                bookList.add("Book Name: " + bookName + "\nPublisher: " + publisher);
            } while (cursor.moveToNext());
        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bookList);
        listViewBooks.setAdapter(adapter);
    }
}