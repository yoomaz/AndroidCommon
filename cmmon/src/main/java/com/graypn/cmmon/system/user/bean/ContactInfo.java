package com.graypn.cmmon.system.user.bean;

/**
 * Created by ZhuLei on 2017/12/1.
 * Email: zhuleineuq@gmail.com
 */
public class ContactInfo {

    private String mName;
    private String mPhoneNumer;

    public ContactInfo() {
    }

    public ContactInfo(String name, String phoneNumer) {
        mName = name;
        mPhoneNumer = phoneNumer;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPhoneNumer() {
        return mPhoneNumer;
    }

    public void setPhoneNumer(String phoneNumer) {
        mPhoneNumer = phoneNumer;
    }
}
