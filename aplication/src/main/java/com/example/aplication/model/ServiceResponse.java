package com.example.aplication.model;

import java.util.Arrays;

public class ServiceResponse {
    private String msg;
    private boolean isSuccess;
    private boolean statusRetorno;
    public FieldError fieldErrors[];

    //    Custom Object Erro Response
    private String dtl;
    private boolean dtlIsNull;
    private boolean msgIsNull;
    private boolean isJcardError;
    private long status;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public boolean isStatusRetorno() {
        return statusRetorno;
    }

    public void setStatusRetorno(boolean statusRetorno) {
        this.statusRetorno = statusRetorno;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDtl() {
        return dtl;
    }

    public void setDtl(String dtl) {
        this.dtl = dtl;
    }

    public boolean isJcardError() {
        return isJcardError;
    }

    public void setJcardError(boolean jcardError) {
        isJcardError = jcardError;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public boolean isDtlIsNull() {
        return dtlIsNull;
    }

    public void setDtlIsNull(boolean dtlIsNull) {
        this.dtlIsNull = dtlIsNull;
    }

    public boolean isMsgIsNull() {
        return msgIsNull;
    }

    public void setMsgIsNull(boolean msgIsNull) {
        this.msgIsNull = msgIsNull;
    }

    @Override
    public String toString() {
        return "ServiceResponse{" +
                "msg='" + msg + '\'' +
                ", isSuccess=" + isSuccess +
                ", statusRetorno=" + statusRetorno +
                ", fieldErrors=" + Arrays.toString(fieldErrors) +
                ", dtl='" + dtl + '\'' +
                ", dtlIsNull=" + dtlIsNull +
                ", msgIsNull=" + msgIsNull +
                ", isJcardError=" + isJcardError +
                ", status=" + status +
                '}';
    }
}
