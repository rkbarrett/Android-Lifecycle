package com.msi.ekb015.androidlifecycle;

import java.util.Date;

/**
 * Class holds information about Android lifecycle action.
 *
 * Created by ekb015 on 4/24/2015.
 */
public class LifecycleInfo {
    private String mMethodName;
    private String mTimeStamp;

    public LifecycleInfo(String methodName, String timeStamp) {
        mMethodName = methodName;
        mTimeStamp = timeStamp;
    }

    public String getMethodName() {
        return mMethodName;
    }

    public String getTimeStamp() {
        return mTimeStamp;
    }
}