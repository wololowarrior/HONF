package honf.harshil.com.honf;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;


public class addCuisine extends SQLiteOpenHelper implements BaseColumns {

    private final static int DATABASE_VER = 2;
    private static final String DATABASE_NAME = "DB1";
    private static final String TABLE_NAME = "CuisineTable";
    private static final String Cusine_name = "Cuisine_Name";
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
            "(" + _ID + " INTEGER PRIMARY KEY,"
            + Cusine_name + " TEXT)";

    public addCuisine(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_NAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    boolean insert_data(String nameCuisine) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Cusine_name,nameCuisine);
        long result=db.insert(TABLE_NAME,null,contentValues);
        return result!=-1;
    }
}
