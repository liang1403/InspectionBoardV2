package com.example.demo.controller;

import com.example.demo.domain.Identified;
import com.example.demo.service.base.IEntityService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.ParameterizedType;
import java.util.Map;
import java.util.Objects;

@Controller
public abstract class EntityController<T extends Identified> {

    protected IEntityService entityService;

    private Class<T> persistentClass;

    private String entityName;

    public EntityController(IEntityService entityService) {
        this.entityService = entityService;
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        this.entityName = this.persistentClass.getSimpleName().toLowerCase();
    }

    @GetMapping(value = {"", "/{id}"})
    public String get(@PathVariable(name = "id", required = false) String id, Model model)  {
        T entity = null;
        if(Objects.isNull(id)) {
            try {
                entity = this.persistentClass.newInstance();
            }
            catch (IllegalAccessException | InstantiationException ignored) {
                // TODO: Logging...
            }
        } else {
            entity = (T)entityService.get(id);
        }
        model.addAttribute(this.entityName, entity);
        return this.entityName;

    }

    @GetMapping("/entities")
    public @ResponseBody
    Page<T> list(GridPageRequest pageRequest) {
        return entityService.getPage(pageRequest);
    }

    @PostMapping
    public String add(T entity) {
        entityService.create(entity);
        return "redirect:/" + this.entityName + "/" + entity.getId();
    }

    @PostMapping("/create")
    public @ResponseBody
    T create(@RequestBody T entity) {
        return (T)entityService.create(entity);
    }

    @PutMapping("/update/{id}")
    public @ResponseBody
    boolean update(@PathVariable("id") String id, @RequestBody Map<String, Object> changedFields) {
        return entityService.update(id, changedFields);
    }

    @DeleteMapping("/delete/{id}")
    public @ResponseBody
    boolean delete(@PathVariable("id") String id) {
        return entityService.delete(id);
    }
}
