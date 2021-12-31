package com.FMS.app.file.dao;

import com.FMS.app.file.model.Setting;
import com.FMS.app.util.DbConnector;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Statement;
import java.sql.ResultSet;

public class SettingDAO implements Dao<Setting>{
    private Connection conn;

    public SettingDAO(DbConnector connector) {
      this.conn = connector.getConnection();
    }

    @Override
    public List<Setting> getAll() {
        List<Setting> settings = new ArrayList<>();
        try {
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from setting");

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
        return null;
    }

    @Override
    public void save(Setting t) {
        // TODO Auto-generated method stub
    }

    @Override
    public void update(Setting t) {
        // TODO Auto-generated method stub
    }

    @Override
    public void delete(Setting t) {
        // TODO Auto-generated method stub
    }
}
