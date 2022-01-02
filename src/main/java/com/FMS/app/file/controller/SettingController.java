package com.FMS.app.file.controller;

import static spark.Spark.post;

import java.util.Optional;

import com.FMS.app.file.dao.SettingDao;
import com.FMS.app.file.model.Setting;

public class SettingController extends Controller {
  private static SettingDao settingDao = new SettingDao(Controller.connector);

  public static void load() {
    post("/update_settings", (req, res) -> {
      String maxFileSize = req.queryParams("maxFileSize");
      String itemPerPage = req.queryParams("itemPerPage");
      String allowedUploadType = req.queryParams("allowedUploadType");
      Optional<Setting> setting = settingDao.get(1);
      if (setting != null) {
        setting.get().setMaxFileSize(Integer.valueOf(maxFileSize));
        setting.get().setItemPerPage(Integer.valueOf(itemPerPage));
        setting.get().setAllowedUploadType(allowedUploadType);
        settingDao.update(setting.get());
      } else {
        settingDao.save(
          new Setting(Integer.parseInt(maxFileSize), Integer.parseInt(itemPerPage), allowedUploadType));
      }

      res.redirect("/");
      return res;
    });
  }
}
