package com.panda.cvsandroid.c_anmiations_utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.drawable.GradientDrawable;
import android.view.View;

/**
 * main Role Set Of Functions To Apply Selected Animation
 */
class CAnmiationExecuter
{
     static void HideView(final View view, final CAnmiation.CaCallBack callBack)
     {
         final ValueAnimator valueAnimator = ValueAnimator.ofObject(new ArgbEvaluator(),
                 0xFFFFFFFF,
                 0xff78c5f9);
         final GradientDrawable background = (GradientDrawable) view.getBackground();



        view.animate()
                .translationY(0)
                .alpha(0.0f)
                .setListener(new AnimatorListenerAdapter() {
                                 @Override
                                 public void onAnimationEnd(Animator animation) {
                                     super.onAnimationEnd(animation);
                                     view.setVisibility(View.GONE);
                                     callBack.OnAnmiationFinish();
                                 }
                             }
                );
    }

     static void ShowView(final View view, final CAnmiation.CaCallBack callBack)
    {
        view.animate()
                .translationY(0)
                .alpha(1f)
                .setDuration(350)
                .setListener(new AnimatorListenerAdapter() {
                                 @Override
                                 public void onAnimationEnd(Animator animation)
                                 {
                                     super.onAnimationEnd(animation);
                                     view.setVisibility(View.VISIBLE);
                                     callBack.OnAnmiationFinish();
                                 }
                             }
                );
    }
}
