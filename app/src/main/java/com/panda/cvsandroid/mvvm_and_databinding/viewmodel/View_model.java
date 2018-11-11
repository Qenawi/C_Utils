package com.panda.cvsandroid.mvvm_and_databinding.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.panda.cvsandroid.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class View_model extends ViewModel {
    public List<Movie> getData() {
        return data;
    }

    public void setData(List<Movie> data) {
        this.data = data;
    }

    private List<Movie> data;
    private List<String> data2 = new ArrayList<String>() {
        {
            add("Qenawi0");
            add("Qenawi1");
            add("Qenawi2");
            add("Qenawi3");
            add("Qenawi4");
        }
    };

    public List<String> getData2() {
        return data2;
    }

    public void setData2(List<String> data2) {
        this.data2 = data2;
    }
}
