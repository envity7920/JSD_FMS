package com.FMS.app.file.model;

import java.sql.Timestamp;

public class Setting {
  private int id;
  private int maxFileSize;
  private int itemPerPage;
  private String mimeTypeAllowed;
  private Timestamp lastUpdatedTime;

  public Setting(
      int maxFileSize,
      int itemPerPage,
      String mimeTypeAllowed,
      Timestamp lastUpdatedTime) {
    this.maxFileSize = maxFileSize;
    this.itemPerPage = itemPerPage;
    this.mimeTypeAllowed = mimeTypeAllowed;
    this.lastUpdatedTime = new Timestamp(System.currentTimeMillis());
  }

  public int getId() {
    return id;
  }

  public int getMaxFileSize() {
    return maxFileSize;
  }

  public int getItemPerPage() {
    return itemPerPage;
  }

  public String getMimeTypeAllowed() {
    return mimeTypeAllowed;
  }

  public Timestamp getLastUpdatedTime() {
    return lastUpdatedTime;
  }

  public void setId(int id) {
    if (id >= 0) {
      this.id = id;
    }
  }
}
