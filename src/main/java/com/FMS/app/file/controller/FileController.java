package com.FMS.app.file.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.Part;
import java.util.List;

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
      Map m = new HashMap();
      m.put("key", "value");

      // sample.hbs will be understand as src/main/resources/sample.hbs
      return new ModelAndView(m, "index.hbs");
    }, new HandlebarsTemplateEngine());

    get("/sample", (req, res) -> {
      Map m = new HashMap();
      m.put("pageNumber", "1");
      m.put("totalPages", "4");
      // sample.hbs will be understand as src/main/resources/sample.hbs
      return new ModelAndView(m, "sample.hbs");
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
