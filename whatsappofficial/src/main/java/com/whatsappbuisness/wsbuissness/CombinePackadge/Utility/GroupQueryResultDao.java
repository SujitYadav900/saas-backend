package com.whatsappbuisness.wsbuissness.CombinePackadge.Utility;

public class GroupQueryResultDao<T,T1,T3,T4,T5>{
    @Override
    public String toString() {
        return "GroupQueryResultDao{" +
                "name=" + name +
                ", count=" + count +
                ", sessionStartTime=" + sessionStartTime +
                ", sessionEndTime=" + sessionEndTime +
                ", sessionType=" + sessionType +
                '}';
    }


    public T getName() {
        return name;
    }

    public void setName(T name) {
        this.name = name;
    }

    public T1 getCount() {
        return count;
    }

    public void setCount(T1 count) {
        this.count = count;
    }

    private T name;
    private T1 count;

    private T3 sessionStartTime;
    private T4 sessionEndTime;
    private T5 sessionType;

    public T3 getSessionStartTime() {
        return sessionStartTime;
    }

    public void setSessionStartTime(T3 sessionStartTime) {
        this.sessionStartTime = sessionStartTime;
    }

    public T4 getSessionEndTime() {
        return sessionEndTime;
    }

    public void setSessionEndTime(T4 sessionEndTime) {
        this.sessionEndTime = sessionEndTime;
    }

    public T5 getSessionType() {
        return sessionType;
    }

    public void setSessionType(T5 sessionType) {
        this.sessionType = sessionType;
    }
}
