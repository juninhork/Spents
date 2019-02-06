package com.example.aplication.utils.progressdialog;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by juniorbraga on 28/03/17.
 */

public class ProgresDialogUtil {
    private Context mContext;
    public ProgressDialog progress;

    public ProgresDialogUtil(Activity context){
        this.mContext = context;
    }

    public ProgresDialogUtil(Context context){
        this.mContext = context;
    }

    public void show(){
        createProgress("Aguarde","");
    }

    public void show(String title){
        createProgress(title,"");
    }

    public void show(String title,String menssage){
        createProgress(title,menssage);
    }

    public void dismiss(){
        if (this.progress != null && this.progress.isShowing()) {
            this.progress.dismiss();
        }
    }

    private void createProgress(String title,String menssage){
        if(this.progress !=null) {
            this.progress.setTitle(title);
            this.progress.setMessage(menssage);
            this.progress.show();
        }else{
            this.progress = new ProgressDialog(mContext);
            this.progress = ProgressDialog.show(mContext, title,
                    menssage, true);
        }
    }

    public ProgressDialog getProgress() {
        return progress;
    }

    public void setProgress(ProgressDialog progress) {
        this.progress = progress;
    }
}

