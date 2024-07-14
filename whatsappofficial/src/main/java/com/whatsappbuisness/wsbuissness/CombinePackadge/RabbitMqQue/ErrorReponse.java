package com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue;

public class ErrorReponse{
    @Override
    public String toString() {
        return "Error{" +
                "code=" + code +
                ", title='" + title + '\'' +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private int code;
    private String title;
}
