package com.FMS.app.file.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
  List<T> getAll();

  Optional<T> get(int id, String string, String string);

  void save(T t);

  void update(T t);

  void delete(T t);
}
