package honf.harshil.com.honf;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class BaseActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    TextView name, uname;
    SQLiteDatabase db;
    String str = "username";
    Menu menu;
    user User = new user(this);
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        View headerLayout = navigationView.getHeaderView(0); // 0-index header
        toolbar.setTitleTextColor(Color.WHITE);
        name = (TextView) headerLayout.findViewById(R.id.name);
        uname = (TextView) headerLayout.findViewById(R.id.user_name);
        if (getSharedPreferences(getApplicationContext()).getString(str, "").length() != 0) {
            username = getSharedPreferences(getApplicationContext()).getString(str, "");
            Log.d("username", username);
            Cursor cursor = User.getEntry(username);
            if (cursor.moveToFirst()) {
                name.setText(cursor.getString(cursor.getColumnIndex("name")));
                Log.d("name", cursor.getString(cursor.getColumnIndex("name")));
                uname.setText(cursor.getString(cursor.getColumnIndex("username")));
            }

        }
        if (getSharedPreferences(getApplicationContext()).getString(str, "").length() == 0) {


            Menu menuNav=navigationView.getMenu();
            MenuItem nav_item2 = menuNav.findItem(R.id.action_logout);
            nav_item2.setVisible(false);

        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.action_logout:
                        SharedPreferences.Editor editor = getSharedPreferences(getApplicationContext()).edit();
                        editor.clear(); //clear all stored data
                        editor.apply();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();
                        break;
                    case R.id.add_hotels:
                        Intent i1=new Intent(getApplicationContext(),activity_add_restaurant.class);
                        startActivity(i1);
                        break;
                    case R.id.admin:
                        Intent i2=new Intent(getApplicationContext(),admin.class);
                        startActivity(i2);
                        break;
                    case R.id.search:
                        Intent i3=new Intent(getApplicationContext(),search_enter.class);
                        startActivity(i3);
                        finish();
                        break;
                    case R.id.action_home:
                        Intent i4=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(i4);
                        finish();
                        break;
                }
                return false;
            }
        });

    }

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        actionBarDrawerToggle.syncState();
    }
}


