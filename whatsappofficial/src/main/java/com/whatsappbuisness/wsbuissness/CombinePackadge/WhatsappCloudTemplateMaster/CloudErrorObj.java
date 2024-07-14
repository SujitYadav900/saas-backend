package com.whatsappbuisness.wsbuissness.CombinePackadge.WhatsappCloudTemplateMaster;

import java.io.Serializable;

public class CloudErrorObj implements Serializable {
    private ErrorInnerObj error;

    public ErrorInnerObj getError() {
        return error;
    }

    public void setError(ErrorInnerObj error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "CloudErrorObj{" +
                "error=" + error +
                '}';
    }
}
