package com.example.demo.service.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IEntityService<T, PK> {

    T create(T entity);

    T get(PK id);

    boolean exists(PK id);

    boolean update(PK id, T entity);

    boolean delete(PK id);

    List<T> list();

    Page<T> getPage(Pageable pageable);
}