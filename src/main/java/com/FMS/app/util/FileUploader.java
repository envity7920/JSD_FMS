package com.FMS.app.util;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;

import spark.Request;

public class FileUploader {
  private static final String location = "public";
  private static final long maxFileSize = 100000000;
  private static final long maxRequestSize = 100000000;
  private static final int fileSizeThreshold = 1024;
  private MultipartConfigElement multipartConfigElement;

  public FileUploader() {
    this.multipartConfigElement = new MultipartConfigElement(
        location, maxFileSize, maxRequestSize, fileSizeThreshold);
  }

  public void configMultipart(Request req) {
    req.raw().setAttribute("org.eclipse.jetty.multipartConfig", multipartConfigElement);
  }

  public String storeFile(Part file) {
    String url = location + "/" + file.getSubmittedFileName();

    try (final InputStream in = file.getInputStream()) {
      Path out = Paths.get(url);
      Files.copy(in, out);
      file.delete();
    } catch (Exception e) {
      System.err.println("[ERROR] " + e.getMessage());
    }

    return url;
  }
}
