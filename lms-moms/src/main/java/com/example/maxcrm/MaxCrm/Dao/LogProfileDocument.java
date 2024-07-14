package com.example.maxcrm.MaxCrm.Dao;

import org.springframework.data.mongodb.core.index.Indexed;

import javax.persistence.Id;

public class LogProfileDocument {
    @Id
    private String id;
    private String message;
    private String url;
    private String icon;
    private String dateTime;
    private String subject;
    @Indexed
    private int userId;

}
