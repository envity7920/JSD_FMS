package com.FMS.app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
  public final String DATABASE_NAME = "fms";
  public final String USERNAME = "root";
  public final String PASSWORD = "Toan7920@";
  public final String URL_MYSQL = "jdbc:mysql://localhost:3306/" + DATABASE_NAME;
  private Connection conn;

  public DbConnector() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("[INFO] Creating DB Connection....");
    } catch (Exception e) {
      System.err.println("[ERROR] " + e.toString());
    }
  }

  public Connection getConnection() {
    try {
      conn = DriverManager.getConnection(URL_MYSQL, USERNAME, PASSWORD);
    } catch (Exception e) {
      System.err.println("[ERROR] " + e.getMessage());
    }
    return conn;
  }

  public void closeConnection() {
    try {
      if (this.conn != null) {
        this.conn.close();
      }
    } catch (SQLException e) {
      System.err.println("[ERROR] " + e.getMessage());
    }
  }
}
