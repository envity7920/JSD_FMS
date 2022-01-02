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
import com.FMS.app.file.model.File;
import com.FMS.app.util.FileDownloader;
import com.FMS.app.util.FileUploader;

public class FileController extends Controller {
  private static FileDao fileDao = new FileDao(Controller.connector);

  public static void load() {
    get("/", (req, res) -> {
      List<File> files = fileDao.getAll();
      Map<String, Object> viewData = new HashMap<>();
      viewData.put("files", files);

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
      // if (!file.isEmpty()) {
      //   fileDao.delete(file.get());
      // }
      res.redirect("/");
      return res;
    });
  }
}
