package com.FMS.app.file.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.PreparedStatement;


import com.FMS.app.file.model.Setting;
import com.FMS.app.util.DbConnector;

public class SettingDao implements Dao<Setting> {
  private Connection conn;

  public SettingDao(DbConnector connector) {
    this.conn = connector.getConnection();
  }

  @Override
  public List<Setting> getAll() {
    List<Setting> settings = new ArrayList<>();
    try {
      Statement stmt = this.conn.createStatement();
      ResultSet rs = stmt.executeQuery("select * from settings");

      while (rs.next()) {
        Setting setting = new Setting(
            rs.getInt(1),
            rs.getInt(2),
            rs.getInt(3),
            rs.getString(4),
            rs.getDate(5));
        settings.add(setting);
      }
    } catch (Exception e) {
      System.err.println("[ERROR] " + e.getMessage());
    }
    return settings;
  }

  @Override
  public Optional<Setting> get(int id) {
    // TODO Auto-generated method stub
    try {
      PreparedStatement stmt = conn.prepareStatement("select * from settings where id = ? limit 1;");
      stmt.setInt(1, id);

      ResultSet rs = stmt.executeQuery();
      rs.next();
      Setting setting = new Setting(
          rs.getInt(2),
          rs.getInt(3),
          rs.getString(4),
          rs.getDate(5));
      setting.setId(rs.getInt(1));

      return Optional.of(setting);
    } catch (Exception e) {
      //TODO: handle exception
      System.err.print("[ERROR] " + e.getMessage());
    }

    return Optional.of(null);
  }

  @Override
  public void save(Setting t) {
    // TODO Auto-generated method stub
    try {
      PreparedStatement stmt = conn.prepareStatement("insert into settings (maxFileSize, itemPerPage, mimTypeAllowed, lastUpdatedTime) values (?, ?, ?, ?);");
      stmt.setInt(1, t.getMaxFileSize());
      stmt.setInt(2, t.getItemPerPage());
      stmt.setString(3, t.getMimeTypeAllowed());
      stmt.setDate(4, t.getLastUpdatedTime());
      stmt.executeUpdate();

    } catch (Exception e) {
      //TODO: handle exception
      System.err.print("[ERROR] " + e.getMessage());
    }

  }

  @Override
  public void update(Setting t) {
    // TODO Auto-generated method stub
    try {
      PreparedStatement stmt = conn.prepareStatement(
        "update settings set maxFileSize = ?, itemPerPage = ?, mimTypeAllowed = ?, lastUpdatedTime = ? where id = ?"); 
        stmt.setInt(1, t.getMaxFileSize());
      stmt.setInt(2, t.getItemPerPage());
      stmt.setString(3, t.getMimeTypeAllowed());
      stmt.setDate(4, t.getLastUpdatedTime());
      stmt.setInt(5, t.getId());
      stmt.executeUpdate();
    } catch (Exception e) {
      //TODO: handle exception
      System.err.print("[ERROR] " + e.getMessage());
    
    }
  }

  @Override
  public void delete(Setting t) {
    // TODO Auto-generated method stub

    try {
      PreparedStatement stmt = conn.prepareStatement("delete from settings where id =  ?;");
      stmt.setInt(1, t.getId());
      stmt.executeUpdate();
    } catch (Exception e) {
      System.err.print("[ERROR] " + e.getMessage());
    }
  }

  @Override
  public Optional<Setting> get(int id, String string, String string2) {
    // TODO Auto-generated method stub
    return null;
  }
}
