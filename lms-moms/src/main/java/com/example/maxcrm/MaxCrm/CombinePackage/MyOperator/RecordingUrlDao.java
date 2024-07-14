package com.example.maxcrm.MaxCrm.CombinePackage.MyOperator;

public class RecordingUrlDao {

    private String status;
    private String code;
    private String url;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "RecordingUrlDao{" +
                "status='" + status + '\'' +
                ", code='" + code + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
