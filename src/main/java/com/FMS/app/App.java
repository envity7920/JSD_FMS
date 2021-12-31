package com.FMS.app;

import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.HashMap;
import java.util.Map;
import com.FMS.app.file.dao.*;
import com.FMS.app.file.model.*;
import com.FMS.app.util.*;

public class App {
    public static void main(String[] args) {
        get("/", (req, res) -> {
            // sample database retrieve
            DbConnector connector = new DbConnector();
            FileDao files = new FileDao(connector);
            files.getAll();
            try {
                File f = files.getAll().get(0);
                System.out.print(f);
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
