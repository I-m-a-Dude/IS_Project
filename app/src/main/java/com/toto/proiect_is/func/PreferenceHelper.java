package com.toto.proiect_is.func;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {

    private static final String PREFERENCES_NAME = "MyAppPreferences";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_REMEMBER_ME = "rememberMe";

    private final SharedPreferences preferences;

    public PreferenceHelper(Context context) {
        preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public void saveCredentials(String username, String password, boolean rememberMe) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_PASSWORD, password);
        editor.putBoolean(KEY_REMEMBER_ME, rememberMe);
        editor.apply();
    }

    public String getUsername() {
        return preferences.getString(KEY_USERNAME, "");
    }

    public String getPassword() {
        return preferences.getString(KEY_PASSWORD, "");
    }

    public boolean getRememberMe() {
        return preferences.getBoolean(KEY_REMEMBER_ME, false);
    }
}
