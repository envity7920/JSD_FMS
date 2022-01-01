package com.FMS.app;

import com.FMS.app.file.controller.FileController;
import com.FMS.app.file.controller.SettingController;

public class App {
    public static void main(String[] args) {
        FileController.load();
        SettingController.load();
    }
}
