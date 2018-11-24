package com.panda.cvsandroid.mvvm_and_databinding.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;

import com.panda.cvsandroid.models.Moviex;

public class DataItemViewModel extends BaseObservable
{
    private Moviex dataModel;

    public DataItemViewModel(Moviex dataModel) {
        this.dataModel = dataModel;
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
        return !TextUtils.isEmpty(dataModel.getTitle()) ? dataModel.getTitle() : "";
    }
}