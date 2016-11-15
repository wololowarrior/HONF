package honf.harshil.com.honf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class results extends AppCompatActivity {
    Button btn;
    String location,cuisine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Bundle ex=getIntent().getExtras();
        location=ex.getString("Location");
        cuisine=ex.getString("cuisine");
//        Log.d("debug",cuisine);
//        Log.d("debug",location);
    }
    public void restProfile(View view){
        Intent i=new Intent(getApplicationContext(),restaraunt_profile.class);
        startActivity(i);
    }


}
