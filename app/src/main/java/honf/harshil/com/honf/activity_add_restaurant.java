package honf.harshil.com.honf;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TimePicker;

public class activity_add_restaurant extends BaseActivity implements View.OnClickListener, View.OnFocusChangeListener,TimePickerDialog.OnTimeSetListener
{
    EditText et,et1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout F = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_add_restaurant, F);
        et=(EditText)findViewById(R.id.openingTime);
        et1=(EditText)findViewById(R.id.closingTime);


    }



    @Override
    public void onClick(View view) {
        if(et.isEnabled()) {
            et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    DialogFragment newFragment = new TimePickerFragment();
                    newFragment.show(getFragmentManager(), "TimePicker");
                }
            });
        }
        else if(et1.isEnabled()) {
            et1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    DialogFragment newFragment = new TimePickerFragment();
                    newFragment.show(getFragmentManager(), "TimePicker");

                }
            });
        }
    }

    @Override
    public void onFocusChange(View view, boolean b) {

    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {

    }
}
