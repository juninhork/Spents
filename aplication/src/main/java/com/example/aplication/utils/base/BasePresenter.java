package com.example.aplication.utils.base;

import android.content.Context;

import com.example.aplication.utils.progressdialog.ProgresDialogUtil;

/**
 * Created by juniorbraga on 16/03/17.
 */

public abstract class BasePresenter<T extends BaseView> {

    public ProgresDialogUtil mProgresDialogUtil;
    protected Context mContext;
    public T mView;

    protected BasePresenter(T view) {

        if (!(view instanceof Context)) {
            throw new RuntimeException("Your view must be instance of Context for this constructor");
        }

        this.mContext = (Context) view;
        this.mView = view;
        this.mProgresDialogUtil = new ProgresDialogUtil(mContext);
    }

    public BasePresenter(Context context, T view) {
        this.mContext = context;
        this.mView = view;
        this.mProgresDialogUtil = new ProgresDialogUtil(mContext);
    }

    public Context getContext(){
        return mContext;
    }
}