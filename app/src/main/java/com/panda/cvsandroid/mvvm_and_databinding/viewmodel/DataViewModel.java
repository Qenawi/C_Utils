package com.panda.cvsandroid.mvvm_and_databinding.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.panda.cvsandroid.BR;
import com.panda.cvsandroid.models.Moviex;
import com.panda.cvsandroid.mvvm_and_databinding.adapter.DataAdapter;
import com.panda.cvsandroid.mvvm_and_databinding.adapter.DataAdapterStrings;

import java.util.ArrayList;
import java.util.List;

public class DataViewModel extends BaseObservable
{
    private static final String TAG = "DataViewModel";
    private DataAdapter adapter;
    private DataAdapterStrings adapter2;
    private List<Moviex> data ;
    private List<String>data2;

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

    public DataViewModel()
    {
        data = new ArrayList<>();
        adapter = new DataAdapter();

        adapter2=new DataAdapterStrings();
        data2=new ArrayList<>();
    }



    @Bindable
    public List<Moviex> getData() {
        return this.data;
    }
    @Bindable
    public DataAdapter getAdapter() {
        return this.adapter;
    }
    @Bindable
    public DataAdapterStrings getAdapter2()
    {
        return adapter2;
    }


    @Bindable
    public List<String> getData2() {
        return data2;
    }
    public void setData2(List<String> data2) {
        this.data2 = data2;
    }

    //-----------populate----------------------
    private void populateData()
    {
        // notfiy data Change
        notifyPropertyChanged(BR.data);
        notifyPropertyChanged(BR.data2);
    }
   //--life cycle aware------------
   public void setUp(ArrayList<Moviex> tempo,ArrayList<String>data2)
   {
       // perform set up tasks, such as adding listeners, data population, etc.
       if (tempo != null && !tempo.isEmpty())
       {
           this.data.clear();
           this.data.addAll(tempo);
       }
       if (data2!=null&&!data2.isEmpty())
       {
           this.data2.clear();
           this.data2.addAll(data2);
       }
       populateData();
   }
    public void tearDown() {
        // perform tear down tasks, such as removing listeners
    }
}