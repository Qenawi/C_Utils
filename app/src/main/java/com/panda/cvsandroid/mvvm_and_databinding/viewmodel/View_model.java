package com.panda.cvsandroid.mvvm_and_databinding.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.panda.cvsandroid.models.Movie;

import java.util.List;

public class View_model extends ViewModel
{
    public List<Movie> getData()
    {
        return data;
    }
    public void setData(List<Movie> data)
    {
        this.data = data;
    }
    private List<Movie> data ;
}
