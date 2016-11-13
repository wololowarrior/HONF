package honf.harshil.com.honf;

import android.content.Context;
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
    public static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"( "+_ID+" INTEGER PRIMARY KEY AUTO INCREMENT,"+
                                             Name+" TEXT,"+
                                                Address+" TEXT,"+City+" TEXT,"+Pincode+" INTEGER,"+Phone+" INTEGER,"+openingTime+" TEXT,"+closingTime+" TEXT)";
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }
    add_restaurant_db (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VER);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
