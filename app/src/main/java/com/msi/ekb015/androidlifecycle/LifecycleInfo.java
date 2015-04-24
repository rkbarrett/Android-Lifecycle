package com.msi.ekb015.androidlifecycle;

import java.util.Date;

/**
 * Class holds information about Android lifecycle action.
 *
 * Created by ekb015 on 4/24/2015.
 */
public class LifecycleInfo {
    private String mMethodName;
    private Date mTimeStamp;

    public LifecycleInfo(String methodName, Date timeStamp) {
        mMethodName = methodName;
        mTimeStamp = timeStamp;
    }

    public String getMethodName() {
        return mMethodName;
    }

    public Date getTimeStamp() {
        return mTimeStamp;
    }
}
