package com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.TokenServiceWs;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

/*
 Author=Supreet Singh
 Date= 10/03/21 10:52 AM
*/
@Document(collection = "TokenService")
public class TokenServiceDao {
    @Override
    public String toString() {
        return "TokenServiceDao{" +
                "id=" + id +
                ", accessToken='" + accessToken + '\'' +
                ", expired=" + expired +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpired() {
        return expired;
    }

    public void setExpired(int expired) {
        this.expired = expired;
    }

    @Id
    private long id;
    private String accessToken;
    private int expired;
}
