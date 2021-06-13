package com.example.advancecardealerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import java.sql.Connection;
import java.sql.DriverManager;

public class CarsDataBaseHelper extends SQLiteOpenHelper{

    public CarsDataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE CAR(ID INTEGER PRIMARY KEY UNIQUE,MAKE TEXT, MODEL TEXT,DISTANCE TEXT,YEAR TEXT,PRICE INTEGER,ACCIDENTS TEXT,OFFERS TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE RESERVE(EMAIL TEXT, CARID INTEGER, DATE TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE FAVORITE(EMAIL TEXT, CARID INTEGER)");

        sqLiteDatabase.execSQL("CREATE TABLE USER(" +
                "EMAIL TEXT PRIMARY KEY," +
                "FIRSTNAME TEXT,LASTNAME TEXT, " +
                "PASSWORD TEXT," +
                "GENDER TEXT," +
                "PHONE TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE ADMIN(" +
                "ADMINEMAIL TEXT PRIMARY KEY," +
                "ADMINFIRSTNAME TEXT,ADMINLASTNAME TEXT, " +
                "ADMINPASSWORD TEXT," +
                "ADMINGENDER TEXT," +
                "ADMINPHONE TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor getAllCars() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM Car", null);
    }


    public void deleteAllCars(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        sqLiteDatabase.delete("Car",null,null);
    }



    public void insertCar(int ID ,String MODEL,String MAKE,String DISTANCE,int YEAR,int PRICE, boolean ACCIDENTS, boolean OFFERS) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", ID);
        contentValues.put("MODEL", MODEL);
        contentValues.put("MAKE",MAKE);
        contentValues.put("DISTANCE", DISTANCE);
        contentValues.put("YEAR", YEAR);
        contentValues.put("PRICE", PRICE);
        contentValues.put("ACCIDENTS", ACCIDENTS);
        contentValues.put("OFFERS", OFFERS);
        sqLiteDatabase.insert("CAR", null, contentValues);


    }

    public void insertFavorite(String email, int carid){
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL",email);
        contentValues.put("CARID", carid);
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert("FAVORITE", null, contentValues);
    }

    public void insertReserve(String email, int carid,String date){
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL",email);
        contentValues.put("CARID", carid);
        contentValues.put("DATE",date);
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert("RESERVE", null, contentValues);
    }

    public Cursor getFavByEmail(String email){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("SELECT * FROM FAVORITE WHERE EMAIL = '"+email+"'",null);
        return cursor;
    }

    public Cursor getReservesByEmail(String email){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("SELECT * FROM RESERVE WHERE EMAIL = '"+email+"'",null);
        return cursor;
    }


    public Cursor getAllSpecialOffers(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("SELECT * FROM CAR WHERE OFFERS = '"+1+"'",null);
        return cursor;
    }

    public Cursor getAllReserves(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("SELECT * FROM RESERVE",null); return cursor;
    }

    public void insert(String Email,String FirstName,String LastName,String Password,String Gender, String Phone) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", Email);
        contentValues.put("FIRSTNAME",FirstName);
        contentValues.put("LASTNAME", LastName);
        contentValues.put("PASSWORD", Password);
        contentValues.put("GENDER", Gender);
        contentValues.put("PHONE", Phone);
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert("USER", null, contentValues);
    }


    public void insertAdmin(String Email,String FirstName,String LastName,String Password,String Gender, String Phone) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("ADMINEMAIL", Email);
        contentValues.put("ADMINFIRSTNAME",FirstName);
        contentValues.put("ADMINLASTNAME", LastName);
        contentValues.put("ADMINPASSWORD", Password);
        contentValues.put("ADMINGENDER", Gender);
        contentValues.put("ADMINPHONE", Phone);
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert("ADMIN", null, contentValues);
    }



    public boolean LogIn(String email,String password){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT EMAIL, PASSWORD FROM User WHERE EMAIL = ?",new String[] {email});
        //System.out.println("***************"+cursor.getCount());

        if(cursor.getCount()>0) {
            if (cursor.moveToFirst()){
                String data = cursor.getString(cursor.getColumnIndex("PASSWORD"));
                if(password.equals(data))
                    return true;
            }
            return false;
        }
        else
            return false;
    }

    public boolean LogInAsAdmin(String email,String password){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT ADMINEMAIL, ADMINPASSWORD FROM ADMIN WHERE ADMINEMAIL = ?",new String[] {email});

        if(cursor.getCount()>0) {
            return true;
        }
        else
            return false;
    }



    public Cursor getAllUsers(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("SELECT EMAIL FROM USER",null);


        System.out.println("***************"+cursor.getCount());
        return cursor;
    }

//    public Cursor getUserByEmail(String email){
//        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
//        //Cursor cursor= sqLiteDatabase.rawQuery("SELECT * FROM USER WHERE EMAIL = '"+email+"'",null);
//        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM User WHERE EMAIL = ?",new String[] {email});
//
//        return cursor;
//    }

    public Cursor getUserByEmail(String email) {
        Cursor user = null;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String strSQL = "SELECT * FROM USER WHERE EMAIL = '" + email + "'";
        try {
            user = sqLiteDatabase.rawQuery(strSQL, null);
        } catch (Exception e) {

        }
        return user;
    }

//    public boolean isAdmin(String email){
//        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
//        Cursor cursor = sqLiteDatabase.rawQuery("SELECT EMAIL, PASSWORD FROM ADMIN WHERE EMAIL = ?",new String[] {email});
//        if(cursor.getCount()>0) {
//            if (cursor.moveToFirst()){
//                String data = cursor.getString(cursor.getColumnIndex("ADMIN"));
//                if("true".equals(data))
//                    return true;
//            }
//            return false;
//        }
//        else
//            return false;
//
//}

    public Cursor getAllAdmins(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("SELECT * FROM ADMIN",null); return cursor;
    }

    public void deleteByEmail(String email){
        System.out.println("DELETING : " + email);
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        sqLiteDatabase.delete("USER","EMAIL="+"'"+email+"'",null);

    }



}