package com.whatsappbuisness.wsbuissness.CombinePackadge.Messages;

public class ProviderDao {
    @Override
    public String toString() {
        return "ProviderDao{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
