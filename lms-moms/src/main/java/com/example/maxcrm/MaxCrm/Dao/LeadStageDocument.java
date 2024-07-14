package com.example.maxcrm.MaxCrm.Dao;

import org.springframework.data.mongodb.core.mapping.Document;


import javax.persistence.Id;
import java.util.List;
import java.util.Objects;
@Document(collection = "LeadStageDocument")
public class LeadStageDocument {


    public List<StageOptionDocument> getOption() {
        return option;
    }

    public void setOption(List<StageOptionDocument> option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return "LeadStageDocument{" +
                "id='" + id + '\'' +
                ", stage='" + stage + '\'' +
                ", option=" + option +
                '}';
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String stage;
    private List<StageOptionDocument> option;

//    @Override
//    public boolean equals(Object o) {
//
//        LeadStageDocument that = (LeadStageDocument) o;
//        System.out.println("Will compare "+that.getStage()+" to "+this.getStage() );
//        return that.getStage().equalsIgnoreCase(this.getStage());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, stage, option);
//    }
}
