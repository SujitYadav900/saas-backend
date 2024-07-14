package com.example.maxcrm.MaxCrm.Dao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Tbl_UrlAccess", uniqueConstraints={@UniqueConstraint(name = "unqpagename",columnNames = "pageName")})
public class UrlAccessDao {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getPageDesc() {
        return pageDesc;
    }

    public void setPageDesc(String pageDesc) {
        this.pageDesc = pageDesc;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPageIcon() {
        return pageIcon;
    }

    public void setPageIcon(String pageIcon) {
        this.pageIcon = pageIcon;
    }

    public byte getIndex() {
        return indexSeq;
    }

    public void setIndex(byte index) {
        this.indexSeq = index;
    }

    public byte getShowOnMenu() {
        return showOnMenu;
    }

    public void setShowOnMenu(byte showOnMenu) {
        this.showOnMenu = showOnMenu;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @Override
    public String toString() {
        return "UrlAccessDao{" +
                "id=" + id +
                ", pageName='" + pageName + '\'' +
                ", pageDesc='" + pageDesc + '\'' +
                ", status=" + status +
                ", url='" + url + '\'' +
                ", pageIcon='" + pageIcon + '\'' +
                ", index=" + indexSeq +
                ", showOnMenu=" + showOnMenu +
                ", roleName='" + roleName + '\'' +
                ", menuId=" + menuId +
                ", menuName='" + menuName + '\'' +
                '}';
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @NotBlank(message = "Page Name is mandatory")
    @Column(unique=true)
    private String pageName;
    private String pageDesc;
    private byte status;
    private String url;
    private String pageIcon;
    private byte indexSeq;
    private byte showOnMenu;
    private String roleName;
    private int menuId;


    @Transient
    private String menuName;


}
