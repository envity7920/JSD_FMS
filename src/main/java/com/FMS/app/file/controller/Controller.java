package com.FMS.app.file.controller;

import com.FMS.app.util.DbConnector;

public abstract class Controller {
  protected static DbConnector connector = new DbConnector();
}
