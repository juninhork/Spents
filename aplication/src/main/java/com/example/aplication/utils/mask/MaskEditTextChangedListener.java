package com.example.aplication.utils.mask;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aplication.utils.Utils;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by juniorbraga on 28/03/17.
 */

public class MaskEditTextChangedListener implements TextWatcher {

    private static MaskEditTextChangedListener instance;

    private String mMask;
    private EditText mEditText;
    private TextView mTextView;
    private Set<String> symbolMask = new HashSet<String>();
    private boolean isUpdating;
    private String old = "";
    private boolean mIsTextView;

    public MaskEditTextChangedListener(String mask, EditText editText) {
        this.mMask = mask;
        this.mEditText = editText;
        this.initSymbolMask();
        this.mIsTextView = false;
    }

    public MaskEditTextChangedListener(String mask, TextView editText) {
        this.mMask = mask;
        this.mTextView = editText;
        this.initSymbolMask();
        this.mIsTextView = true;
    }


    private void initSymbolMask() {
        for (int i = 0; i < mMask.length(); i++) {
            char ch = mMask.charAt(i);
            if (ch != '#')
                this.symbolMask.add(String.valueOf(ch));
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String str = Utils.unmask(s.toString(), this.symbolMask);
        String mascara = "";

        if (this.isUpdating) {
            this.old = str;
            this.isUpdating = false;
            return;
        }

        if (str.length() > this.old.length())
            mascara = Utils.mask(this.mMask, str);
        else
            mascara = s.toString();

        this.isUpdating = true;

        if (!this.mIsTextView) {
            this.mEditText.setText(mascara);
            this.mEditText.setSelection(mascara.length());
        } else {
            this.mTextView.setText(mascara);
//            mTextView.setSelection(mascara.length());
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }


    @Override
    public void afterTextChanged(Editable s) {

    }
}