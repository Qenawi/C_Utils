package com.panda.cvsandroid.mvvm_and_databinding.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;

public class DataItemViewModel2 extends BaseObservable
{
    private String dataModel2;

    public DataItemViewModel2(String dataModel2)
    {
        this.dataModel2 = dataModel2;
    }

    public void setUp() {
        // perform set up tasks, such as adding listeners
    }

    public void tearDown() {
        // perform tear down tasks, such as removing listeners
    }

    @Bindable
    public String getTitle()
    {
        return !TextUtils.isEmpty(dataModel2) ? dataModel2 : "";
    }
}