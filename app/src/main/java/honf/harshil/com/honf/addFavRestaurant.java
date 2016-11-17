package honf.harshil.com.honf;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by harshil on 18.11.16.
 */

public class addFavRestaurant extends SQLiteOpenHelper implements BaseColumns {

    private final static int DATABASE_VER = 2;
    private static final String DATABASE_NAME = "DB1";
    private static final String RestaurantId="Rid";
    private static final String una="username";
    private static final String TABLE_NAME="favRestaurant";
    public static final String Createtable="CREATE TABLE "+TABLE_NAME+"("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+RestaurantId+" TEXT,"+una+" TEXT)";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(user.create_reg_table);
        sqLiteDatabase.execSQL(addCuisine.CREATE_TABLE);
        sqLiteDatabase.execSQL(add_restaurant_db.CREATE_TABLE);
        sqLiteDatabase.execSQL(Createtable);
    }
    addFavRestaurant (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VER);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean insertfav(int id,String username){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(una,username);
        contentValues.put(RestaurantId,id);
        long res=sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return res != -1;
    }
    public boolean delete(int id,String username){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        long res=sqLiteDatabase.delete(TABLE_NAME,"where "+_ID+"="+id+"and username='"+username+"'",null);
        return res!=-1;
        }
    public Cursor favres(String uname){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("Select * from "+TABLE_NAME+" where username='"+uname+"'",null);
    }
}
