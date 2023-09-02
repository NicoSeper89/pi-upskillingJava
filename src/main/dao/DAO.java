package main.dao;

import java.util.List;

public interface DAO<T> {

    //CRUD

    void insert(T t);

    void update(T t);

    void delete(Integer id);

    List<T> getAll();

    T getByID(Integer id);

}
