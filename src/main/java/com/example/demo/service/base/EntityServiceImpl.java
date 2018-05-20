package com.example.demo.service.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Transactional
public abstract class EntityServiceImpl<T, PK> implements IEntityService<T, PK> {

    protected JpaRepository<T, PK> repo;

    private Class<T> persistentClass;

    public EntityServiceImpl(JpaRepository<T, PK> repo) {
        this.repo = repo;
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Transactional
    public T create(T entity) {
        return repo.saveAndFlush(entity);
    }

    public T get(PK id) {
        return repo.findById(id).orElse(null);
    }

    @Transactional
    public boolean update(PK id, Map<String, Object> changedFields) {
        T entity = this.get(id);
        boolean entityExists = Objects.nonNull(entity);
        if (entityExists) {
            this.setFieldsFromJSON(entity, changedFields);
            repo.saveAndFlush(entity);
        }
        return entityExists;
    }

    @Transactional
    public boolean delete(PK id) {
        T entity = this.get(id);
        boolean entityExists = Objects.nonNull(entity);
        if (entityExists) {
            repo.deleteById(id);
        }
        return entityExists;
    }

    public Page<T> getPage(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public List<T> list() {
        return repo.findAll();
    }

    private void setFieldsFromJSON(T entity, Map<String, Object> changedFields) {
        changedFields.forEach((fieldName, value) -> {
            try {
                Field field = this.persistentClass.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(entity, value);
            } catch (NoSuchFieldException | IllegalAccessException ignored) {}
        });
    }
}