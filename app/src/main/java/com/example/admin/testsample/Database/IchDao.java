package com.example.admin.testsample.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.admin.testsample.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 11/24/2016.
 */


/**
 * Created by admin on 11/14/2016.
 */
public class IchDao {
    DatabaseHelper dbHelper;
    SQLiteDatabase database;

    public IchDao(Context context) {
        super();
        dbHelper = new DatabaseHelper(context);

    }

    public void openDB() {

        database = dbHelper.getWritableDatabase();

    }

    public void closeDB() {

        database.close();
    }

    public String getTotal() {
        String type = "0.00";
        openDB();
        Cursor cursor = database.rawQuery("SELECT SUM(" + (ColumnID.TOTAL) + ") FROM " + ColumnID.MENU_TABLE, null);
        if (cursor != null && cursor.moveToFirst()) {

            type = cursor.getString(cursor.getColumnIndex(ColumnID.TOTAL));
        }
        closeDB();
        return type;

    }
    public void insertOptionCart(List<Category> optionValueArrayList) {
        for (int i=0;i<optionValueArrayList.size();i++) {
            ContentValues values = new ContentValues();
            values.put(ColumnID.MENU_ID, optionValueArrayList.get(i).getCategoryId());
            values.put(ColumnID.COUNT, optionValueArrayList.get(i).getDescription());

            openDB();
            database.beginTransaction();
            database.insert(ColumnID.MENU_OPTION_TABLE_CHECKBOX, null, values);
            database.setTransactionSuccessful();
            database.endTransaction();
            closeDB();
        }
    }

    public void insetCart(String menuid, String menuname, String pic, String count,
                          String price, String singleselection, String singleprice, String total,String type) {
        ContentValues values = new ContentValues();
        values.put(ColumnID.MENU_NAME, menuname);
        values.put(ColumnID.MENU_ID, menuid);
        values.put(ColumnID.MENU_PIC, pic);
        values.put(ColumnID.COUNT, count);
        values.put(ColumnID.PRICE, price);

        values.put(ColumnID.SINGLE_SELECTION, singleselection);
        values.put(ColumnID.SINGLE_SELECTION_PRICE, type);
        values.put(ColumnID.TOTAL, total);
        openDB();
        database.beginTransaction();
        database.insert(ColumnID.MENU_TABLE, null, values);
        database.setTransactionSuccessful();
        database.endTransaction();
        closeDB();
    }

    public void updateCart(String menuid, String count, String total) {
        ContentValues values = new ContentValues();
        values.put(ColumnID.COUNT, count);
        values.put(ColumnID.TOTAL, total);


        openDB();
        database.beginTransaction();

        database.update(ColumnID.MENU_TABLE, values, "menu_id=" + menuid, null);
        database.setTransactionSuccessful();
        database.endTransaction();
        closeDB();
    }

    public void deleteOptionCart(String menuid) {



        openDB();
        database.beginTransaction();

        database.delete(ColumnID.MENU_OPTION_TABLE_CHECKBOX,  "menu_id=" + menuid, null);
        database.setTransactionSuccessful();
        database.endTransaction();
        closeDB();
    }



    public void deleteMenu(String menuid) {
        openDB();
        database.beginTransaction();

        database.delete(ColumnID.MENU_TABLE,  "menu_id=" + menuid, null);
        database.setTransactionSuccessful();
        database.endTransaction();
        closeDB();
    }

    public void deleteAllMenu() {
        openDB();
        database.beginTransaction();

        database.execSQL("delete from "+ ColumnID.MENU_TABLE);
        database.setTransactionSuccessful();
        database.endTransaction();
        closeDB();
    }

    public void deleteAllOption() {
        openDB();
        database.beginTransaction();

        database.execSQL("delete from "+ ColumnID.MENU_OPTION_TABLE_CHECKBOX);
        database.setTransactionSuccessful();
        database.endTransaction();
        closeDB();
    }



    public ArrayList<Category> getCartItem(String id) {
        ArrayList<Category> countyList = new ArrayList<Category>();
        openDB();
        String query = "Select *  from " + ColumnID.MENU_TABLE + " where " + ColumnID.MENU_ID + " = " + id;
        Cursor cursor = database.rawQuery(query, null);


        while (cursor.moveToNext()) {
            Category county = new Category();

            county.setName(cursor.getString(cursor
                    .getColumnIndex(ColumnID.MENU_ID)));



            countyList.add(county);

        }
        return countyList;

    }




}
