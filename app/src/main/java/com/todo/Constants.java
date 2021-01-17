package com.todo;

public class Constants {
    // Notification Channel constants

    // Name of Notification Channel for verbose notifications of background work
    public static final CharSequence VERBOSE_NOTIFICATION_CHANNEL_NAME =
            "Verbose WorkManager Notifications";
    public static String VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION =
            "Shows notifications whenever task end";
    public static final CharSequence NOTIFICATION_TITLE = "Your task ends";
    public static final String CHANNEL_ID = "VERBOSE_NOTIFICATION" ;
    public static final int NOTIFICATION_ID = 1;


    public static final String KEY_TASK_TITLE = "Key Task Title";
}
