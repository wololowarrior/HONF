package honf.harshil.com.honf;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

public class admin extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout F = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_admin, F);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);

        View headerLayout = navigationView.getHeaderView(0);
        name = (TextView) headerLayout.findViewById(R.id.name);
        uname = (TextView) headerLayout.findViewById(R.id.user_name);
        name.setText("Admin");
        uname.setText("Admin");
        AppCompatImageButton b=(AppCompatImageButton)findViewById(R.id.appCompatImageButton);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.appCompatImageButton:
//                Toast.makeText(getApplicationContext(),"Pressed",LENGTH_LONG).show();
                TextInputEditText et=(TextInputEditText)findViewById(R.id.textInputEditText);
                Log.d("String",et.getText().toString());
                addCuisine cuisine=new addCuisine(this);
               boolean res= cuisine.insert_data(et.getText().toString());
                if (res){
                    Toast.makeText(getApplicationContext(),"Inserted",LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Not Inserted",LENGTH_LONG).show();
                }
                break;
        }
    }
}
