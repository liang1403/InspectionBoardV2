package com.example.demo.controller;

import com.example.demo.domain.Enrollee;
import com.example.demo.service.interfaces.IEnrolleeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.config.Constants.ENROLLEE_VIEW;

@Controller
@RequestMapping("/enrollee")
public class EnrolleeController {

    @Autowired
    private IEnrolleeService enrolleeService;

    @GetMapping(value = {"/{id}", ""})
    public String get(@PathVariable(name = "id", required = false) String id, Model model) {
        Enrollee enrollee = id != null ? enrolleeService.get(id) : new Enrollee();
        model.addAttribute("enrollee", enrollee);
        return ENROLLEE_VIEW;
    }

    @GetMapping("/list")
    public String getGridPage() {
        return "enrolleeGrid";
    }

    @GetMapping("/entities")
    public @ResponseBody
    Page<Enrollee> list(Pageable pageable) {
        return enrolleeService.getPage(pageable);
    }

    @PostMapping
    public String create(@ModelAttribute("enrollee") Enrollee enrollee) {
        enrolleeService.create(enrollee);
        return "redirect:/" + ENROLLEE_VIEW + "/" + enrollee.getId();
    }

    @PutMapping(value = "/{id}",  consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody
    String update(@PathVariable("id") String id, Enrollee enrollee) {
        if (enrolleeService.update(id, enrollee)) {
            return "Enrollee has been updated successfully.";
        } else {
            return "No Enrollee for ID " + id + " found.";
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody
    String delete(@PathVariable("id") String id) {
        if (enrolleeService.delete(id)) {
            return "Enrollee has been deleted successfully.";
        } else {
            return "No Enrollee for ID " + id + " found.";
        }
    }
}
