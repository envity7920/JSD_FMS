package com.FMS.app.file.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.FMS.app.file.model.File;
import com.FMS.app.util.DbConnector;

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
      ResultSet rs = stmt.executeQuery("select * from files where status = '" + File.STATUS_VISIBLE + "';");

      while (rs.next()) {
        File file = new File(
            rs.getString(2),
            rs.getString(3),
            rs.getInt(4),
            rs.getString(5));
        file.setId(rs.getInt(1));
        file.setNumberOfDownload(rs.getInt(6));
        file.setVersion(rs.getInt(7));
        file.setStatus(rs.getString(8));
        file.setCreatedDateTime(rs.getTimestamp(9));
        file.setVersions(rs.getString(10));
        files.add(file);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return files;
  }

  public int getTotalCount(int maxSize, String allowedFileType) {
    try {
      Statement stmt = conn.createStatement();
      String sql = "select count(*) from files where status = '" + File.STATUS_VISIBLE + "'";
      int fileSize = maxSize * 1000000;
      sql += " and fileSize <= " + String.valueOf(fileSize);
      if (!allowedFileType.equals("*")) {
        sql += " and mime = '" + allowedFileType + "';";
      }
      ResultSet rs = stmt.executeQuery(sql);

      rs.next();
      return rs.getInt(1);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }

  public List<File> getAll(int limit, int currentPage, int maxSize, String allowedFileType) {
    List<File> files = new ArrayList<>();
    try {
      Statement stmt = conn.createStatement();
      int offset = 0;

      offset = limit * currentPage - limit;
      String sql = "select * from files where status = '" + File.STATUS_VISIBLE;
      int fileSize = maxSize * 1000000;
      sql += "' and fileSize <= " + String.valueOf(fileSize);
      if (!allowedFileType.equals("*")) {
        sql += " and mime = '" + allowedFileType + "'";
      }
      sql += " order by createdDateTime desc, name, version";
      sql += " limit " + limit + " offset " + offset + ";";
      System.out.println(sql);

      ResultSet rs = stmt.executeQuery(sql);

      while (rs.next()) {
        File file = new File(
            rs.getString(2),
            rs.getString(3),
            rs.getInt(4),
            rs.getString(5));
        file.setId(rs.getInt(1));
        file.setNumberOfDownload(rs.getInt(6));
        file.setVersion(rs.getInt(7));
        file.setStatus(rs.getString(8));
        file.setCreatedDateTime(rs.getTimestamp(9));
        file.setVersions(rs.getString(10));
        files.add(file);
      }
    } catch (Exception e) {
      e.printStackTrace();
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
      File file = new File(
          rs.getString(2),
          rs.getString(3),
          rs.getInt(4),
          rs.getString(5));
      file.setId(rs.getInt(1));
      file.setNumberOfDownload(rs.getInt(6));
      file.setVersion(rs.getInt(7));
      file.setStatus(rs.getString(8));
      file.setCreatedDateTime(rs.getTimestamp(9));
      file.setVersions(rs.getString(10));

      return Optional.of(file);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Optional.of(null);
  }

  public Optional<File> get(String name) {
    try {
      PreparedStatement stmt = conn
          .prepareStatement("select * from files where name = ? order by (version) desc limit 1;");
      stmt.setString(1, name);

      ResultSet rs = stmt.executeQuery();
      if (rs.next() == false) {
        return null;
      }
      File file = new File(
          rs.getString(2),
          rs.getString(3),
          rs.getInt(4),
          rs.getString(5));
      file.setId(rs.getInt(1));
      file.setNumberOfDownload(rs.getInt(6));
      file.setVersion(rs.getInt(7));
      file.setStatus(rs.getString(8));
      file.setCreatedDateTime(rs.getTimestamp(9));
      file.setVersions(rs.getString(10));

      return Optional.of(file);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Optional.of(null);
  }

  @Override
  public void save(File f) {
    try {
      PreparedStatement stmt = conn
          .prepareStatement("insert into files (name, path, fileSize, mime, version) values (?, ?, ?, ?, ?);");
      stmt.setString(1, f.getName());
      stmt.setString(2, f.getPath());
      stmt.setInt(3, f.getFileSize());
      stmt.setString(4, f.getMime());
      stmt.setInt(5, f.getVersion());

      stmt.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update(File f) {
    try {
      PreparedStatement stmt = conn
          .prepareStatement(
              "update files set name = ?, path = ?, fileSize = ?, mime = ?, numberOfDownload = ?, version = ?, status = ?, versionIds = ? where id = ?;");
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
      e.printStackTrace();
    }
  }

  @Override
  public void delete(File f) {
    try {
      PreparedStatement stmt = conn.prepareStatement("update files set status = ? where id =  ?;");
      stmt.setString(1, File.STATUS_REMOVED);
      stmt.setInt(2, f.getId());
      stmt.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
