package com.android.luomeiji_driver.network;

public class GetCodeBean {

    /**
     * code : 1
     * result : 发送成功
     * message : 044849
     */

    private String code;
    private String result;
    private String message;

    public GetCodeBean(String code, String result, String message) {
        this.code = code;
        this.result = result;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "GetCodeBean{" +
                "code='" + code + '\'' +
                ", result='" + result + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
