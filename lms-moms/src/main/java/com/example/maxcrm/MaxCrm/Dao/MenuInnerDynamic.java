package com.example.maxcrm.MaxCrm.Dao;

import com.google.gson.Gson;

public class MenuInnerDynamic {
    public String getPagename() {
        return pagename;
    }

    public void setPagename(String pagename) {
        this.pagename = pagename;
    }

    @Override
    public String toString() {
        return "MenuInnerDynamic{" +
                "pagename='" + pagename + '\'' +
                ", pagedesc='" + pagedesc + '\'' +
                ", rolename='" + rolename + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String getPagedesc() {
        return pagedesc;
    }

    public void setPagedesc(String pagedesc) {
        this.pagedesc = pagedesc;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //  SELECT GROUP_CONCAT(CONCAT('{pagename:"', Tbl_UrlAccess.pageName, '",pagedesc:"', Tbl_UrlAccess.pageDesc, '",rolename:"', Tbl_UrlAccess.roleName, '", url:"', Tbl_UrlAccess.url, '"}')) list, Tbl_Menu.menuName FROM Tbl_UrlAccess INNER JOIN Tbl_RolePage ON Tbl_UrlAccess.id = Tbl_RolePage.urlId LEFT JOIN Tbl_Menu ON Tbl_UrlAccess.menuId = Tbl_Menu.id WHERE Tbl_UrlAccess.status = 1 AND Tbl_UrlAccess.showOnMenu = 1 AND Tbl_Menu.status = 1 AND Tbl_RolePage.roleId = (SELECT Tbl_Role.id FROM Tbl_Role WHERE Tbl_Role.roleName = 'Admin') GROUP BY Tbl_Menu.menuName order by Tbl_Menu.menuName asc
    private String pagename;
    private String pagedesc;
    private String rolename;
    private String url;
  public   MenuInnerDynamic[] convertJsonToObject(String json) {
        return new Gson().fromJson(json, MenuInnerDynamic[].class);

    }

}
