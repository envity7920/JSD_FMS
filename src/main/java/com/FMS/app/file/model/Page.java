package com.FMS.app.file.model;

public class Page {
  private int index;
  private String url;
  private boolean active;

  public Page(int index) {
    this.index = index;
    this.url = "/?currentPage=" + index;
    this.active = false;
  }

  public int getIndex() {
    return index;
  }

  public String getUrl() {
    return url;
  }

  public boolean getActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  @Override
  public String toString() {
    return "Page: index=" + index + ", url=" + url;
  }
}