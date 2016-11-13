package honf.harshil.com.honf;

import android.Manifest;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.List;

public class activity_add_restaurant extends BaseActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, View.OnFocusChangeListener, TimePickerDialog.OnTimeSetListener {
    EditText et, et1;
    Spinner spinner;
    Button openCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout F = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_add_restaurant, F);
        et = (EditText) findViewById(R.id.openingTime);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setPrompt("----Select-----");
        spinner.setOnItemSelectedListener(this);
        et1 = (EditText) findViewById(R.id.closingTime);
        openCamera = (Button) findViewById(R.id.openCamera);
        et.setOnClickListener(this);
        et.setOnFocusChangeListener(this);
        et1.setOnClickListener(this);
        et1.setOnFocusChangeListener(this);
        openCamera.setOnClickListener(this);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        0);
            }
        }
        loadspinnerdata();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.openingTime:
                Toast.makeText(getApplicationContext(), "Button Pressed", Toast.LENGTH_LONG).show();
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "TimePicker");
                break;
            case R.id.closingTime:

                DialogFragment newFragment_1 = new TimePickerFragment();
                newFragment_1.show(getFragmentManager(), "TimePicker");

                break;
            case R.id.openCamera:
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, 0);
                }


                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        ImageView capturedImage = (ImageView) findViewById(R.id.capturedImage);
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bitmap bp = (Bitmap) data.getExtras().get("data");
            capturedImage.setImageBitmap(bp);
        }
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        switch (view.getId()) {
            case R.id.openingTime:
                if (b) {
                    DialogFragment newFragment = new TimePickerFragment();
                    newFragment.show(getFragmentManager(), "TimePicker");
                }
                break;
            case R.id.closingTime:
                if (b) {
                    DialogFragment newFragment = new TimePickerFragment();
                    newFragment.show(getFragmentManager(), "TimePicker");
                }
                break;
        }
    }

    private void loadspinnerdata() {
        addCuisine cuisine = new addCuisine(getApplicationContext());
        List<String> cuisines = cuisine.getAll();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cuisines);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String str = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
