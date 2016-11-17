package honf.harshil.com.honf;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class restaraunt_profile extends AppCompatActivity {
    TextView t1, t2, t3, t4;

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaraunt_profile);
        Bundle bundle = getIntent().getExtras();
        final int id = bundle.getInt("resid");
        t1 = (TextView) findViewById(R.id.phone);
        t2 = (TextView) findViewById(R.id.address);
        t3 = (TextView) findViewById(R.id.ot);
        t4 = (TextView) findViewById(R.id.ct);
//        Log.d("id", String.valueOf(id));
        add_restaurant_db db = new add_restaurant_db(this);
        CollapsingToolbarLayout toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbarlayout);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        Cursor c = db.getdata(id);
        if (c.moveToFirst()) {
            toolbarLayout.setTitle(c.getString(c.getColumnIndex("Restaurant_Name")));
            t2.setText(c.getString(c.getColumnIndex("Address")));
            t1.setText(c.getString(c.getColumnIndex("Phone")));
            t3.setText(c.getString(c.getColumnIndex("OT")));
            t4.setText(c.getString(c.getColumnIndex("CT")));
        }
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String str = t1.getText().toString();
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:" + str));
                startActivity(i);
                finish();
            }
        });
        if (getSharedPreferences(getApplicationContext()).getString("username", "").length() == 0) {
            LinearLayout l=(LinearLayout)findViewById(R.id.layoutfav);
            l.setVisibility(View.INVISIBLE);
        }
        else {
            LinearLayout l=(LinearLayout)findViewById(R.id.layoutfav);
            l.setVisibility(View.VISIBLE);
        }
         Switch  aSwitch=(Switch)findViewById(R.id.favit);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    String username=getSharedPreferences(getApplicationContext()).getString("username","");
                        addFavRestaurant favRestaurant=new addFavRestaurant(getApplicationContext());
                        boolean res=favRestaurant.insertfav(id,username);
                    if(res){
                        Toast.makeText(getApplicationContext(),"Marked as Favorite",Toast.LENGTH_SHORT).show();
                    }
                    else if(!b) {

                        boolean res1=favRestaurant.delete(id,username);
                        if(res1) {
                            Toast.makeText(getApplicationContext(), "Unmarked as favorite", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }
        });
    }
}

