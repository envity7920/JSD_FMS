package com.FMS.app.file.controller;

import static spark.Spark.post;

import java.sql.Timestamp;

import com.FMS.app.file.dao.SettingDao;
import com.FMS.app.file.model.Setting;

public class SettingController extends Controller {
  private static SettingDao settingDao = new SettingDao(Controller.connector);

  public static void load() {
    post("/update_settings", (req, res) -> {
      // TODO: update first setting
      String newMaxFileSize;
      String newItemPerPage;
      String newAllowedUploadType;

      newMaxFileSize = req.queryParams("maxFileSizeInput");
      newItemPerPage = req.queryParams("itemPerPageInput");
      newAllowedUploadType = req.queryParams("selectedMimeType");

      Setting t = new Setting(Integer.parseInt(newMaxFileSize), Integer.parseInt(newItemPerPage), newAllowedUploadType, new Timestamp(System.currentTimeMillis()));
      settingDao.save(t);
      res.redirect("/");
      return res;
    });
  }
}
