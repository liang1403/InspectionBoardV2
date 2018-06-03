package com.example.demo.service.base;

import com.example.demo.domain.Identified;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IEntityService<T extends Identified, PK extends Serializable> {

    T instantiateByJSON(Map<String, Object> entityFields);

    T create(T entity);

    T get(PK id);

    boolean update(PK id, Map<String, Object> changedFields);

    boolean delete(PK id);

    List<T> getList();

    Page<T> getPage(Pageable pageable);
}