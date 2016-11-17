package honf.harshil.com.honf;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

public class user_home extends BaseActivity {
    user u;
    SharedPreferences sharedPreferences;
    String username="username";
    String uname;
  TextView t,t1;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout F=(FrameLayout)findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_user_home,F);

        uname=getSharedPreferences(getApplicationContext()).getString(username,"");
        // t.setText(uname);
        t=(TextView)findViewById(R.id.userName);
        t1=(TextView)findViewById(R.id.resname);
        Log.d("username",uname);
        t.setText(uname);
        addFavRestaurant favRestaurant=new addFavRestaurant(this);
        Cursor c= favRestaurant.favres(uname);
        while(c.moveToNext()){
            add_restaurant_db db=new add_restaurant_db(this);
            Cursor cursor=db.getdata(Integer.parseInt(c.getString(c.getColumnIndex("Rid"))));
            cursor.moveToNext();
            t1.setText(cursor.getString(cursor.getColumnIndex("Restaurant_Name")));
        }
    }
    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

}
