package com.smartwave.taskr.core;

/**
 * Created by smartwavedev on 6/16/16.
 */
public class TSingleton {

    public static String logoutGmail;

    public static String getLogoutGmail() {
        return logoutGmail;
    }

    public static void setLogoutGmail(String logoutGmail) {
        TSingleton.logoutGmail = logoutGmail;
    }
}
