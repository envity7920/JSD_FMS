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
      String mimeTypeAllowed) {
    this.maxFileSize = maxFileSize;
    this.itemPerPage = itemPerPage;
    this.mimeTypeAllowed = mimeTypeAllowed;
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

  public void setId(int id) {
    if (id >= 0) {
      this.id = id;
    }
  }

  public void setMaxFileSize(int maxFileSize) {
    this.maxFileSize = maxFileSize;
  }

  public void setItemPerPage(int itemPerPage) {
    this.itemPerPage = itemPerPage;
  }

  public void setAllowedUploadType(String mimeTypeAllowed) {
    this.mimeTypeAllowed = mimeTypeAllowed;
  }

  @Override
  public String toString() {
    return "Setting: maxSize=" + maxFileSize + ", itemPerPage=" + itemPerPage + ", mimeType=" + mimeTypeAllowed;
  }
}
