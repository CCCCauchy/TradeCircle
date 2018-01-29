package com.xiaochui.tradecircle.data.model;


/**
 * @author Death丶Love
 * @project_name XiaoChuiProject
 * @package_name com.bocai.czeducation.models
 * @time 2017/8/29 14:09
 * @remark
 */

public class CommonNetModel<T> {
    private String ext;
    private int isEncrypt;
    private int minId;
    private int resultCode;
    private String timestamp;
    private T resultMap;
    private String resultMsg;

    public String getExt() {
        return ext;
    }

    public void String(String ext) {
        this.ext = ext;
    }

    public int getMinId() {
        return minId;
    }

    public void setMinId(int minId) {
        this.minId = minId;
    }

    public int getIsEncrypt() {
        return isEncrypt;
    }

    public void setIsEncrypt(int isEncrypt) {
        this.isEncrypt = isEncrypt;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public T getResultMap() {
        return resultMap;
    }

    public void setResultMap(T resultMap) {
        this.resultMap = resultMap;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
