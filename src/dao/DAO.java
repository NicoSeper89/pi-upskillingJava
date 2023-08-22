package dao;

import java.util.List;

public interface DAO<T> {

    void insert(T t);

    void update(T t);

    void delete(T t);

    List<T> getAll();

    T getByID(Integer id);

}
