package com.panda.cvsandroid;

import android.view.View;

public class BindingUtils
{

    public static String capitalize(String text) {
        return text.toUpperCase();
    }
    public static int SetVisibile(Boolean show)
    {
        return  show? View.VISIBLE:View.GONE;
    }
}
