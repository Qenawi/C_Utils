package com.panda.cvsandroid.mvvm_and_databinding;

import android.app.Application;
import android.databinding.DataBindingUtil;

import com.panda.cvsandroid.mvvm_and_databinding.databinding.AppDataBindingComponent;

public class App extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();
        DataBindingUtil.setDefaultComponent(new AppDataBindingComponent());
    }
}