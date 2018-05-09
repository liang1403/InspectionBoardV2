package com.example.demo.service.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public abstract class EntityServiceImpl<T, PK> {

    private JpaRepository<T, PK> repo;

    public EntityServiceImpl(JpaRepository<T, PK> repo) {
        this.repo = repo;
    }

    @Transactional
    public T save(T entity) {
        return repo.save(entity);
    }

    public T get(PK id) {
        return repo.getOne(id);
    }

    @Transactional
    public void delete(PK id) {
        repo.deleteById(id);
    }

    public List<T> list() {
        return repo.findAll();
    }
}