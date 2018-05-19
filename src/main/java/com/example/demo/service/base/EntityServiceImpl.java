package com.example.demo.service.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public abstract class EntityServiceImpl<T, PK> implements IEntityService<T, PK> {

    protected JpaRepository<T, PK> repo;

    public EntityServiceImpl(JpaRepository<T, PK> repo) {
        this.repo = repo;
    }

    @Transactional
    public T create(T entity) {
        return repo.saveAndFlush(entity);
    }

    public T get(PK id) {
        return repo.getOne(id);
    }

    public boolean exists(PK id) {
        return repo.findById(id).isPresent();
    }

    @Transactional
    public boolean update(PK id, T entity) {
        boolean entityExists = exists(id);
        if(entityExists) {
            repo.saveAndFlush(entity);
        }
        return entityExists;
    }

    @Transactional
    public boolean delete(PK id) {
        boolean entityExists = exists(id);
        if(entityExists) {
            repo.deleteById(id);
        }
        return entityExists;
    }

    public List<T> list() {
        return repo.findAll();
    }

    public Page<T> getPage(Pageable pageable) {
        return repo.findAll(pageable);
    }
}