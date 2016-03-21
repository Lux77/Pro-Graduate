package com.crown.shoppingonline.utils;

import android.util.Log;

/**
 * Created by Crown on 2016/3/21.
 */
public class LogHelper {

    private static final String LOG_PREFIX = "OnlineMall_";
    private static final int LOG_PREFIX_LENGTH = LOG_PREFIX.length();
    private static final int LOG_TAG_MAX_LENGTH = 23;

    public static String makeLogTag(String str) {
        if(str.length() > LOG_TAG_MAX_LENGTH - LOG_PREFIX_LENGTH) {
            return LOG_PREFIX + str.substring(0, LOG_TAG_MAX_LENGTH - LOG_PREFIX_LENGTH - 1);
        }
        return LOG_PREFIX + str;
    }

    public static String makeLogTag(Class cls) {
        return makeLogTag(cls.getSimpleName());
    }

    public static void i(String tag, Object... messages) {
        log(tag, Log.INFO, null, messages);
    }

    public static void log(String tag, int level, Throwable t, Object... messages) {
        if(Log.isLoggable(tag, level)) {
            String message;
            if(t == null && messages != null && messages.length == 1) {
                message = messages[0].toString();
            }
            else {
                StringBuilder sb = new StringBuilder();
                if(messages != null) {
                    for(Object msg : messages) {
                        sb.append(msg);
                    }
                }
                if(t != null) {
                    sb.append("\n").append(Log.getStackTraceString(t));
                }
                message = sb.toString();
            }
            Log.println(level, tag, message);
        }

    }
}
