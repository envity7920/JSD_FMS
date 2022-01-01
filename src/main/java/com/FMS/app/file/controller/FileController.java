package com.FMS.app.file.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;
import java.util.List;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.delete;

import com.FMS.app.file.dao.FileDao;
import com.FMS.app.file.model.File;

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
      String location = "image"; // the directory location where files will be stored
      long maxFileSize = 100000000; // the maximum size allowed for uploaded files
      long maxRequestSize = 100000000; // the maximum size allowed for multipart/form-data requests
      int fileSizeThreshold = 1024; // the size threshold after which files will be written to disk

      MultipartConfigElement multipartConfigElement = new MultipartConfigElement(
          location, maxFileSize, maxRequestSize, fileSizeThreshold);
      req.raw().setAttribute("org.eclipse.jetty.multipartConfig",
          multipartConfigElement);

      Collection<Part> parts = req.raw().getParts();
      for (Part part : parts) {
        System.out.println("Name: " + part.getName());
        System.out.println("Size: " + part.getSize());
        System.out.println("Filename: " + part.getSubmittedFileName());
      }

      String fName = req.raw().getPart("file").getSubmittedFileName(); // DIE HERE!
      System.out.println("Title: " + req.raw().getParameter("title"));
      System.out.println("File: " + fName);

      Part uploadedFile = req.raw().getPart("file");
      Path out = Paths.get("image/" + fName);
      try (final InputStream in = uploadedFile.getInputStream()) {
        Files.copy(in, out);
        uploadedFile.delete();
      }
      // cleanup
      multipartConfigElement = null;
      parts = null;
      uploadedFile = null;
      res.redirect("/");
      return null;
    });

    delete("/delete", (req, res) -> {
      res.redirect("/");
      return null;
    });
  }
}
