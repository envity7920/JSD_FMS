package com.FMS.app.file.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.Part;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.get;
import static spark.Spark.post;

import com.FMS.app.file.dao.FileDao;
import com.FMS.app.file.dao.SettingDao;
import com.FMS.app.file.model.File;
import com.FMS.app.file.model.Setting;
import com.FMS.app.util.FileDownloader;
import com.FMS.app.util.FileUploader;
import com.FMS.app.util.Paginator;

public class FileController extends Controller {
  private static FileDao fileDao = new FileDao(Controller.connector);
  private static SettingDao settingDao = new SettingDao(Controller.connector);

  public static void load() {
    get("/", (req, res) -> {
      String currentPage = req.queryParams("currentPage");
      Setting currentSetting = new Setting(50000000, 3, "all");
      Optional<Setting> setting = settingDao.get(1);

      if (setting != null) {
        currentSetting.setMaxFileSize(setting.get().getMaxFileSize());
        currentSetting.setItemPerPage(setting.get().getItemPerPage());
        currentSetting.setAllowedUploadType(setting.get().getMimeTypeAllowed());
      }

      if (currentPage == null) {
        currentPage = "1";
      }

      List<File> files = fileDao.getAll(
          currentSetting.getItemPerPage(),
          Integer.valueOf(currentPage),
          currentSetting.getMaxFileSize(),
          currentSetting.getMimeTypeAllowed());
      int total = fileDao.getTotalCount(
          currentSetting.getMaxFileSize(),
          currentSetting.getMimeTypeAllowed());
      Paginator paginator = new Paginator(
          currentSetting.getItemPerPage(),
          Integer.valueOf(currentPage),
          total);

      Map<String, Object> viewData = new HashMap<>();
      viewData.put("files", files);
      viewData.put("pages", paginator.genPages());
      viewData.put("setting", currentSetting);

      return new ModelAndView(viewData, "index.hbs");
    }, new HandlebarsTemplateEngine());

    post("/upload", "multipart/form-data", (req, res) -> {
      FileUploader uploader = new FileUploader();
      uploader.configMultipart(req);

      Collection<Part> parts = req.raw().getParts();
      for (Part part : parts) {
        String newFileName = part.getSubmittedFileName();
        int newFileVersion = 1;
        Optional<File> oldVersionFile = fileDao.get(newFileName);
        if (oldVersionFile != null) {
          newFileVersion = oldVersionFile.get().getVersion() + 1;
        }

        String fileUrl = uploader.storeFile(part, newFileVersion);
        File f = new File(part.getSubmittedFileName(), fileUrl, new Long(part.getSize()).intValue(),
            part.getContentType());
        f.setVersion(newFileVersion);
        fileDao.save(f);
      }

      res.redirect("/");
      return res;
    });

    get("/download", (req, res) -> {
      int fileId = Integer.valueOf(req.queryParams("fileId"));
      File file = fileDao.get(fileId).get();

      file.setNumberOfDownload(
          file.getNumberOfDownload() + 1);
      fileDao.update(file);

      FileDownloader downloader = new FileDownloader(file, res);
      downloader.sendFile();
      return res;
    });

    get("/delete", (req, res) -> {
      int fileId = Integer.valueOf(req.queryParams("fileId"));
      Optional<File> file = fileDao.get(fileId);
      if (!file.isEmpty()) {
        fileDao.delete(file.get());
      }
      res.redirect("/");
      return res;
    });
  }
}
