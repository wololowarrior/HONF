package honf.harshil.com.honf;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class search_enter extends BaseActivity implements AdapterView.OnItemSelectedListener {
    ImageView imageView;
    String location1,str,location2,location3,location4;
    String cuisuine;
    EditText t;
    private Spinner spinner;
    private String cityname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout F = (FrameLayout) findViewById(R.id.content_frame);
        Log.d("Debug", "33");
        getLayoutInflater().inflate(R.layout.activity_search_enter, F);
        imageView = (ImageView) findViewById(R.id.loc);
        t = (EditText) findViewById(R.id.location);
       final GPSTracker gps=new GPSTracker(this,search_enter.this);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gps.canGetLocation()){
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    // \n is for new line
                    Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                    try {
                        List<Address> listAddresses = geocoder.getFromLocation(latitude, longitude, 1);
                        if(null!=listAddresses&&listAddresses.size()>0){
                            location1 = listAddresses.get(0).getAddressLine(0);
                            location2=listAddresses.get(0).getAddressLine(1);
                            location3 = listAddresses.get(0).getAddressLine(2);
                            location4= listAddresses.get(0).getAddressLine(3);

                             cityname = listAddresses.get(0).getLocality();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    t.setText(cityname);
                    Toast.makeText(getApplicationContext(),location1+" "+location2+" "+location3+" "+location4, Toast.LENGTH_LONG).show();

                }

                else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }
            }
        });
        spinner = (Spinner) findViewById(R.id.searchView);
        spinner.setPrompt("----select----");
        spinner.setOnItemSelectedListener(this);
        loadspinnerdata();
    }

    private void loadspinnerdata() {
        addCuisine cuisine = new addCuisine(getApplicationContext());
        List<String> cuisines = cuisine.getAll();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cuisines);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        str = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void content(View view) {
        location1 =t.getText().toString();
        Intent i = new Intent(getApplicationContext(), results.class);
//        Log.d("debog",location1);
        i.putExtra("Location",location1);
        i.putExtra("cuisine",str);
        startActivity(i);
    }
}


