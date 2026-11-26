package dao;

import java.util.List;

public interface IDao<T> {
    void insert(T o);
    void update(T o);
    void delete(T o);
    T findById(Long id);
    List<T> findAll();
    void deleteAll();
}
