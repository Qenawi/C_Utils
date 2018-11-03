package com.panda.cvsandroid;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class CustomString extends BaseObservable
{
    public CustomString(String data, String url) {
        this.myText = data;
        this.profileImage = url;
    }

    @Bindable
    public String getMyText() {
        return myText;
    }

    public void setMyText(String myText) {
        this.myText = myText;
        notifyPropertyChanged(BR.myText);
    }

    @Bindable
    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage)
    {
        this.profileImage = profileImage;
        notifyPropertyChanged(BR.profileImage);

    }

    public void append(String data) {
        myText += data;
        notifyPropertyChanged(BR.myText);
    }

    private String myText;
    private String profileImage;

    @BindingAdapter({"android:profileImage"})
    public static void setImageUrl(ImageView view, String url) {
        if (!TextUtils.isEmpty(url))
            Glide.with(view.getContext()).load(url).into(view);
    }
}
