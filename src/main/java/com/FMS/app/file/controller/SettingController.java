package com.FMS.app.file.controller;

import static spark.Spark.post;


import com.FMS.app.file.dao.SettingDao;
import com.FMS.app.file.model.Setting;

public class SettingController extends Controller {
  private static SettingDao settingDao = new SettingDao(Controller.connector);

  public static void load() {
    post("/update_settings", (req, res) -> {
      String newMaxFileSize;
      String newItemPerPage;
      String newAllowedUploadType;

      newMaxFileSize = req.queryParams("maxFileSizeInput");
      newItemPerPage = req.queryParams("itemPerPageInput");
      newAllowedUploadType = req.queryParams("selectedMimeType");

      Setting setting = settingDao.get(0).get();

      if (setting != null) {
        // update setting
        setting.setMaxFileSize(Integer.parseInt(newMaxFileSize));
        setting.setItemPerPage(Integer.parseInt(newItemPerPage));
        setting.setAllowedUploadType(newAllowedUploadType);
        settingDao.save(setting);
      } else {
        settingDao.save(
          new Setting(Integer.parseInt(newMaxFileSize), Integer.parseInt(newItemPerPage), newAllowedUploadType));
        
      }

      res.redirect("/");
      return res;
    });
  }
}
