package com.example.myapplication.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.Model.asset_infor;

import java.util.ArrayList;
import java.util.List;



public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "assetDB";
    private static final String TABLE_NAME = "assetinfor";

    private static final String KEY_ID = "id";
    private static final String KEY_IDASSET = "idasset";
    private static final String KEY_NAME = "name";
    private static final String KEY_DATE ="date";
    private static final String KEY_VALUET ="valuet";
    private static final String KEY_VALUEH ="valueh";
    private static final String KEY_VALUEW ="valuew";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        /*context.deleteDatabase(DATABASE_NAME);*/
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT NOT NULL," + KEY_DATE + " TEXT NOT NULL," +KEY_IDASSET+" TEXT NOT NULL,"+KEY_VALUET+" FLOAT NOT NULL,"+KEY_VALUEH+" FLOAT NOT NULL,"+KEY_VALUEW+" FLOAT NOT NULL"+ ")";




        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addInfor(asset_infor infor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues inititalValues = new ContentValues();
        inititalValues.put(KEY_NAME, infor.getName());
        inititalValues.put(KEY_IDASSET,infor.getIdasset());
        inititalValues.put(KEY_NAME,infor.getName());
        inititalValues.put(KEY_DATE,infor.getDate());
        inititalValues.put(KEY_VALUET,infor.getValueT());
        inititalValues.put(KEY_VALUEH,infor.getValueH());
        inititalValues.put(KEY_VALUEW,infor.getValueW());
        db.insert(TABLE_NAME, null, inititalValues);
        db.close();
    }

    public List<asset_infor> getAllContacts() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<asset_infor> arraylist = new ArrayList<>();

        Cursor cursor = db.query(TABLE_NAME, new String[]{KEY_ID,KEY_IDASSET, KEY_NAME,KEY_DATE,KEY_VALUET,KEY_VALUEH,KEY_VALUEW },
                null, null, null, null, null);

        while (cursor.moveToNext()) {
            int indexId = cursor.getColumnIndex(KEY_ID);
            int indexIda = cursor.getColumnIndex(KEY_IDASSET);
            int indexName = cursor.getColumnIndex(KEY_NAME);
            int indexDate = cursor.getColumnIndex(KEY_DATE);
            int indexValuet = cursor.getColumnIndex(KEY_VALUET);
            int indexValueh = cursor.getColumnIndex(KEY_VALUEH);
            int indexValuew = cursor.getColumnIndex(KEY_VALUEW);


            asset_infor infor = new asset_infor(cursor.getString(indexIda), cursor.getString(indexName), cursor.getString(indexDate),cursor.getFloat(indexValuet),cursor.getFloat(indexValueh),cursor.getFloat(indexValuew));
            arraylist.add(infor);
        }

        db.close();
        return arraylist;
    }

    /*public void updateStudent(int id, String name, String mssv) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("UPDATE " + TABLE_NAME + " SET " + KEY_NAME + "='" + name + "'" +
                " WHERE " + KEY_ID + "='" + id + "'");
        db.execSQL("UPDATE " + TABLE_NAME + " SET " + KEY_MSSV + "='" + mssv + "'" +
                " WHERE " + KEY_ID + "='" + id + "'");

        db.close();
    }

    public void deleteStudent(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "id="+id;
        db.delete(TABLE_NAME, selection, null);
        reSortStudent(id);
        db.close();
    }*/

    private void reSortStudent(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "id>"+id;
        Cursor cursor = db.query(TABLE_NAME, new String[]{KEY_ID},
                selection, null, null, null, null);

        id--;
        while (cursor.moveToNext()) {
            int indexId = cursor.getColumnIndex(KEY_ID);
            id++;
            db.execSQL("UPDATE " + TABLE_NAME + " SET " + KEY_ID + "='" + id + "'" +
                    " WHERE " + KEY_ID + "='" + cursor.getInt(indexId) + "'");
        }
        db.close();
    }
}
