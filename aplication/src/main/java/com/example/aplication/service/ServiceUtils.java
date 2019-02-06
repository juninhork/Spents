package com.example.aplication.service;

import android.content.Context;
import android.content.DialogInterface;

import com.example.aplication.model.ServiceResponse;
import com.example.aplication.utils.alert.CustomAlertUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;

public class ServiceUtils {

    private static final ServiceUtils ourInstance = new ServiceUtils();

    private IService iService;

    public static ServiceUtils getInstance() {
        return ourInstance;
    }


    public void alertMsg(ResponseBody body, final Context context, DialogInterface.OnClickListener onClickListener) {
        if (body != null) {
            CustomAlertUtil.DialogType tipoAlertaEnum =  CustomAlertUtil.DialogType.ERROR_ALERT;

            ServiceResponse response = getresponse(body);
            if (!response.isMsgIsNull() && !response.isDtlIsNull()) {
                CustomAlertUtil.simpleAlert(context,response.getMsg(), response.getDtl(), "OK", onClickListener);
            } else if (!response.isMsgIsNull()) {
                if(response.getMsg().contains("sucesso"))
                    tipoAlertaEnum = CustomAlertUtil.DialogType.SUCCESSFUL_ALERT;

                CustomAlertUtil.alertResponse(context, tipoAlertaEnum, response.getMsg(), "OK", onClickListener);
            } else if (!response.isStatusRetorno()) {
                if (response.getStatus() == 403) {
                    if(getiService()!= null) {
                        this.getiService().errorService(true,false,false);
                    }
                }
            }
        }
    }

    public ServiceResponse getresponse(ResponseBody body) {
        ServiceResponse serviceResponse = new ServiceResponse();

        if (body != null) {
            try {
                JSONObject jsonObject = new JSONObject(body.string());

                if(!jsonObject.isNull("msg") && !jsonObject.isNull("DTL")) {
                    serviceResponse.setMsg(jsonObject.getString("msg"));
                    serviceResponse.setDtl(jsonObject.getString("DTL"));

                    return serviceResponse;
                }else if(!jsonObject.isNull("msg")) {
                    serviceResponse.setMsg(jsonObject.getString("msg"));
                    serviceResponse.setDtlIsNull(true);

                    return serviceResponse;
                } else if(!jsonObject.isNull("jcardError")){
                    serviceResponse.setJcardError(true);
                    serviceResponse.setMsgIsNull(true);
                    serviceResponse.setDtlIsNull(true);
                    return serviceResponse;
                } else{
                    ;
                    serviceResponse.setMsgIsNull(true);
                    serviceResponse.setDtlIsNull(true);
                    if(!jsonObject.isNull("status")) {
                        serviceResponse.setStatus(jsonObject.getLong("status"));
                        return serviceResponse;
                    }else{
                        serviceResponse.setStatusRetorno(true);
                        return serviceResponse;
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (JSONException ex) {
                ex.printStackTrace();
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return serviceResponse;
    }

    public void alertMsg(ResponseBody body, Context context) {
        alertMsg(body, context, null);
    }

    public void alertIfSocketException(Throwable t, final Context context) {
        t.printStackTrace();
        if(context==null) return;
        if (t instanceof java.net.SocketException) {
            if(this.getiService()!= null) {
                this.getiService().errorService(false,true,false);
            }
        }else if(t instanceof  java.net.SocketTimeoutException){

            if(this.getiService()!= null) {
                this.getiService().errorService(false,false,true);
            }
        }else{
            CustomAlertUtil.alertResponse(context, CustomAlertUtil.DialogType.INFORMATION_ALERT, "Aconteceu algum erro inesperado","OK",null);
        }
    }

    public IService getiService() {
        return iService;
    }

    public void setiService(IService iService) {
        this.iService = iService;
    }
}

