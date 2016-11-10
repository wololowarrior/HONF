package honf.harshil.com.honf;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import static honf.harshil.com.honf.R.id.pass;

/**
 * Created by harshil on 8.10.16.
 */


public class user extends SQLiteOpenHelper implements BaseColumns {
    private static final int DATABASE_VER = 2;
    private static final String DATABASE_NAME = "DB1";
    private static final String TABLE_Reg = "registration";
    private static final String username = "username";
    //        private static final String id="id";
    private static final String name = "name";
    private static final String password = "password";
    private static final String number = "number";
    private String Create_reg_table = "CREATE TABLE " + TABLE_Reg +
            "(" + username + " TEXT PRIMARY KEY,"
            + name + " TEXT,"
            + password + " TEXT,"
            + number + " TEXT)";

    user(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqdb) {
        sqdb.execSQL(Create_reg_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_Reg);
        onCreate(sqLiteDatabase);
    }

    boolean insert_data(String uname, String nam, String pass, String num) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(username, uname);
        values.put(name, nam);
        values.put(password, pass);
        values.put(number, num);
        long res = db.insert(TABLE_Reg, null, values);
        return res != -1;

    }

    Cursor getEntry(String username) {
        SQLiteDatabase db = this.getWritableDatabase();

        //String p="";


        return db.rawQuery("select * from " + TABLE_Reg + " where username='" + username + "'", null);
    }
}


