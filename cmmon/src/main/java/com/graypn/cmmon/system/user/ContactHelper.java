package com.graypn.cmmon.system.user;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.text.TextUtils;

import com.graypn.cmmon.system.user.bean.CallRecord;
import com.graypn.cmmon.system.user.bean.ContactInfo;
import com.graypn.cmmon.system.user.bean.SmsInfo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户短信，通话记录，联系人帮助类
 * <p>
 * <uses-permission android:name="android.permission.READ_CALL_LOG"/>
 * <p>
 * <uses-permission android:name="android.permission.READ_SMS"/>
 */
public class ContactHelper {

    /**
     * 获取通讯录联系人
     */
    public static List<ContactInfo> getContacts(Context context) {
        if (context == null) {
            return null;
        }
        ContentResolver contactsResolver = context.getContentResolver();
        if (contactsResolver == null) {
            return null;
        }
        // 只检索姓名和电话号码
        String[] columns = new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER};
        Cursor cursor = contactsResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, columns, null, null, null);
        if (cursor == null) {
            return null;
        }
        List<ContactInfo> contactInfoList = new ArrayList<>();
        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            String number = cursor.getString(1);
            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(number)) {
                ContactInfo contactInfo = new ContactInfo(name, number);
                contactInfoList.add(contactInfo);
            }
        }
        cursor.close();
        return contactInfoList;
    }

    /**
     * 获取通话记录
     */
    public static List<CallRecord> getCallLogs(Context context) {
        if (context == null) {
            return null;
        }
        ContentResolver resolver = context.getContentResolver();
        if (resolver == null) {
            return null;
        }
        String[] columns = new String[]{
                CallLog.Calls.CACHED_NAME,
                CallLog.Calls.NUMBER,
                CallLog.Calls.TYPE,
                CallLog.Calls.DATE,
                CallLog.Calls.DURATION
        };
        Cursor cursor = resolver.query(CallLog.Calls.CONTENT_URI, columns, null, null, CallLog.Calls.DATE + " desc");
        if (cursor == null) {
            return null;
        }
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        List<CallRecord> recordList = new ArrayList<>();
        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            String number = cursor.getString(1);
            int type = cursor.getInt(2);
            long date = cursor.getLong(3);
            long duration = cursor.getLong(4);
            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(number)) {
                continue;
            }
            CallRecord record = new CallRecord();
            record.setName(name);
            record.setPhoneNum(number);
            record.setType(type);
            record.setDate(sdf.format(date));
            record.setDuration(duration);
            recordList.add(record);
        }
        cursor.close();
        return recordList;
    }

    /**
     * 获取短信记录
     */
    public static List<SmsInfo> getSmsInfo(Context context) {
        if (context == null) {
            return null;
        }
        ContentResolver resolver = context.getContentResolver();
        if (resolver == null) {
            return null;
        }

        final Uri SMS_URI = Uri.parse("content://sms/");
        final String[] COLUMNS = new String[]{
                "person",
                "address",
                "body",
                "date",
                "type"};
        Cursor cursor = resolver.query(SMS_URI, COLUMNS, null, null, "date desc");
        if (cursor == null) {
            return null;
        }
        List<SmsInfo> smsInfoList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            String phoneNum = cursor.getString(1);
            String content = cursor.getString(2);
            long date = cursor.getLong(3);
            int type = cursor.getInt(4);

            SmsInfo smsinfo = new SmsInfo(content, phoneNum, sdf.format(date), name, type);
            smsInfoList.add(smsinfo);
        }
        cursor.close();
        return smsInfoList;
    }
}
