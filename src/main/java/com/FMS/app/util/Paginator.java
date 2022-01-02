package com.FMS.app.util;

import java.util.ArrayList;
import java.util.List;

import com.FMS.app.file.model.Page;

public class Paginator {
  private List<Page> pages;
  private int limit = 3; // item per page
  private int currentPage;
  private int total;

  public Paginator(int limit, int currentPage, int total) {
    this.pages = new ArrayList<>();
    this.limit = limit;
    this.currentPage = currentPage;
    this.total = total;
  }

  public List<Page> genPages() {
    for (int i = 1; i <= total / limit + 1; i++) {
      Page page = new Page(i);

      if (i == currentPage) {
        page.setActive(true);
      }

      if (i * limit <= total) {
        pages.add(page);
      }
    }

    return pages;
  }
}
