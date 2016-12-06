package honf.harshil.com.honf;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class search_enter extends BaseActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    ImageView imageView;
    String location1, str, location2, location3, location4;
    String cuisuine;
    EditText t;
    private Spinner spinner;
    GoogleApiClient apiClient;
    List<android.location.Address> addresses;
    LocationRequest locationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout F = (FrameLayout) findViewById(R.id.content_frame);
        Log.d("Debug", "33");
        getLayoutInflater().inflate(R.layout.activity_search_enter, F);
        imageView = (ImageView) findViewById(R.id.loc);
        imageView.setOnClickListener((View.OnClickListener) this);
        t = (EditText) findViewById(R.id.location);
        apiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
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
        //Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void content(View view) {
        location1 = t.getText().toString();
        if (location1.equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter location", Toast.LENGTH_LONG).show();
        } else {
            Intent i = new Intent(getApplicationContext(), results.class);
//        Log.d("debog",location1);
            i.putExtra("Location", location1);
            i.putExtra("cuisine", str);
            startActivity(i);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        apiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(apiClient, locationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(final Location location) {
        final Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

              try {
                  addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
              } catch (IOException e) {


              }



    }

    @Override
    public void onClick(final View view) {
        final String[] city = {""};
        switch (view.getId()) {
            case R.id.loc:

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        city[0] = addresses.get(0).getLocality();
                        t.post(new Runnable() {
                            @Override
                            public void run() {
                                t.setText(city[0]);
                            }
                        });
                    }
                }).start();
                break;


        }
    }
}


