package com.example.alex.dictionary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DataBaseHelper {
    private static DataBaseHelper helper;
    private DBHelper dbHelper;
    private SQLiteDatabase database;
    private ContentValues contentValues;

    private DataBaseHelper(Context context) {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        contentValues = new ContentValues();
    }

    static DataBaseHelper getInstance(Context context) {
        if (helper == null) {
            helper = new DataBaseHelper(context);
        }
        return helper;
    }

    void add(Type p) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.KEY_WORD, p.word);
        contentValues.put(DBHelper.KEY_TRANSLATE, p.translate);
        database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
    }

     ArrayList<Type> allElement() {
        ArrayList<Type> dictionary = new ArrayList<>();
        Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, "Translate");

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int wordIndex = cursor.getColumnIndex(DBHelper.KEY_WORD);
            int translateIndex = cursor.getColumnIndex(DBHelper.KEY_TRANSLATE);
            do {
                Type p = new Type(cursor.getString(wordIndex), cursor.getString(translateIndex), cursor.getInt(idIndex));
                dictionary.add(p);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return dictionary;
    }

    void remove(int id) {
        int delCount = database.delete(DBHelper.TABLE_CONTACTS, DBHelper.KEY_ID + "= " + id, null);
    }

    public void change(Type type, String id) {
        contentValues.put(DBHelper.KEY_WORD, type.word);
        contentValues.put(DBHelper.KEY_TRANSLATE, type.translate);
        database.update(DBHelper.TABLE_CONTACTS, contentValues, DBHelper.KEY_ID + "= ?", new String[]{id});
    }

}
