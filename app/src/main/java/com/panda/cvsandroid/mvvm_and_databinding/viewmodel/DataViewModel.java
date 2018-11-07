package com.panda.cvsandroid.mvvm_and_databinding.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.panda.cvsandroid.BR;
import com.panda.cvsandroid.models.Movie;
import com.panda.cvsandroid.mvvm_and_databinding.adapter.DataAdapter;

import java.util.ArrayList;
import java.util.List;

public class DataViewModel extends BaseObservable
{
    private static final String TAG = "DataViewModel";
    private DataAdapter adapter;
    private List<Movie> data = new ArrayList<Movie>();

    @Bindable
    public Boolean getLockLayout() {
        return LockLayout;
    }

    public void setLockLayout(Boolean lockLayout)
    {
        LockLayout = lockLayout;
        notifyPropertyChanged(BR.lockLayout);
    }
    private Boolean LockLayout = false;

    public DataViewModel() {
        data = new ArrayList<>();
        adapter = new DataAdapter();
    }

    public void setUp(ArrayList<Movie> tempo)
    {
        // perform set up tasks, such as adding listeners, data population, etc.
        if (tempo != null && !tempo.isEmpty()) {
            this.data.clear();
            this.data.addAll(tempo);
        }
        populateData();
    }
    public void tearDown() {
        // perform tear down tasks, such as removing listeners
    }

    @Bindable
    public List<Movie> getData() {
        return this.data;
    }

    @Bindable
    public DataAdapter getAdapter() {
        return this.adapter;
    }

    private void populateData()
    {
        notifyPropertyChanged(BR.data);
    }
}