package honf.harshil.com.honf;

import android.Manifest;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

public class activity_add_restaurant extends BaseActivity implements View.OnClickListener, View.OnFocusChangeListener, TimePickerDialog.OnTimeSetListener {
    EditText et, et1;
    Button openCamera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout F = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_add_restaurant, F);
        et = (EditText) findViewById(R.id.openingTime);
        et1 = (EditText) findViewById(R.id.closingTime);
        openCamera=(Button)findViewById(R.id.openCamera);
        et.setOnClickListener(this);
        et.setOnFocusChangeListener(this);
        et1.setOnClickListener(this);
        et1.setOnFocusChangeListener(this);
        openCamera.setOnClickListener(this);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        0);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
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
        ImageView capturedImage=(ImageView)findViewById(R.id.capturedImage);
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
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

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {

    }
}
