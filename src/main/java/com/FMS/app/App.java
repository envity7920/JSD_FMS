package com.FMS.app;

import static spark.Spark.*;

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
    }
}