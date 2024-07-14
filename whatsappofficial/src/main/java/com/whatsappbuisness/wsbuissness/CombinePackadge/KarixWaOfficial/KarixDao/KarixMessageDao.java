package com.whatsappbuisness.wsbuissness.CombinePackadge.KarixWaOfficial.KarixDao;
/*
 Author=Ritu Redhu
 Date= 06/01/23 10:00 AM
*/
public class KarixMessageDao {

//        private String karixToken;

        private Message message;

        private MetaData metaData;


        public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

//    public String getKarixToken() {
//        return karixToken;
//    }
//
//    public void setKarixToken(String karixToken) {
//        this.karixToken = karixToken;
//    }


    @Override
    public String toString() {
        return "KarixMessageDao{" +
                "message=" + message +
                ", metaData=" + metaData +
                '}';
    }
}

