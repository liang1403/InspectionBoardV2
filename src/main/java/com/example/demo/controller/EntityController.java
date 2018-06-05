package com.example.demo.controller;

import com.example.demo.domain.Identified;
import com.example.demo.domain.extension.GridPageRequest;
import com.example.demo.service.base.IEntityService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
public abstract class EntityController<T extends Identified> {

    private IEntityService entityService;

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
    Page<T> getPage(GridPageRequest pageRequest, final HttpServletRequest request) {
        return entityService.getPage(pageRequest);
    }

    @GetMapping("/list")
    public @ResponseBody
    List<T> getList() {
        return entityService.getList();
    }

    @PostMapping
    public @ResponseBody
    T create(@RequestBody Map<String, Object> entityFields) {
        return (T)entityService.instantiateByJSON(entityFields);
    }

    @PutMapping("/{id}")
    public @ResponseBody
    boolean update(@PathVariable("id") String id, @RequestBody Map<String, Object> changedFields) {
        return entityService.update(id, changedFields);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody
    boolean delete(@PathVariable("id") String id) {
        return entityService.delete(id);
    }
}
