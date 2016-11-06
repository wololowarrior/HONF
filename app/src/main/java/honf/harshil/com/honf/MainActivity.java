package honf.harshil.com.honf;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.design.internal.NavigationMenu;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Objects;

import static android.R.attr.password;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static android.util.Log.d;
import static honf.harshil.com.honf.R.id.pass;
import static honf.harshil.com.honf.R.id.start;
import static honf.harshil.com.honf.R.id.uname;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    Button btnLoginDialog;
    user db = new user(this);
    String uname = "username";

    //RelativeLayout r=(RelativeLayout)findViewById(R.id.main_at);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout F = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_main, F);
        // getSupportActionBar().hide();
        Typeface t = Typeface.createFromAsset(getAssets(), "fonts/SourceSansPro-Light.ttf");
        Typeface t1 = Typeface.createFromAsset(getAssets(), "fonts/SourceSansPro-Regular.ttf");
        Button b = (Button) findViewById(R.id.continue_button);
        Button b1 = (Button) findViewById(R.id.signup_button);
//        FloatingActionButton fab1=(FloatingActionButton)findViewById(R.id.add_hotel);

        b1.setTypeface(t1);
        b.setTypeface(t);
        btnLoginDialog = (Button) findViewById(R.id.login_button);
        btnLoginDialog.setOnClickListener(this);
        if (getSharedPreferences(getApplicationContext()).getString(uname, "").length() != 0) {
            Intent i = new Intent(this, user_home.class);
            startActivity(i);
            finish();
        }
        NavigationView navigationView=(NavigationView)findViewById(R.id.navigation_view);
        Menu menuNav=navigationView.getMenu();
        MenuItem item=menuNav.findItem(R.id.add_hotels);
        item.setVisible(false);

    }

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    @Override
    public void onClick(View v) {
        if (v == btnLoginDialog) {

            // Create Object of Dialog class
            final Dialog login = new Dialog(this);
            // Set GUI of login screen
            final Animation a1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
            login.setContentView(R.layout.login_box);

            //r.setVerticalGravity(0);
            login.setTitle("Login to HONF");
            // Init button of login GUI
            Button btnLogin = (Button) login.findViewById(R.id.btnLogin);
            Button btnCancel = (Button) login.findViewById(R.id.btnCancel);
            final EditText txtUsername = (EditText) login.findViewById(R.id.txtUsername);
            final EditText txtPassword = (EditText) login.findViewById(R.id.txtPassword);
            // Attached listener for login GUI button
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String username = txtUsername.getText().toString();
                    String password = txtPassword.getText().toString();
                    if (txtPassword.getText().toString().equals("") || txtUsername.getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(),
                                "Please enter Username and Password", Toast.LENGTH_LONG).show();
                    } else {
                        //d("password", password);
                        Cursor cursor = db.getEntry(username);
                        if (!cursor.moveToFirst()) {
                            Toast.makeText(getApplicationContext(), "Wrong username", Toast.LENGTH_LONG).show();
                            login.dismiss();

                        } else {
                            String p = cursor.getString(cursor.getColumnIndex("password"));
                            if (txtUsername.getText().toString().trim().length() > 0 && txtPassword.getText().toString().trim().length() > 0) {

//                         Validate Your login credential here than display message
                                if (password.equals(p)) {
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            SharedPreferences s = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                            SharedPreferences.Editor editor = s.edit();
                                            editor.putString("username", username);
                                            editor.apply();
                                        }
                                    }).start();
                                    Toast.makeText(getApplicationContext(),
                                            "Welcome Back " + cursor.getString(cursor.getColumnIndex("name")), Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(getApplicationContext(), user_home.class);

                                    startActivity(i);
                                    finish();
//                         Redirect to dashboard / home screen.
                                    login.dismiss();
                                } else if (!(p.equals(password))) {
                                    Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Wrong Username", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
            });

            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    login.dismiss();
                }
            });

            // Make dialog box visible.
            login.show();
        }

    }

    public void signup(View view) {
        Intent intent = new Intent(this, signup.class);
        startActivity(intent);
    }

    public void onBackPressed() {
        moveTaskToBack(true);
        SharedPreferences.Editor editor = getSharedPreferences(getApplicationContext()).edit();
        editor.clear(); //clear all stored data
        editor.apply();
    }

    public void search(View view) {
        Intent intent = new Intent(this, search_enter.class);
        startActivity(intent);
    }

    public void adminPage(View view) {
        Intent i = new Intent(this, admin.class);
        startActivity(i);
    }
    public void addRestaurant(View view){
        Intent i=new Intent(getApplicationContext(),activity_add_restaurant.class);
        startActivity(i);
    }
}


