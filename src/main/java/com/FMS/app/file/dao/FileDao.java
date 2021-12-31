package com.FMS.app.file.dao;

import com.FMS.app.file.model.File;
import com.FMS.app.util.DbConnector;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Statement;
import java.sql.ResultSet;

public class FileDao implements Dao<File> {
  private Connection conn;

  public FileDao(DbConnector connector) {
    this.conn = connector.getConnection();
  }

  @Override
  public List<File> getAll() {
    List<File> files = new ArrayList<>();
    try {
      Statement stmt = this.conn.createStatement();
      ResultSet rs = stmt.executeQuery("select * from files");

      while (rs.next()) {
        File file = new File(
            rs.getInt(1),
            rs.getString(2),
            rs.getString(3),
            rs.getInt(4),
            rs.getString(5));
        files.add(file);
      }
    } catch (Exception e) {
      System.err.println("[ERROR] " + e.getMessage());
    }
    return files;
  }

  @Override
  public Optional<File> get(int id) {
    // TODO
    return null;
  }

  @Override
  public void save(File f) {
    // TODO
  }

  @Override
  public void update(File f) {
    // TODO
  }

  @Override
  public void delete(File f) {
    // TODO
  }
}
