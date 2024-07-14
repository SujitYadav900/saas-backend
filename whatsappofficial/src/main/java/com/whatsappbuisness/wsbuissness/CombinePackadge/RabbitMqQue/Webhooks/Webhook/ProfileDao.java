package com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.Webhooks.Webhook;
/*      
 Author=Supreet Singh
 Date= 08/02/21 6:23 PM
*/

public class ProfileDao {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProfileDao{" +
                "name='" + name + '\'' +
                '}';
    }

    private String name;

}
