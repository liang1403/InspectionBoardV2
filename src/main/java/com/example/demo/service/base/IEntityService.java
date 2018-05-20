package com.example.demo.service.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IEntityService<T, PK> {

    T create(T entity);

    T get(PK id);

    boolean update(PK id, Map<String, Object> changedFields);

    boolean delete(PK id);

    List<T> list();

    Page<T> getPage(Pageable pageable);
}