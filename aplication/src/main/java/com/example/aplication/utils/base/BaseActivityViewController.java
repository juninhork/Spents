package com.example.aplication.utils.base;

import android.app.Activity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.aplication.R;
import com.example.aplication.utils.progressdialog.ProgresDialogUtil;
import com.example.aplication.utils.resultservice.IResponseSevice;
import com.example.aplication.utils.resultservice.ResponseFragment;
import com.example.aplication.utils.resultservice.ResponseService;

import static android.content.Context.INPUT_METHOD_SERVICE;

public abstract class BaseActivityViewController <T extends Activity> {

    protected T activity;
    public ProgresDialogUtil mProgresDialogUtil;

    protected BaseActivityViewController(T activity){
        this.activity = activity;
        this.mProgresDialogUtil = new ProgresDialogUtil(activity);
    }

    public void sucessServiceView(FragmentTransaction ft, ResponseService response, IResponseSevice iResponseSevice) {
        ft.replace(R.id.success_service, ResponseFragment.newInstance(response,iResponseSevice));
        ft.commit();
    }

    public void hideSoftKeyBoard() {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);

        if(imm.isAcceptingText()) { // verify if the soft keyboard is open
            imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

}

