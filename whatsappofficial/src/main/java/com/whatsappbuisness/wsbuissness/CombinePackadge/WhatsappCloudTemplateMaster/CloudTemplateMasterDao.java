package com.whatsappbuisness.wsbuissness.CombinePackadge.WhatsappCloudTemplateMaster;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

@Document(collection = "CloudTemplateMasterDao")
public class CloudTemplateMasterDao implements Serializable {
    @Id
    private String id;
    private String name;
    private String language;
    private String category;
    private long accountId;
    private String status;
    private String createDate;
    private String updateDate;
    private int dateFilter;
    private CloudErrorObj error;

    public CloudErrorObj getError() {
        return error;
    }

    public void setError(CloudErrorObj error) {
        this.error = error;
    }

    private MultipartFile multipartFile;

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public int getDateFilter() {
        return dateFilter;
    }

    public void setDateFilter(int dateFilter) {
        this.dateFilter = dateFilter;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private List<TemplateComponents> components;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<TemplateComponents> getComponents() {
        return components;
    }

    public void setComponents(List<TemplateComponents> components) {
        this.components = components;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "CloudTemplateMasterDao{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", language='" + language + '\'' +
                ", category='" + category + '\'' +
                ", accountId=" + accountId +
                ", status='" + status + '\'' +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", dateFilter=" + dateFilter +
                ", error=" + error +
                ", multipartFile=" + multipartFile +
                ", components=" + components +
                '}';
    }
}
