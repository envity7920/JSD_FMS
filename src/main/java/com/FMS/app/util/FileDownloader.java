package com.FMS.app.util;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.FMS.app.file.model.File;

import spark.Response;

public class FileDownloader {
  private static final String location = "public";
  private File file;
  private Response res;

  public FileDownloader(File file, Response res) {
    this.file = file;
    this.res = res;
  }

  private void configResponseHeaders() {
    res.header("Content-disposition", "attachment; filename = " + file.getName());
  }

  public void sendFile() {
    configResponseHeaders();
    try {
      OutputStream outStream = res.raw().getOutputStream();
      Path path = Paths.get(location + "/v" + file.getVersion() + "_" + file.getName());
      DataInputStream inStream = new DataInputStream(
          new FileInputStream(path.toString()));
      int nBytesToRead = inStream.available();
      if (nBytesToRead > 0) {
        byte[] bytes = new byte[nBytesToRead];
        inStream.read(bytes);
        outStream.write(bytes);
      }
      outStream.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
