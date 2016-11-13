package honf.harshil.com.honf;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;


public class addCuisine extends SQLiteOpenHelper implements BaseColumns {

    private final static int DATABASE_VER = 2;
    private static final String DATABASE_NAME = "DB1";
    private static final String TABLE_NAME = "CuisineTable";
    private static final String Cusine_name = "Cuisine_Name";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
            "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + Cusine_name + " TEXT)";

    public addCuisine(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    boolean insert_data(String nameCuisine) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Cusine_name,nameCuisine);
        long result=db.insert(TABLE_NAME,null,contentValues);
        return result!=-1;
    }
    Cursor getAllCuisine() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("Select * from "+TABLE_NAME, null);

    }
    public List<String> getAll(){
        List<String> cuisines= new ArrayList<String>();
        SQLiteDatabase database=this.getWritableDatabase();
        Cursor cursor=database.rawQuery("Select * from "+TABLE_NAME,null);
        if (cursor.moveToFirst()) {
            do {
                cuisines.add(cursor.getString(cursor.getColumnIndex(Cusine_name)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return cuisines;
    }
    }
