package com.txtkm.txtkm.database;

import java.util.prefs.Preferences;

public class LoginPersistence {
    private final Preferences prefs;
    private final String secret = "Secret";
    private static LoginPersistence persistence;

    private LoginPersistence() {
        prefs = Preferences.userRoot().node(this.getClass().getName());
    }

    public static LoginPersistence getPersistence() {
        if (persistence == null) {
            persistence = new LoginPersistence();
        }
        return persistence;
    }

    public void setPrefs(String token) {
        removePrefs();
        prefs.put(secret, token);
    }

    public String getPrefs() {
        return prefs.get(secret, null);
    }

    public void removePrefs() {
        prefs.remove(secret);
    }
}
