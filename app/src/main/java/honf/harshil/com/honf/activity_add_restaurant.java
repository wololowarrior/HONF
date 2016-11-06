package honf.harshil.com.honf;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TimePicker;
import android.widget.Toast;

public class activity_add_restaurant extends BaseActivity implements View.OnClickListener, View.OnFocusChangeListener, TimePickerDialog.OnTimeSetListener {
    EditText et, et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout F = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_add_restaurant, F);
        et = (EditText) findViewById(R.id.openingTime);
        et1 = (EditText) findViewById(R.id.closingTime);
        et.setOnClickListener(this);
        et.setOnFocusChangeListener(this);
        et1.setOnClickListener(this);
        et1.setOnFocusChangeListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.openingTime:
                Toast.makeText(getApplicationContext(), "Button Pressed", Toast.LENGTH_LONG).show();
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "TimePicker");
                break;
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
