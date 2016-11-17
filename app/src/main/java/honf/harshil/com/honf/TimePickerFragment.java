package honf.harshil.com.honf;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.format.DateFormat;
import android.widget.EditText;
import android.widget.TimePicker;

/**
 * Created by harshil on 28.10.16.
 */

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(), this, hour, min, DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String hour = "";
        String min = "";
        if (String.valueOf(hourOfDay).length() == 1) {
            hour = "0" + String.valueOf(hourOfDay);
        } else {
            hour = String.valueOf(hourOfDay);
        }
        if (String.valueOf(minute).length() == 1) {
            min = "0" + String.valueOf(minute);
        }
        else min=String.valueOf(minute);
        // Log.d("hourofday",String.valueOf(hourOfDay));
        EditText et = (EditText) getActivity().findViewById(R.id.openingTime);
        EditText et2 = (EditText) getActivity().findViewById(R.id.closingTime);
        if (et.isFocused()) {

            et.setText(String.valueOf(hour) +
                    ":" + String.valueOf(min));
        } else if (et2.isFocused()) {
            et2.setText(String.valueOf(hourOfDay) +
                    ":" + String.valueOf(min));
        }
    }

}

