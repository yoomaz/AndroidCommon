package com.graypn.cmmon.system.user.bean;

import android.provider.CallLog;

/**
 * Created by ZhuLei on 2017/12/1.
 * Email: zhuleineuq@gmail.com
 */

public class CallRecord {

    /**
     * Call log type for incoming calls.
     */
    public static final int INCOMING_TYPE = 1;
    /**
     * Call log type for outgoing calls.
     */
    public static final int OUTGOING_TYPE = 2;
    /**
     * Call log type for missed calls.
     */
    public static final int MISSED_TYPE = 3;

    private String mName;
    private String mPhoneNum;
    private int mType;
    /**
     * 通话日期
     */
    private String mDate;
    /**
     * 通话时长
     */
    private long mDuration;

    public CallRecord() {
    }

    public CallRecord(String name, String phoneNum, int type, String date, long duration) {
        mName = name;
        mPhoneNum = phoneNum;
        mType = type;
        mDate = date;
        mDuration = duration;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPhoneNum() {
        return mPhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        mPhoneNum = phoneNum;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        switch (type) {
            case CallLog.Calls.INCOMING_TYPE:
                mType = INCOMING_TYPE;
                break;
            case CallLog.Calls.OUTGOING_TYPE:
                mType = OUTGOING_TYPE;
                break;
            case CallLog.Calls.MISSED_TYPE:
                mType = MISSED_TYPE;
                break;
            default:
                mType = MISSED_TYPE;
        }
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public long getDuration() {
        return mDuration;
    }

    public void setDuration(long duration) {
        mDuration = duration;
    }
}
