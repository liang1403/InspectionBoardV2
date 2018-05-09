package com.example.demo.service.base;

import java.util.List;

public interface IEntityService<T, PK> {

    T save(T entity);

    T get(PK id);

    void delete(PK id);

    List<T> list();
}