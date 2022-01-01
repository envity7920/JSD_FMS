package com.FMS.app.file.model;

import java.sql.*;

public class Setting {
    private int id;
    private int maxFileSize;
    private int itemPerPage;
    private String mimeTypeAllowed;
    private Date lastUpdatedTime;

    public Setting(
      int id,
      int maxFileSize,
      int itemPerPage,
      String mimeTypeAllowed,
      Date lastUpdatedTime) {
    this.id = id;
    this.maxFileSize = maxFileSize;
    this.itemPerPage = itemPerPage;
    this.mimeTypeAllowed = mimeTypeAllowed;
    this.lastUpdatedTime = new Date(System.currentTimeMillis());
  }

  public Setting(
   
    int maxFileSize,
    int itemPerPage,
    String mimeTypeAllowed,
    Date lastUpdatedTime) {
  
  this.maxFileSize = maxFileSize;
  this.itemPerPage = itemPerPage;
  this.mimeTypeAllowed = mimeTypeAllowed;
  this.lastUpdatedTime = new Date(System.currentTimeMillis());
}


public int getId() {
  return this.id;
}

public int getMaxFileSize() {
  return this.maxFileSize;
}

public int getItemPerPage() {
  return this.itemPerPage;
}

public String getMimeTypeAllowed() {
  return this.mimeTypeAllowed;
}

public Date getLastUpdatedTime() {
  return this.lastUpdatedTime;
}

public void setId(int id) {
  if (id >= 0) {
    this.id = id;
  }
}
}
