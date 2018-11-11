package com.panda.cvsandroid.mvvm_and_databinding.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;

import com.panda.cvsandroid.models.Movie;

public class DataItemViewModel extends BaseObservable
{
    private Movie dataModel;

    public DataItemViewModel(Movie dataModel) {
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