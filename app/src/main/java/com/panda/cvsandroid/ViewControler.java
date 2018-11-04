package com.panda.cvsandroid;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class ViewControler extends BaseObservable
{
    @Bindable
    public Boolean getShowProgressBar()
    {
        return ShowProgressBar;
    }
    public void setShowProgressBar(Boolean showProgressBar)
    {
        ShowProgressBar = showProgressBar;
        notifyPropertyChanged(BR.showProgressBar);
    }
    private Boolean ShowProgressBar;
}
