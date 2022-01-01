package com.FMS.app.file.controller;

import java.util.ArrayList;
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
import static spark.Spark.delete;

import com.FMS.app.file.dao.FileDao;
import com.FMS.app.file.model.File;
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
        String fileUrl = uploader.storeFile(part);
        // TODO: fix deprecated convert long to int
        File f = new File(part.getSubmittedFileName(), fileUrl, new Long(part.getSize()).intValue(),
            part.getContentType());
        fileDao.save(f);
      }

      res.redirect("/");
      return null;
    });

    delete("/delete", (req, res) -> {
      res.redirect("/");
      return null;
    });
  }
}
