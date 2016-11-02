package honf.harshil.com.honf;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

public class signup extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout F = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_signup, F);
        Typeface t1=Typeface.createFromAsset(getAssets(),"fonts/SourceSansPro-Regular.ttf");
        Button b1=(Button)findViewById(R.id.button1);
        Button b2=(Button)findViewById(R.id.button2);
        b2.setTypeface(t1);
        b1.setTypeface(t1);


    }
    public  void signupclick(View view){
        user db=new user(this);
        EditText name=(EditText)findViewById(R.id.name);
        EditText uname=(EditText)findViewById(R.id.uname);
        EditText password=(EditText)findViewById(R.id.pass);
        EditText number=(EditText)findViewById(R.id.phno);

boolean re=db.insert_data(uname.getText().toString(),name.getText().toString(),password.getText().toString(),number.getText().toString());
if (re==false){
    Toast.makeText(getBaseContext(),"Not inserted",Toast.LENGTH_LONG).show();
    Intent i=new Intent(getApplicationContext(),MainActivity.class);
    startActivity(i);
}
        else
    Toast.makeText(getBaseContext(),"Inserted",Toast.LENGTH_LONG).show();
    }
    public void returnto(View v){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
