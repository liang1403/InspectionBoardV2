package com.example.demo.controller;

import com.example.demo.service.base.IEntityService;
import com.mysql.jdbc.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

@Controller
public class EntityController<T> {

    private IEntityService entityService;

    private Class<T> persistentClass;

    public EntityController(IEntityService entityService) {
        this.entityService = entityService;
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private String getEntityName() {
        return this.persistentClass.getSimpleName();
    }

    @GetMapping(value = {"/{id}", ""})
    public String get(@PathVariable(name = "id", required = false) String id, Model model) throws IllegalAccessException, InstantiationException {
        T enrollee = id != null ? (T)entityService.get(id) : this.persistentClass.newInstance();
        model.addAttribute(this.getEntityName(), enrollee);
        return this.getEntityName();

    }

    @GetMapping("/list")
    public String getGridPage() {
        return this.getEntityName() + "Grid";
    }

    @GetMapping("/entities")
    public @ResponseBody
    Page<T> list(@RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "rows", required = false) Integer rows,
                        @RequestParam(value = "sidx", required = false) String sidx,
                        @RequestParam(value = "sord", required = false) String sord) {
        Sort.Direction direction = Sort.Direction.fromString(sord);
        String column = !StringUtils.isNullOrEmpty(sidx) ? sidx : "lastname";
        Sort sort = Sort.by(direction, column);
        return entityService.getPage(PageRequest.of(page, rows, sort));
    }

    @PostMapping
    public @ResponseBody
    String create(@RequestBody T entity) {
        entityService.create(entity);
        return ""; //"redirect:/" + ENROLLEE_VIEW + "/" + enrollee.getId();
    }

    @PutMapping(value = "/{id}")
    public @ResponseBody
    String update(@PathVariable("id") String id, @RequestBody Map<String, Object> changedFields) {
        if (entityService.update(id, changedFields)) {
            return String.format("%s has been updated successfully.", this.getEntityName());
        } else {
            return String.format("No %s for ID " + id + " found.", this.getEntityName());
        }
    }

    @DeleteMapping("/{id}")
    public @ResponseBody
    String delete(@PathVariable("id") String id) {
        if (entityService.delete(id)) {
            return String.format("%s has been deleted successfully.", this.getEntityName());
        } else {
            return String.format("No %s for ID " + id + " found.", this.getEntityName());
        }
    }
}
