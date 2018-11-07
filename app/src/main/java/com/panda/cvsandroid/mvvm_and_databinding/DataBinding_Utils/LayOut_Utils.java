package com.panda.cvsandroid.mvvm_and_databinding.DataBinding_Utils;

import android.view.View;

public class LayOut_Utils
{
    public static int SetVisibility(Boolean b)
    {

        return b?View.VISIBLE:View.GONE;
    }
}
