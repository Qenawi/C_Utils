package com.panda.cvsandroid.Cservice;

import android.text.TextUtils;

public class CService_Throwable extends Exception
{
    public CService_Throwable(Throwable throwable) {
        super(throwable);
    }
    public CService_Throwable(String msg) {
        super(msg);
    }
    public CService_Throwable(String msg,Throwable t) {
        super(msg,t);
    }
    private String ACtion =CServiceHelper.ActionString.Action_Default ;
    private int ErrorCode=CServiceHelper.ErrorCode.CODE_Default;
    public void setACtion(String ACtion)
    {
        if (ACtion!=null&&!TextUtils.isEmpty(ACtion))
        this.ACtion = ACtion;
    }
    public int getErrorCode()
    {
        return ErrorCode;
    }
    public void setErrorCode(int errorCode)
    {
        ErrorCode = errorCode;
    }
    public String getACtion(){ return ACtion; }
}
