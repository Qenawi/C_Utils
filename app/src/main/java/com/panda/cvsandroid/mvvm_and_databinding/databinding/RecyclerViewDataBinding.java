package com.panda.cvsandroid.mvvm_and_databinding.databinding;

import android.databinding.BindingAdapter;
import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;

import com.panda.cvsandroid.mvvm_and_databinding.adapter.DataAdapter;
import com.panda.cvsandroid.mvvm_and_databinding.adapter.DataAdapterStrings;

import java.util.List;

public class RecyclerViewDataBinding
{
    /**
     * Binds the data to the {@link android.support.v7.widget.RecyclerView.Adapter}, sets the
     * recycler view on the adapter, and performs some other recycler view initialization.
     *
     * @param recyclerView passed in automatically with the data binding
     * @param adapter      must be explicitly passed in
     * @param data         must be explicitly passed in
     */
    @BindingAdapter({"app:adapter", "app:data"})
    public void bind(RecyclerView recyclerView, DataAdapter adapter, List<Movie> data)
    {
        recyclerView.setAdapter(adapter);
        adapter.updateData(data);
    }
    @BindingAdapter({"app:adapter", "app:data"})
    public void bind2(RecyclerView recyclerView, DataAdapterStrings adapter, List<String> data)
    {
        recyclerView.setAdapter(adapter);
        adapter.updateData(data);
    }
}