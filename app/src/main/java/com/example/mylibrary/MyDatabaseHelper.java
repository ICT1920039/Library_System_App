package com.example.mylibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "librarydb";
    private static final int DB_VERSION = 9;

    public static final String TABLE_BOOKS = "books";
    public static final String BOOK_ID_COL = "id";
    public static final String BOOK_NAME_COL = "name";
    public static final String BOOK_PUBLISHER_COL = "publisher";

    public static final String TABLE_PUBLISHERS = "publishers";
    public static final String PUBLISHER_NAME_COL = "name";
    public static final String PUBLISHER_ADDRESS_COL = "address";
    public static final String PUBLISHER_CONTACT_COL = "contact";

    public static final String TABLE_BRANCH = "branch";
    public static final String BRANCH_ID_COL = "id";
    public static final String BRANCH_NAME_COL = "name";
    public static final String BRANCH_ADDRESS_COL = "address";

    public static final String TABLE_MEMBER = "member";
    public static final String CARD_ID_COL = "id";
    public static final String MEMBER_NAME_COL = "name";
    public static final String MEMBER_ADDRESS_COL = "address";
    public static final String MEMBER_CONTACT_COL = "contact";
    public static final String UNPAID_DUES_COL = "unpaid";

    public static final String TABLE_AUTHOR = "author";
    public static final String AUTHOR_BOOK_ID_COL = "id";
    public static final String AUTHOR_NAME_COL = "name";

    public static final String TABLE_COPY = "copy";
    public static final String COPY_BOOK_ID_COL = "id";
    public static final String COPY_BRANCH_ID_COL = "id";
    public static final String ACCESS_NO_COL = "accessNo";


    public MyDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the books table
        String createBooksTableQuery = "CREATE TABLE " + TABLE_BOOKS + " ("
                + BOOK_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BOOK_NAME_COL + " TEXT,"
                + BOOK_PUBLISHER_COL + " TEXT)";
        db.execSQL(createBooksTableQuery);

        String createPublishersTableQuery = "CREATE TABLE " + TABLE_PUBLISHERS + " ("
                + PUBLISHER_NAME_COL + " TEXT PRIMARY KEY, "
                + PUBLISHER_ADDRESS_COL + " TEXT,"
                + PUBLISHER_CONTACT_COL + " TEXT)";
        db.execSQL(createPublishersTableQuery);

        String createBranchTableQuery = "CREATE TABLE " + TABLE_BRANCH + " ("
                + BRANCH_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BRANCH_NAME_COL + " TEXT,"
                + BRANCH_ADDRESS_COL + " TEXT)";
        db.execSQL(createBranchTableQuery);

        String createMemberTableQuery = "CREATE TABLE " + TABLE_MEMBER + " ("
                + CARD_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MEMBER_NAME_COL + " TEXT,"
                + MEMBER_ADDRESS_COL + " TEXT"
                + MEMBER_CONTACT_COL + "TEXT"
                + UNPAID_DUES_COL + "TEXT)";
        db.execSQL(createMemberTableQuery);

        String createAuthorTableQuery = "CREATE TABLE " + TABLE_AUTHOR + " ("
                + AUTHOR_BOOK_ID_COL + "INTEGER,"
                + AUTHOR_NAME_COL + "TEXT PRIMARY KEY)";
        db.execSQL(createAuthorTableQuery);

        String createCopyTableQuery = "CREATE TABLE " + TABLE_COPY + " ("
                + COPY_BOOK_ID_COL + " INTEGER,"
                + COPY_BRANCH_ID_COL + "INTEGER,"
                + ACCESS_NO_COL + "TEXT)";
        db.execSQL(createCopyTableQuery);
    }

    public void addNewBooks(String bookName, String bookPublisher) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BOOK_NAME_COL, bookName);
        values.put(BOOK_PUBLISHER_COL, bookPublisher);
        db.insert(TABLE_BOOKS, null, values);
        db.close();
    }
    public void addNewPublisher(String publisherName, String publisherAddress, String publisherContact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PUBLISHER_NAME_COL, publisherName);
        values.put(PUBLISHER_ADDRESS_COL, publisherAddress);
        values.put(PUBLISHER_CONTACT_COL, publisherContact);
        db.insert(TABLE_PUBLISHERS, null, values);
        db.close();
    }
    public void addNewBranch(String branchName, String branchAddress) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BRANCH_NAME_COL, branchName);
        values.put(BRANCH_ADDRESS_COL, branchAddress);
        db.insert(TABLE_BRANCH, null, values);
        db.close();
    }
    public void addNewMember(String memberName, String memberAddress, String memberContact,String Unpaid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MEMBER_NAME_COL, memberName);
        values.put(MEMBER_ADDRESS_COL, memberAddress);
        values.put(MEMBER_CONTACT_COL, memberContact);
        values.put(UNPAID_DUES_COL, Unpaid);
        db.insert(TABLE_MEMBER, null, values);
        db.close();
    }
    public void addNewAuthors(String authorName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AUTHOR_NAME_COL, authorName);
        db.insert(TABLE_AUTHOR, null, values);
        db.close();
    }
    public void addNewCopy(String accessNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ACCESS_NO_COL, accessNo);
        db.insert(TABLE_COPY, null, values);
        db.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PUBLISHERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BRANCH);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMBER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AUTHOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COPY);
        onCreate(db);
    }
}
