package com.FMS.app;

import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.FMS.app.file.dao.*;
import com.FMS.app.file.model.*;
import com.FMS.app.util.*;

public class App {
    public static void main(String[] args) {
        get("/", (req, res) -> {
            // sample database retrieve
            DbConnector connector = new DbConnector();
            FileDao fileDao = new FileDao(connector);
            Optional<File> xxx = fileDao.get(1);
            xxx.get().setFileName("bar.jpg");
            fileDao.update(xxx.get());
            try {
                List<File> f = fileDao.getAll();
                for (File l : f) {
                    System.out.println(l);
                }
            } catch (Exception e) {
                System.err.println("[ERROR] " + e.getMessage());
            }

            return "Hello Worldsddfsdf";
        });

        get("/sample_view", (req, res) -> {
            Map m = new HashMap();
            m.put("key", "value");
            // sample.hbs will be understand as src/main/resources/sample.hbs
            return new ModelAndView(m, "sample.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
