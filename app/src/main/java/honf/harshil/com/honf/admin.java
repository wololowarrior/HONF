package honf.harshil.com.honf;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import static android.provider.BaseColumns._ID;
import static android.widget.Toast.LENGTH_LONG;

public class admin extends BaseActivity implements View.OnClickListener {

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
        AppCompatImageButton b = (AppCompatImageButton) findViewById(R.id.appCompatImageButton);
        b.setOnClickListener(this);
        addCuisine cuisine = new addCuisine(this);
        Cursor cursor = cuisine.getAllCuisine();
        TableLayout tableLayout = (TableLayout) findViewById(R.id.table);
        TableRow tr;
        TextView t, t1;
        if (!cursor.moveToNext()) {
    tr=new TableRow(this);
            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
            t= new TextView(this);
            t.setText("No Cuisine Available...");
            t.setTextSize(30);
            t.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            tr.addView(t);
            tableLayout.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

        } else {
            do {
                tr = new TableRow(this);
                tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                t = new TextView(this);
                t.setText(cursor.getString(cursor.getColumnIndex(_ID)));
                t.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                t.setPadding(10, 10, 10, 10);
                t.setTextSize(20);
                tr.addView(t);
                t1 = new TextView(this);
                t1.setText(cursor.getString(cursor.getColumnIndex("Cuisine_Name")));
                t1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                t1.setTextSize(20);
                t1.setPadding(10, 10, 10, 10);
                tr.addView(t1);
                tableLayout.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
            } while (cursor.moveToNext());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.appCompatImageButton:
//                Toast.makeText(getApplicationContext(),"Pressed",LENGTH_LONG).show();
                TextInputEditText et = (TextInputEditText) findViewById(R.id.textInputEditText);
                Log.d("String", et.getText().toString());
                addCuisine cuisine = new addCuisine(this);
                boolean res = cuisine.insert_data(et.getText().toString());
                if (res) {
                    Toast.makeText(getApplicationContext(), "Inserted", LENGTH_LONG).show();
//                    addC();
                    Intent i=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Not Inserted", LENGTH_LONG).show();
                }
                break;
        }
    }
//    public void addC(){
//        TableLayout tableLayout = (TableLayout) findViewById(R.id.table);
//        addCuisine cuisine = new addCuisine(this);
//        Cursor cursor = cuisine.getAllCuisine();
//        TableRow tr;
//        TextView t, t1;
//        do {
//            tr = new TableRow(this);
//            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
//            t = new TextView(this);
//            t.setText(cursor.getString(cursor.getColumnIndex(_ID)));
//            t.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
//            t.setPadding(10, 10, 10, 10);
//            t.setTextSize(20);
//            tr.addView(t);
//            t1 = new TextView(this);
//            t1.setText(cursor.getString(cursor.getColumnIndex("Cuisine_Name")));
//            t1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
//            t1.setTextSize(20);
//            t1.setPadding(10, 10, 10, 10);
//            tr.addView(t1);
//            tableLayout.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
//        } while (cursor.moveToNext());
//    }
}
