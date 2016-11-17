package honf.harshil.com.honf;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.Toast;

public class results extends BaseActivity {
    Button btn;
    String location, cuisine;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    add_restaurant_db restaurantDb;
    restaurantEntity[] entity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout F = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_results, F);
        Bundle ex = getIntent().getExtras();
        location = ex.getString("Location");
        cuisine = ex.getString("cuisine");
        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
  //      Switch aSwitch=(Switch)findViewById(R.id.favit);
//        aSwitch.setVisibility(View.GONE);
        recyclerView.setHasFixedSize(true);
        restaurantDb = new add_restaurant_db(this);
        Cursor cursor = restaurantDb.getAll(location, cuisine);
        int in = cursor.getCount();
        entity = new restaurantEntity[in];
        if ( cursor.moveToFirst()){

        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                entity[i] = new restaurantEntity(cursor.getInt(0), cursor.getString(cursor.getColumnIndex("Restaurant_Name")));
                adapter = new dbadapter(getBaseContext(), entity);
                recyclerView.setAdapter(adapter);
                cursor.moveToNext();
            }
        }
        } else {
            Toast.makeText(getApplicationContext(), "No Restaurant Available", Toast.LENGTH_SHORT).show();
        }
//        Log.d("debug", cuisine);
  //      Log.d("debug", location);
    }

    public void restProfile(View view) {
        Intent i = new Intent(getApplicationContext(), restaraunt_profile.class);
        startActivity(i);
    }


}
