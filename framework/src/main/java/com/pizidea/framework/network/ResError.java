package com.pizidea.framework.network;

/**
 * <b>desc your class</b><br/>
 * Created by yflai on 2015/6/7.
 */
public abstract class ResError {

    private int errCode;
    private String errMsg;

    public ResError(){}

    public ResError(int errCode,String errMsg){
        this.errCode = errCode;
        this.errMsg = errMsg;
    }


    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    //
}
