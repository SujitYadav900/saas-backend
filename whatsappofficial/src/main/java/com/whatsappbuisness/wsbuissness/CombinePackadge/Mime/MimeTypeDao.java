package com.whatsappbuisness.wsbuissness.CombinePackadge.Mime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Tbl_MimeType" )
public class MimeTypeDao {
    @Override
    public String toString() {
        return "MimeType{" +
                "id='" + id + '\'' +
                ", mimeType='" + mimeType + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    @Id
    private String id;
    private String mimeType;
}
