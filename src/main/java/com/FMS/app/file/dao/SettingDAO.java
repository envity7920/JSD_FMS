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
            rs.getInt(2),
            rs.getInt(3),
            rs.getString(4));
        setting.setId(rs.getInt(1));
        settings.add(setting);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return settings;
  }

  @Override
  public Optional<Setting> get(int id) {
    try {
      PreparedStatement stmt = conn.prepareStatement("select * from settings where id = ? limit 1;");
      stmt.setInt(1, id);

      ResultSet rs = stmt.executeQuery();
      rs.next();
      Setting setting = new Setting(
          rs.getInt(2),
          rs.getInt(3),
          rs.getString(4));
      setting.setId(rs.getInt(1));

      return Optional.of(setting);
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public void save(Setting t) {
    try {
      PreparedStatement stmt = conn.prepareStatement(
          "insert into settings (maxFileSize, itemPerPage, mimeTypeAllowed) values (?, ?, ?);");
      stmt.setInt(1, t.getMaxFileSize());
      stmt.setInt(2, t.getItemPerPage());
      stmt.setString(3, t.getMimeTypeAllowed());
      stmt.executeUpdate();

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public void update(Setting t) {
    try {
      PreparedStatement stmt = conn.prepareStatement(
          "update settings set maxFileSize = ?, itemPerPage = ?, mimeTypeAllowed = ? where id = ?;");
      stmt.setInt(1, t.getMaxFileSize());
      stmt.setInt(2, t.getItemPerPage());
      stmt.setString(3, t.getMimeTypeAllowed());
      stmt.setInt(4, t.getId());
      stmt.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void delete(Setting t) {
    try {
      PreparedStatement stmt = conn.prepareStatement("delete from settings where id =  ?;");
      stmt.setInt(1, t.getId());
      stmt.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
