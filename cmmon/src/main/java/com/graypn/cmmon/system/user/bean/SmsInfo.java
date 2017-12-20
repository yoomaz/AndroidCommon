package com.graypn.cmmon.system.user.bean;

/**
 * 短信
 */
public class SmsInfo {

    private String content;
    private String phoneNumber;
    private String date;
    private String name;
    /**
     * 类型
     * 1:接收
     * 2:发出
     */
    private int type;

    public SmsInfo(String content, String phoneNumber, String date, String name, int type) {
        this.content = content;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.name = name;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SmsInfo{" +
                "content='" + content + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }


}
