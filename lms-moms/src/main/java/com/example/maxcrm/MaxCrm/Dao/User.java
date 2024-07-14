package com.example.maxcrm.MaxCrm.Dao;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class User   implements UserDetails {
    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }



    private Long id;

    public int getDepIdFk() {
        return depIdFk;
    }

    public void setDepIdFk(int depIdFk) {
        this.depIdFk = depIdFk;
    }

    private int depIdFk;

    public Long getPlanIdFk() {
        return planIdFk;
    }

    public void setPlanIdFk(Long planIdFk) {
        this.planIdFk = planIdFk;
    }

    private Long planIdFk;

    public int getUserId() {
        return userId;
    }

    public void setUserId( int userId) {
        this.userId = userId;
    }

    private int userId;

    private String username;




    public double getDailytarget() {
        return dailytarget;
    }

    public void setDailytarget(double dailytarget) {
        this.dailytarget = dailytarget;
    }

    public double getMonthtarget() {
        return monthtarget;
    }

    public void setMonthtarget(double monthtarget) {
        this.monthtarget = monthtarget;
    }

    double dailytarget,monthtarget;
    public String fullName;
    private String password;

    public String getReportto() {
        return reportto;
    }

    public void setReportto(String reportto) {
        this.reportto = reportto;
    }

    private String reportto;

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    private String depName;

    public UserMasterDao getUserMasterDao() {
        return userMasterDao;
    }

    public void setUserMasterDao(UserMasterDao userMasterDao) {
        this.userMasterDao = userMasterDao;
    }

    private UserMasterDao userMasterDao;


    private boolean enabled;



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", planIdFk=" + planIdFk +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +

                ", enabled=" + enabled +

                ", isAccountNonLocked=" + isAccountNonLocked +
                ", isCredentialsNonExpired=" + isCredentialsNonExpired +
                ", roles=" + roles +
                '}';
    }





    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    public List<Role> getRoleli() {
        return roleli;
    }

    public void setRoleli(List<Role> roleli) {
        this.roleli = roleli;
    }

    public List<Role> roleli;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }


    private Collection<? extends GrantedAuthority> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
