package honf.harshil.com.honf;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by harshil on 13.11.16.
 */

public class add_restaurant_db extends SQLiteOpenHelper implements BaseColumns {


    private final static int DATABASE_VER = 2;
    private static final String DATABASE_NAME = "DB1";
    private static final String TABLE_NAME = "reg_Restaurant";
    private static final String Name="Restaurant_Name";
    private static final String Address="Address";
    private static final String City="City";
    private static final String Pincode="Pincode";
    private static final String Phone="Phone";
    private static final String openingTime="OT";
    private static final String closingTime="CT";
    private static final String cuisine="cuisine";

    public static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"( "+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                                             Name+" TEXT,"+
                                                Address+" TEXT,"+City+" TEXT,"+Pincode+" TEXT,"+Phone+" TEXT,"+openingTime+" TEXT,"+closingTime+" TEXT,"+cuisine+" TEXT)";
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(user.create_reg_table);
        sqLiteDatabase.execSQL(addCuisine.CREATE_TABLE);
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }
    add_restaurant_db (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VER);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public Boolean addRes(String name,String address,String city,String pincode,String phone,String ot,String ct,String cu){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues  contentValues= new ContentValues();
        contentValues.put(Name,name);
        contentValues.put(Address,address);
        contentValues.put(City,city);
        contentValues.put(Phone,phone);
        contentValues.put(Pincode,pincode);
        contentValues.put(openingTime,ot);
        contentValues.put(closingTime,ct);
        contentValues.put(cuisine,cu);
        long res=sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return res!=-1;
    }
    public Cursor getAll(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        return sqLiteDatabase.rawQuery("Select "+_ID+" , "+Name+" from "+TABLE_NAME,null);
    }

}
