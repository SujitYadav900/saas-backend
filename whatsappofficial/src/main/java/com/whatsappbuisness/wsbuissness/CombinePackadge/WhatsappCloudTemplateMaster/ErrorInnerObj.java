package com.whatsappbuisness.wsbuissness.CombinePackadge.WhatsappCloudTemplateMaster;

import java.io.Serializable;

public class ErrorInnerObj implements Serializable {

    private String message;
    private String type;
    private long code;
    private long error_subcode;
    private String fbtrace_id;
    private ErrorData error_data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public long getError_subcode() {
        return error_subcode;
    }

    public void setError_subcode(long error_subcode) {
        this.error_subcode = error_subcode;
    }

    public String getFbtrace_id() {
        return fbtrace_id;
    }

    public void setFbtrace_id(String fbtrace_id) {
        this.fbtrace_id = fbtrace_id;
    }

    public ErrorData getError_data() {
        return error_data;
    }

    public void setError_data(ErrorData error_data) {
        this.error_data = error_data;
    }

    @Override
    public String toString() {
        return "ErrorInnerObj{" +
                "message='" + message + '\'' +
                ", type='" + type + '\'' +
                ", code=" + code +
                ", error_subcode=" + error_subcode +
                ", fbtrace_id='" + fbtrace_id + '\'' +
                ", error_data=" + error_data +
                '}';
    }
}
