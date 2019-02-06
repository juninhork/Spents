package com.example.aplication.utils.datetextfield;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import com.example.aplication.R;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by juniorbraga on 27/09/16.
 */
public class MonthYearPickerDialog extends DateAlertDialog {

    public static final Locale PT_BR = new Locale("pt", "BR");

    private static Calendar sCalendar = Calendar.getInstance(PT_BR);
    private static final int MIN_MONTH_INDEX = 0;
    private static final int MAX_MONTH_INDEX = 11;
    public static final int BASE_DAY = sCalendar.get(Calendar.DAY_OF_MONTH);
    public static final int BASE_MONTH = sCalendar.get(Calendar.MONTH);
    public static final int BASE_YEAR = sCalendar.get(Calendar.YEAR);
    public static final int MIN_YEAR = sCalendar.getActualMinimum(Calendar.YEAR); // Minimum Year in GregorianCalendar class.
    public static final int MAX_YEAR = sCalendar.getActualMaximum(Calendar.YEAR);

    private Long mMinDate;
    private Long mMaxDate;

    private LinearLayout mLayoutMonth;

    private LinearLayout mLayoutYear;

    private NumberPicker mMonthPicker;

    private NumberPicker mYearPicker;

    boolean onlyYear;
    boolean onlyMonth;

    private Context mContext;
    private View mDialogView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        this.mContext = getActivity();

        this.mDialogView = getActivity().getLayoutInflater().inflate(R.layout.fragment_date_picker_dialog, null);

        this.mLayoutMonth = (LinearLayout)this.mDialogView.findViewById(R.id.layout_month);
        this.mLayoutYear = (LinearLayout)this.mDialogView.findViewById(R.id.layout_year);
        this.mMonthPicker = (NumberPicker)this.mDialogView.findViewById(R.id.np_month);
        this.mLayoutYear = (NumberPicker)this.mDialogView.findViewById(R.id.np_year);

        this.mMonthPicker.setMinValue(MIN_MONTH_INDEX);
        this.mMonthPicker.setMaxValue(MAX_MONTH_INDEX);
        this.mMonthPicker.setDisplayedValues(getCalendarMonths());
        this.mMonthPicker.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);

        this.mYearPicker.setMinValue(MIN_YEAR);
        this.mYearPicker.setMaxValue(MAX_YEAR);
        this.mYearPicker.setValue(BASE_YEAR);
        this.mYearPicker.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);

        if (isOnlyMonth()) {
            this.mLayoutMonth.setVisibility(View.VISIBLE);
            this.mLayoutYear.setVisibility(View.GONE);
        } else if (isOnlyYear()) {
            this.mLayoutMonth.setVisibility(View.GONE);
            this.mLayoutYear.setVisibility(View.VISIBLE);
        } else {
            this.mLayoutMonth.setVisibility(View.VISIBLE);
            this.mLayoutYear.setVisibility(View.VISIBLE);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
        builder.setView(this.mDialogView);


        builder.setPositiveButton(R.string.alert_ok, positiveClickListener());

        return builder.create();
    }

    private DialogInterface.OnClickListener positiveClickListener() {
        return new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                int year = mYearPicker.getValue();
                int month = mMonthPicker.getValue();

                getCallback().onDateSet(null, year, month, BASE_DAY);
            }
        };
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

    public String[] getCalendarMonths() {
        return mContext.getResources().getStringArray(R.array.months);
    }

    public void setOnlyYear(boolean isOnlyYear) {
        this.onlyYear = isOnlyYear;
    }

    public void setOnlyMonth(boolean isOnlyMonth) {
        this.onlyMonth = isOnlyMonth;
    }

    public boolean isOnlyYear() {
        return onlyYear;
    }

    public boolean isOnlyMonth() {
        return onlyMonth;
    }

}
