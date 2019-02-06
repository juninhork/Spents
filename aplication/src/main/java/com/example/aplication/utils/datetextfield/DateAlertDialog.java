package com.example.aplication.utils.datetextfield;

import android.app.DatePickerDialog;
import android.support.v4.app.DialogFragment;

/**
 * Created by juniorbraga on 27/09/16.
 */
public class DateAlertDialog extends DialogFragment {

    DatePickerDialog.OnDateSetListener mCallback;

    public DatePickerDialog.OnDateSetListener getCallback() {
        return mCallback;
    }


    public void registerCallback(DatePickerDialog.OnDateSetListener callback) {
        this.mCallback = callback;
    }
}
