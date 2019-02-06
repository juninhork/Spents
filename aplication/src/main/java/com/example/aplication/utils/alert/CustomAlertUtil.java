package com.example.aplication.utils.alert;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.aplication.R;

/**
 * Created by juniorbraga on 17/03/17.
 */

public class CustomAlertUtil {

    private Context context;
    public static AlertDialog mAlertDialog;
    public static final DialogInterface.OnClickListener NO_LISTENER = null;

    public static void simpleAlert(Context context, String title, String menssage, String nameButon){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setTitle(title);
        dialogBuilder.setMessage(menssage);
        dialogBuilder.setPositiveButton(nameButon, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });

        mAlertDialog = dialogBuilder.create();
        mAlertDialog.show();
    }

    public static void simpleAlert(Context context, String title, String menssage, String nameButon, DialogInterface.OnClickListener onClickListener){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setTitle(title);
        dialogBuilder.setMessage(menssage);
        dialogBuilder.setPositiveButton(nameButon, onClickListener);

        mAlertDialog = dialogBuilder.create();
        mAlertDialog.show();
    }
    public static void customAlert(Context context, String title, String menssage, int image,
                                   String nameButon){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setTitle(title);
        dialogBuilder.setMessage(menssage);
        dialogBuilder.setIcon(image);
        dialogBuilder.setNegativeButton(nameButon, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        mAlertDialog = dialogBuilder.create();
        mAlertDialog.show();
    }

    public static void customAlert(Context context, String title, String menssage, int image,
                                   String nameButon, DialogInterface.OnClickListener mListener){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setTitle(title);
        dialogBuilder.setMessage(menssage);
        dialogBuilder.setIcon(image);
        dialogBuilder.setNegativeButton(nameButon, mListener);

        mAlertDialog = dialogBuilder.create();
        mAlertDialog.show();
    }

    public static void customAlert(Context context, String title, String menssage, int image,
                                   String namePositiveButton, String namenegativeButton,
                                   DialogInterface.OnClickListener mPositiveListener,
                                   DialogInterface.OnClickListener mNegativeListener){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setTitle(title);
        dialogBuilder.setMessage(menssage);
        dialogBuilder.setIcon(image);
        dialogBuilder.setPositiveButton(namePositiveButton, mPositiveListener);
        dialogBuilder.setNegativeButton(namenegativeButton, mNegativeListener);

        mAlertDialog = dialogBuilder.create();
        mAlertDialog.show();
    }



    public static void alertResponse(Context context , DialogType dialogType, String message,
                                     String positiveButtonLabel,
                                     DialogInterface.OnClickListener positiveListeners){
        switch (dialogType) {
            case ERROR_ALERT:
                customAlert(context,"Erro",message, R.mipmap.ic_menssage_erro,positiveButtonLabel,positiveListeners);
                break;
            case INFORMATION_ALERT:
                customAlert(context,"Informação",message, R.mipmap.ic_alert_menssage,positiveButtonLabel,positiveListeners);
                break;
            case SUCCESSFUL_ALERT:
                customAlert(context,"Sucesso",message, R.mipmap.ic_sucess,positiveButtonLabel,positiveListeners);
                break;
            case WARNING_ALERT_WITH_OK:
                customAlert(context,"Alerta",message, R.mipmap.ic_alert_menssage,positiveButtonLabel,positiveListeners);
                break;
        }
    }


    public static void alertResponse(Context context , DialogType dialogType, String message,
                                     String positiveButtonLabel, String negativeButtonLabel,
                                     DialogInterface.OnClickListener positiveListeners,
                                     DialogInterface.OnClickListener negativeListener){

        switch (dialogType) {
            case CONFIRMATION_ALERT:
                customAlert(context,"Alerta",message, R.mipmap.ic_alert_menssage,positiveButtonLabel,negativeButtonLabel,positiveListeners,negativeListener);
                break;
            case ERROR_ALERT:
                customAlert(context,"Erro",message, R.mipmap.ic_menssage_erro,positiveButtonLabel,positiveListeners);
                break;
            case INFORMATION_ALERT:
                customAlert(context,"Informação",message, R.mipmap.ic_alert_menssage,positiveButtonLabel,positiveListeners);
                break;
            case SUCCESSFUL_ALERT:
                customAlert(context,"Sucesso",message, R.mipmap.ic_sucess,positiveButtonLabel,positiveListeners);
                break;
            case WARNING_ALERT_WITH_OK:
                customAlert(context,"Alerta",message, R.mipmap.ic_alert_menssage,positiveButtonLabel,positiveListeners);
                break;
            default:
                customAlert(context,"Alerta",message, R.mipmap.ic_alert_menssage,positiveButtonLabel,negativeButtonLabel,positiveListeners,negativeListener);
                break;
        }
    }

    public enum DialogType {
        CONFIRMATION_ALERT,
        ERROR_ALERT,
        INFORMATION_ALERT,
        SUCCESSFUL_ALERT,
        WARNING_ALERT_WITH_OK,
    }


    public static AlertDialog getmAlertDialog() {
        return mAlertDialog;
    }

    public static void setmAlertDialog(AlertDialog mAlertDialog) {
        CustomAlertUtil.mAlertDialog = mAlertDialog;
    }
}
