package com.FMS.app.file.dao;

import com.FMS.app.file.model.File;
import com.FMS.app.util.DbConnector;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Statement;
import java.sql.PreparedStatement;
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
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("select * from files");

      while (rs.next()) {
        File file = new File(
            rs.getString(2),
            rs.getString(3),
            rs.getInt(4),
            rs.getString(5));
        file.setId(rs.getInt(1));
        files.add(file);
      }
    } catch (Exception e) {
      System.err.println("[ERROR] " + e.getMessage());
    }
    return files;
  }

  @Override
  public Optional<File> get(int id) {
    try {
      PreparedStatement stmt = conn.prepareStatement("select * from files where id = ? limit 1;");
      stmt.setInt(1, id);

      ResultSet rs = stmt.executeQuery();
      rs.next();
      File f = new File(
          rs.getString(2),
          rs.getString(3),
          rs.getInt(4),
          rs.getString(5));
      f.setId(rs.getInt(1));

      return Optional.of(f);
    } catch (Exception e) {
      System.err.print("[ERROR] " + e.getMessage());
    }
    return Optional.of(null);
  }

  @Override
  public void save(File f) {
    try {
      PreparedStatement stmt = conn
          .prepareStatement("insert into files (name, path, fileSize, mime) values (?, ?, ?, ?);");
      stmt.setString(1, f.getName());
      stmt.setString(2, f.getPath());
      stmt.setInt(3, f.getFileSize());
      stmt.setString(4, f.getMime());
      stmt.executeUpdate();
    } catch (Exception e) {
      System.err.print("[ERROR] " + e.getMessage());
    }
  }

  @Override
  public void update(File f) {
    try {
      PreparedStatement stmt = conn
          .prepareStatement(
              "update files set name = ?, path = ?, fileSize = ?, mime = ?, numberOfDownload = ?, version = ?, status = ?, versionIds = ? where id = ?");
      stmt.setString(1, f.getName());
      stmt.setString(2, f.getPath());
      stmt.setInt(3, f.getFileSize());
      stmt.setString(4, f.getMime());
      stmt.setInt(5, f.getNumberOfDownload());
      stmt.setInt(6, f.getVersion());
      stmt.setString(7, f.getStatus());
      stmt.setString(8, f.getVersions());
      stmt.setInt(9, f.getId());
      stmt.executeUpdate();
    } catch (Exception e) {
      System.err.print("[ERROR] " + e.getMessage());
    }
  }

  @Override
  public void delete(File f) {
    try {
      PreparedStatement stmt = conn.prepareStatement("delete from files where id =  ?;");
      stmt.setInt(1, f.getId());
      stmt.executeUpdate();
    } catch (Exception e) {
      System.err.print("[ERROR] " + e.getMessage());
    }
  }

  @Override
  public Optional<File> get(int id, String string, String string2) {
    // TODO Auto-generated method stub
    return null;
  }
}
