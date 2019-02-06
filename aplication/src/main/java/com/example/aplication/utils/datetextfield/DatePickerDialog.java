package com.example.aplication.utils.datetextfield;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import java.util.Calendar;

/**
 * Created by juniorbraga on 27/09/16.
 */
public class DatePickerDialog extends DateAlertDialog {

    private Long mMinDate;
    private Long mMaxDate;

    static Calendar sCalendar;
    int mDay;
    int mMonth;
    int mYear;
    Context mContext;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        this.sCalendar = Calendar.getInstance();
        this.mDay = sCalendar.get(Calendar.DAY_OF_MONTH);
        this.mMonth = sCalendar.get(Calendar.MONTH);
        this.mYear = sCalendar.get(Calendar.YEAR);
        this.mContext = getActivity();

        android.app.DatePickerDialog datePickerDialog = new android.app.DatePickerDialog(mContext, getCallback(), mYear, mMonth, mDay);

        if (getMaxDate() != null && getMaxDate() != 0) {
            datePickerDialog.getDatePicker().setMinDate(getMaxDate());
        }

        if (getMinDate() != null && getMaxDate() != 0) {
            datePickerDialog.getDatePicker().setMinDate(getMinDate());
        }

        return datePickerDialog;
    }

    public Long getMinDate() {
        return mMinDate;
    }

    public void setMinDate(Long mMinDate) {
        this.mMinDate = mMinDate;
    }

    public Long getMaxDate() {
        return mMaxDate;
    }

    public void setMaxDate(Long mMaxDate) {
        this.mMaxDate = mMaxDate;
    }


}
