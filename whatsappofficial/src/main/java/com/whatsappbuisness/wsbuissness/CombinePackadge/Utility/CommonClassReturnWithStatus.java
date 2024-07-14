package com.whatsappbuisness.wsbuissness.CombinePackadge.Utility;

import java.io.Serializable;

/**
 * Use this class for return data that has to be further manupilated
 * on the basis of status
 * @param <T> data class
 * @param <T1> status enum class
 */
public class CommonClassReturnWithStatus<T,T1> implements Serializable {
    public CommonClassReturnWithStatus(T data, T1 status) {
        this.data = data;
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T1 getStatus() {
        return status;
    }

    public void setStatus(T1 status) {
        this.status = status;
    }

    private T data;
    private T1 status;

    @Override
    public String toString() {
        return "CommonClassReturnWithStatus{" +
                "data=" + data +
                ", status=" + status +
                '}';
    }
}
