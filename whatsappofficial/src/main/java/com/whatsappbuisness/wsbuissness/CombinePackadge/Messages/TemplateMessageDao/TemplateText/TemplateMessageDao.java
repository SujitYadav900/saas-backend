package com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TemplateMessageDao.TemplateText;
/*
 Author=Supreet Singh
 Date= 04/02/21 5:18 PM
*/

import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TemplateMessageDao.ComponentsDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TemplateMessageDao.LanguageDao;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.io.Serializable;
import java.util.List;
@Document(collection = "Template")
public class TemplateMessageDao implements Serializable {


  public String getNamespace() {
    return namespace;
  }

  public void setNamespace(String namespace) {
    this.namespace = namespace;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LanguageDao getLanguage() {
    return language;
  }

  public void setLanguage(LanguageDao language) {
    this.language = language;
  }

  public List<ComponentsDao> getComponents() {
    return components;
  }

  public void setComponents(List<ComponentsDao> components) {
    this.components = components;
  }

  @Override
  public String toString() {
    return "TemplateMessageDao{" +
            "id='" + id + '\'' +
            ", status=" + status +
            ", category='" + category + '\'' +
            ", namespace='" + namespace + '\'' +
            ", name='" + name + '\'' +
            ", language=" + language +
            ", components=" + components +
            ", application='" + application + '\'' +
            ", accountId=" + accountId +
            ", templateBodyText='" + templateBodyText + '\'' +
            '}';
  }

  @Id
  private String id;
  private boolean status;
  private String category;

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  private String namespace;
    private String name;
    private LanguageDao language;
    private List<ComponentsDao> components;

  public String getApplication() {
    return application;
  }

  public void setApplication(String application) {
    this.application = application;
  }

  private String application;

  public long getAccountId() {
    return accountId;
  }

  public void setAccountId(long accountId) {
    this.accountId = accountId;
  }

  private long accountId;

  private String templateBodyText;

  public String getTemplateBodyText() {
    return templateBodyText;
  }

  public void setTemplateBodyText(String templateBodyText) {
    this.templateBodyText = templateBodyText;
  }
}
