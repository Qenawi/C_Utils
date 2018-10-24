package com.panda.cvsandroid;

import android.content.Context;
import android.preference.PreferenceManager;

public class Shared_prefs {
    public static void save_Loged(String num, Context c) {
        PreferenceManager.getDefaultSharedPreferences(c).edit().putString("egyx", num).apply();
    }

    public static String get_loged(Context c) {
        return PreferenceManager.getDefaultSharedPreferences(c).getString("egyx", "out");
    }

    public static void save_id(int num, Context c) {
        PreferenceManager.getDefaultSharedPreferences(c).edit().putInt("asdawd", num).apply();
    }

    public static int get_id(Context c) {
        return PreferenceManager.getDefaultSharedPreferences(c).getInt("asdawd", 0);
    }

    //----------------------JsonShared-----------------------------
    public static String getProfileJson(Context c)
    {
        return PreferenceManager.getDefaultSharedPreferences(c).getString("Profile", "");
    }

    public static void saveProfileJson(Context c, String profileJson)
    {
        PreferenceManager.getDefaultSharedPreferences(c).edit().putString("Profile", profileJson).apply();
    }
}
