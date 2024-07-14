package com.whatsappbuisness.wsbuissness.CombinePackadge.WhatsappCloudTemplateMaster;



import java.util.List;

public class Example {
    private List<List<String>> body_text;
    private List<String> header_handle;

    public List<List<String>> getBody_text() {
        return body_text;
    }

    public void setBody_text(List<List<String>> body_text) {
        this.body_text = body_text;
    }

    public List<String> getHeader_handle() {
        return header_handle;
    }

    public void setHeader_handle(List<String> header_handle) {
        this.header_handle = header_handle;
    }

    @Override
    public String toString() {
        return "Example{" +
                "body_text=" + body_text +
                ", header_handle=" + header_handle +
                '}';
    }
}
