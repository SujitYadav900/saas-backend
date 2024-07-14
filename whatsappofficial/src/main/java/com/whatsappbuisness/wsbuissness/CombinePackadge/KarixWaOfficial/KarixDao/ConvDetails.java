package com.whatsappbuisness.wsbuissness.CombinePackadge.KarixWaOfficial.KarixDao;
/*
 Author=Ritu Redhu
 Date= 06/01/23 10:00 AM
*/
public class ConvDetails {

    public String conversationType;
    public String isBillable;
    public String waConvId;

    public String getConversationType() {
        return conversationType;
    }

    public void setConversationType(String conversationType) {
        this.conversationType = conversationType;
    }

    public String getIsBillable() {
        return isBillable;
    }

    public void setIsBillable(String isBillable) {
        this.isBillable = isBillable;
    }

    public String getWaConvId() {
        return waConvId;
    }

    public void setWaConvId(String waConvId) {
        this.waConvId = waConvId;
    }

    @Override
    public String toString() {
        return "ConvDetails{" +
                "conversationType='" + conversationType + '\'' +
                ", isBillable='" + isBillable + '\'' +
                ", waConvId='" + waConvId + '\'' +
                '}';
    }
}
