package com.example.aplication.utils.resultservice;

public class ResponseService {

    private String msg;
    private boolean isSuccess;
    private boolean statusRetorno;


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
}
