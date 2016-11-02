package honf.harshil.com.honf;

import android.content.Context;
import android.content.SharedPreferences;
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
  TextView t;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout F=(FrameLayout)findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_user_home,F);

        uname=getSharedPreferences(getApplicationContext()).getString(username,"");
        // t.setText(uname);
        t=(TextView)findViewById(R.id.userName);
        Log.d("username",uname);
        t.setText(uname);
    }
    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

}
