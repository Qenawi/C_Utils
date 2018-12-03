package com.panda.cvsandroid.c_anmiations_utils;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.util.Log;
import android.view.View;


import io.reactivex.disposables.CompositeDisposable;

public class CAnmiation implements LifecycleObserver
{
    private Context context;
    private CompositeDisposable disposable;

    public CAnmiation(Context C) {
        this.context = C;
        disposable = new CompositeDisposable();
        if (C instanceof LifecycleOwner) {
            ((LifecycleOwner) C).getLifecycle().addObserver(this);

            Log.v("Life Cycle Yea", "ATTACHED");
        }
    }

    public interface CaCallBack
    {
        void OnAnmiationFinish();
    }

    public void AppllyAnmiation(int ANMIType, View view, CaCallBack callBack) {
        switch (ANMIType) {
            case CAnmiationHelper.HIDE_VIEW_GONE:
                CAnmiationExecuter.HideView(view,callBack);
                break;
            case CAnmiationHelper.SHOW_VIEW_VISIBLE:
                CAnmiationExecuter.ShowView(view,callBack);

                break;

        }

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onAttach()

    {
        Log.v("ATTACHED", "ATTACHED");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onDetech() {
        Log.v("Detached", "ATTACHED");
        /*
        view is not ready to handel api calls so cancel them
         */
      //  AlertDialogHandeler.DismissDialog(null);
        disposable.clear();
    }
}
