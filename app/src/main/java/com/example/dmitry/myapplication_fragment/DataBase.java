package com.example.dmitry.myapplication_fragment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Dmitry on 06.12.2015.
 */
public class DataBase extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "foodBasket";

    // Contacts table name
    private static final String TABLE_CARDS = "cards";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_PRODUCT_NAME = "name_product";
    private static final String KEY_PERSON_AGE = "age";
    private static final String KEY_PHOTO_URI = "photo_uri";
    private static final String KEY_PERSON_ID = "product_id";
    private static final String LOG_TAG_ERROR = "myLogs";


    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables

    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_CARDS + "("
                + KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_PERSON_ID + " UUID, "
                + KEY_PERSON_AGE + " TEXT, "
                + KEY_PRODUCT_NAME + " TEXT, "
                + KEY_PHOTO_URI + " Uri );";
        db.execSQL(CREATE_PRODUCTS_TABLE);
        Log.d(LOG_TAG_ERROR, "create table ok");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARDS);

        // Create tables again
        onCreate(db);
    }
    public void addPerson(Person person){
        // Adding new contact
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_PERSON_AGE, person.getAge_person());
        values.put(KEY_PRODUCT_NAME, person.getName_person());
        values.put(KEY_PERSON_ID, String.valueOf(person.getId()));
        values.put(KEY_PHOTO_URI, String.valueOf(person.getPhoto_person()));

        // Inserting Row
        db.insert(TABLE_CARDS, null, values);
        Log.d(LOG_TAG_ERROR, "addProduct OK " +" "+person.getName_person()+" "+person.getId()+" "+person.getPhoto_person() );

        db.close(); // Closing database connection
    }
    public List<Person> getAllPersons() {
        List<Person> personsList = new ArrayList<Person>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CARDS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Person person = new Person();
                person.setId(cursor.getString(1));
                person.setAge_person(cursor.getString(2));
                person.setName_person(cursor.getString(3));
                person.setPhoto_person(cursor.getString(4));



                // Adding contact to list
                personsList.add(person);
            } while (cursor.moveToNext());
        }
        Log.d(LOG_TAG_ERROR, "getPerson_OK " );
        // return contact list
        return personsList;
    }
    public void deleteProduct(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CARDS, KEY_PERSON_ID + " = ?",
                new String[]{String.valueOf(person.getId())});
        db.close();
        Log.d(LOG_TAG_ERROR, "delete person in DB"+person.getId());
    }
}
