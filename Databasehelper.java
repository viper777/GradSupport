package com.example.a21701125.testingitout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 21701125 on 14/05/2017.
 */

public class Databasehelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private  static final String Database_name="contacts.db";
    private  static final String TABLE_NAME="contacts";
    private  static final String Colums_ID="id";
    private  static final String Colums_name="name";
    private  static final String Colums_email="email";
    private  static final String Colums_uname="uname";
    private  static final String Columns_pass="pass";
SQLiteDatabase db;
    private static final String Table_create="Create table Contacts(id integer priamry key not null ,"+
        "name text not null,email text not null,uname text not null,pass text not null);";


    public Databasehelper(Context context)
    {
super(context,Database_name,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Table_create);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query="Drop table if exists"+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);

    }

public void insertContact(Contact c)
{
    db=this.getWritableDatabase();
    ContentValues values=new ContentValues();
    String query="select * from Contacts";
    Cursor cursor=db.rawQuery(query,null);
    int count= cursor.getCount();
    values.put(Colums_ID,count);

    values.put(Colums_email,c.getEmail());
values.put(Colums_name,c.getName());
    values.put(Colums_uname,c.getUname());
    values.put(Columns_pass,c.getPass());
db.insert(TABLE_NAME,null,values);
db.close();
}
public String searchPass(String uname)
{
    db=this.getReadableDatabase();
    String query="select uname, pass from "+TABLE_NAME;
Cursor cursor=db.rawQuery(query,null);
 String a,b;
    b="not Found";
    if(cursor.moveToFirst())
    {
        do{
            a=cursor.getString(0);

           if(a.equals(uname))
           {
               b=cursor.getString(1);
               break;
           }
        }
        while(cursor.moveToNext());
    }
return b;
}
}
