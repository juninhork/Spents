package com.example.aplication.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class CustomCurrencyTextWatcher implements TextWatcher {

    public final static Locale LOCALE_BRAZIL = new Locale("pt", "BR");

    protected String currentString = "";
    protected EditText mEditText;

    public CustomCurrencyTextWatcher(EditText mEditText) {
        this.mEditText = mEditText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {


        if (Utils.allNotNull(s, mEditText, currentString)) {
            if (!s.toString().equals(currentString) && !s.toString().equals("")) {
                mEditText.removeTextChangedListener(this);


                String cleanString = s.toString().replaceAll("([\\$]*)\\W", "");
                cleanString = cleanString.replaceAll("([aA-zZ]\\D*)", "");
                cleanString = cleanString.replaceAll(" ", "");

                if (!cleanString.isEmpty()) {
                    BigDecimal parsed = new BigDecimal(cleanString).setScale(2, BigDecimal.ROUND_FLOOR).divide(new BigDecimal(100), BigDecimal.ROUND_FLOOR);
                    String formatted = NumberFormat.getCurrencyInstance(LOCALE_BRAZIL).format(parsed);
                    currentString = formatted;
                } else {
                    currentString = "";
                }

                mEditText.setText(currentString);
                mEditText.setSelection(mEditText.getText().length());
                mEditText.addTextChangedListener(this);
            }
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
    }

}
