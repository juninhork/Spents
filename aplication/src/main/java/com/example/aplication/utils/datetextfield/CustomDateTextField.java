package com.example.aplication.utils.datetextfield;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.app.FragmentManager;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import com.example.aplication.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * Created by juniorbraga on 27/09/16.
 */
public class CustomDateTextField extends android.support.v7.widget.AppCompatEditText implements android.app.DatePickerDialog.OnDateSetListener {

    public static final int FULL = 0;
    public static final int MONTH = 1;
    public static final int YEAR = 2;
    public static final int MONTH_YEAR = 3;

    private static final String TAG = "DateTextField";
    private static final java.lang.String DATE_FORMAT = "dd/MM/yyyy";
    private DateAlertDialog mDatePickerDialog;
    private FragmentManager mFragmentManager;
    private Long mMinDate;
    private Long mMaxDate;
    private int mDialogType = FULL;
    private IDateTextFieldCallBack mSelectionCallBack;

    public CustomDateTextField(Context context) {
        super(context);
        initializeValues();
    }

    public CustomDateTextField(Context context, AttributeSet attrs) {
        super(context, attrs);
        getDialogTypeAttr(context, attrs);
        initializeValues();
    }

    public CustomDateTextField(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getDialogTypeAttr(context, attrs);
        initializeValues();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
        try {
            setText(getDateValue(year, monthOfYear, dayOfMonth));
        } catch (ParseException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    private String getDateValue(int year, int monthOfYear, int dayOfMonth) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        String formatedDate = simpleDateFormat.format(new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime());

        switch (getDialogType()) {
            case FULL:
                // Segue a data completa, sem alteracoes
                break;
            case MONTH_YEAR:
                String dateMonth = CustomDateUtil.getMonthFromDate(formatedDate);
                String dateYear = CustomDateUtil.getYearFromDate(formatedDate);
                formatedDate = dateMonth + "/" + dateYear;
                break;
            case MONTH:
                formatedDate = CustomDateUtil.getMonthFromDate(formatedDate);
                break;
            case YEAR:
                formatedDate = CustomDateUtil.getYearFromDate(formatedDate);
                break;
            default:
                // Segue a data completa, sem alteracoes
                break;
        }

        if (null != getmSelectionCallBack()) {
            getmSelectionCallBack().onDateSelected(formatedDate);
        }
        return formatedDate;
    }


    private OnClickListener dateTextFieldListener() {

        return new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getDialogType() == FULL) {
                    ((DatePickerDialog) mDatePickerDialog).setMinDate(getMinDate());
                    ((DatePickerDialog) mDatePickerDialog).setMaxDate(getMaxDate());
                }
                mDatePickerDialog.show(getFragmentManager(), TAG + "_" + getId());
            }
        };

    }

    private void initializeValues() {
        setFocusable(false);
        setInputType(InputType.TYPE_NULL);
        setOnClickListener(dateTextFieldListener());
        initializeDialog(getDialogType());
        this.mDatePickerDialog.registerCallback(CustomDateTextField.this);
    }

    private void initializeDialog(int mDialogType) {

        switch (mDialogType) {
            case FULL:
                this.mDatePickerDialog = new DatePickerDialog();
                break;
            case MONTH_YEAR:
                this.mDatePickerDialog = new MonthYearPickerDialog();
                break;
            case MONTH:
                this.mDatePickerDialog = new MonthYearPickerDialog();
                ((MonthYearPickerDialog) mDatePickerDialog).setOnlyMonth(true);
                break;
            case YEAR:
                this.mDatePickerDialog = new MonthYearPickerDialog();
                ((MonthYearPickerDialog) mDatePickerDialog).setOnlyYear(true);
                break;
            default:
                this.mDatePickerDialog = new DatePickerDialog();
                break;
        }
    }

    private void getDialogTypeAttr(Context context, AttributeSet attributeSet) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attributeSet,
                R.styleable.DateTextField,
                0, 0);

        try {
            setDialogType(typedArray.getInteger(R.styleable.DateTextField_dialogType, FULL));
        } finally {
            typedArray.recycle();
        }

    }


    public FragmentManager getFragmentManager() {
        return mFragmentManager;
    }

    public void setFragmentManager(FragmentManager mFragmentManager) {
        this.mFragmentManager = mFragmentManager;
    }

    public Long getMinDate() {
        return mMinDate;
    }

    /**
     * Set the selectable minimum date for a date picker.
     *
     * @param mMinDate The minimum date in milliseconds.
     */
    public void setMinDate(Long mMinDate) {
        this.mMinDate = mMinDate;
    }


    public Long getMaxDate() {
        return mMaxDate;
    }

    /**
     * Set the selectable maximum date for a date picker.
     *
     * @param mMaxDate The maximum date in milliseconds.
     */
    public void setMaxDate(Long mMaxDate) {
        this.mMaxDate = mMaxDate;
    }

    public IDateTextFieldCallBack getmSelectionCallBack() {
        return mSelectionCallBack;
    }

    public int getDialogType() {
        return mDialogType;
    }

    public void setDialogType(int mDialogType) {
        this.mDialogType = mDialogType;
    }


    public void setmSelectionCallBack(IDateTextFieldCallBack mSelectionCallBack) {
        this.mSelectionCallBack = mSelectionCallBack;
    }
}
