package com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.Health;
/*      
 Author=Supreet Singh
 Date= 12/02/21 3:10 PM
*/

public class HealthInnerDao {
    @Override
    public String toString() {
        return "HealthInnerDao{" +
                "gateway_status='" + gateway_status + '\'' +
                '}';
    }

    public String getGateway_status() {
        return gateway_status;
    }

    public void setGateway_status(String gateway_status) {
        this.gateway_status = gateway_status;
    }

    private String gateway_status;
}
