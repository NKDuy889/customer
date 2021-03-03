package service;

import java.util.List;

public interface IService<T> {
    List<T> findAll();
    T findById(int id);
    void update(T t,int id);
    void save(T t );
}
