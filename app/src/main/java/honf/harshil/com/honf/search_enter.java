package honf.harshil.com.honf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class search_enter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_enter);
    }
    public void content(View view){
        Intent intent=new Intent(this,results.class);
        startActivity(intent);
    }
}
