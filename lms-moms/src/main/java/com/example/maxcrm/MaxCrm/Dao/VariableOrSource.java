package com.example.maxcrm.MaxCrm.Dao;

public class VariableOrSource {
    @Override
    public String toString() {
        return "VariableOrSource{" +
                "name='" + name + '\'' +
                ", status=" + status +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    private String name;
    private byte status;

}
