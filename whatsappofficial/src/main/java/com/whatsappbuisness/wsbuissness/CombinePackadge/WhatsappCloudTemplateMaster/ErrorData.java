package com.whatsappbuisness.wsbuissness.CombinePackadge.WhatsappCloudTemplateMaster;

import java.io.Serializable;

public class ErrorData implements Serializable {
        private String messaging_product;
        private String details;

    public String getMessaging_product() {
        return messaging_product;
    }

    public void setMessaging_product(String messaging_product) {
        this.messaging_product = messaging_product;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "ErrorData{" +
                "messaging_product='" + messaging_product + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
