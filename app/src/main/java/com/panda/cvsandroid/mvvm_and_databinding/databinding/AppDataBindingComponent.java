package com.panda.cvsandroid.mvvm_and_databinding.databinding;

/**
 * Created by Gregory Rasmussen on 7/26/17.
 */
public class AppDataBindingComponent implements android.databinding.DataBindingComponent {
    @Override
    public RecyclerViewDataBinding getRecyclerViewDataBinding() {
        return new RecyclerViewDataBinding();
    }
}