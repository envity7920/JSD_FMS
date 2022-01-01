package com.FMS.app.file.controller;

import static spark.Spark.post;

import com.FMS.app.file.dao.SettingDao;
import com.FMS.app.file.model.Setting;

public class SettingController extends Controller {
  private static SettingDao settingDao = new SettingDao(Controller.connector);

  public static void load() {
    post("/update_settings", (req, res) -> {
      // TODO: update first setting
       Setting t =  new Setting(maxFileSize, itemPerPage, mimeTypeAllowed, lastUpdatedTime)

      res.redirect("/");
      return null;
    });
  }
}
