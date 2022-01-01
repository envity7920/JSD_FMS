package com.FMS.app.file.model;

import java.sql.Date;

public class File {
  public final static String STATUS_REMOVED = "REMOVED";
  public final static String STATUS_VISIBLE = "VISIBLE";

  private int id;
  private String name;
  private String path;
  private int fileSize;
  private String mime;
  private int numberOfDownload;
  private int version;
  private String status;
  private Date createdDateTime;
  private String versionIds;

  public File(
      String name,
      String path,
      int fileSize,
      String mime) {
    this.name = name;
    this.path = path;
    this.fileSize = fileSize;
    this.mime = mime;
    this.numberOfDownload = 0;
    this.status = File.STATUS_VISIBLE;
    this.createdDateTime = new Date(System.currentTimeMillis());
  }

  public void setId(int id) {
    if (id >= 0) {
      this.id = id;
    }
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setFileName(String name) {
    if (name != null) {
      this.name = name;
    }
  }

  public String getPath() {
    return path;
  }

  public int getFileSize() {
    return fileSize;
  }

  public void setFileSize(int fileSize) {
    if (fileSize >= 0) {
      this.fileSize = fileSize;
    }
  }

  public String getMime() {
    return mime;
  }

  public int getNumberOfDownload() {
    return numberOfDownload;
  }

  public int getVersion() {
    return version;
  }

  public String getStatus() {
    return status;
  }

  public Date getCreatedDateTime() {
    return createdDateTime;
  }

  public String getVersions() {
    return versionIds;
  }

  @Override
  public String toString() {
    return "File: " + name + ", located at: " + path;
  }
}
