package com.whatsappbuisness.wsbuissness.CombinePackadge.MediaDb;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Tbl_Media_Dao" )
public class MediaDbDao {
    @Override
    public String toString() {
        return "MediaDbDao{" +
                "id='" + id + '\'' +
                ", dateFilter=" + dateFilter +
                ", date='" + date + '\'' +
                ", filename='" + filename + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDateFilter() {
        return dateFilter;
    }

    public void setDateFilter(int dateFilter) {
        this.dateFilter = dateFilter;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Id
    private String id;
    private int dateFilter;
    private String date;
    private String filename;

}
