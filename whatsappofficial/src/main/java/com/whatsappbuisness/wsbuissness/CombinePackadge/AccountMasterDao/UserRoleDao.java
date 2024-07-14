package com.whatsappbuisness.wsbuissness.CombinePackadge.AccountMasterDao;

public class UserRoleDao {

    private long userId;
    private int roleId;

    public UserRoleDao(long userId, int roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public UserRoleDao() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserRoleDao{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }
}
