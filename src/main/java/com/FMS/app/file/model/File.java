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
      int id,
      String name,
      String path,
      int fileSize,
      String mime) {
    this.id = id;
    this.name = name;
    this.path = path;
    this.fileSize = fileSize;
    this.mime = mime;
    this.numberOfDownload = 0;
    this.status = File.STATUS_VISIBLE;
    this.createdDateTime = new Date(System.currentTimeMillis());
  }

  public int getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getPath() {
    return this.path;
  }

  public int getFileSize() {
    return this.fileSize;
  }

  public String mime() {
    return this.mime;
  }

  public int getNumberOfDownload() {
    return this.numberOfDownload;
  }

  public int getVersion() {
    return this.version;
  }

  public String status() {
    return this.status;
  }

  public Date getCreatedDateTime() {
    return this.createdDateTime;
  }

  public String getVersions() {
    return this.versionIds;
  }

  public String toString() {
    return "File: " + this.name + ", located at: " + this.path;
  }
}
