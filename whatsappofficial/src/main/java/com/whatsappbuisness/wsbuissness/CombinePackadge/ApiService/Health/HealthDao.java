package com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.Health;
/*      
 Author=Supreet Singh
 Date= 12/02/21 3:09 PM
*/

import com.google.gson.Gson;

public class HealthDao {
    @Override
    public String toString() {
        return "HealthDao{" +
                "health=" + health +
                '}';
    }

    public HealthInnerDao getHealth() {
        return health;
    }

    public void setHealth(HealthInnerDao health) {
        this.health = health;
    }

    private HealthInnerDao health;
    public HealthDao toObj(String json)
    {
        return new Gson().fromJson(json,HealthDao.class);
    }

}
