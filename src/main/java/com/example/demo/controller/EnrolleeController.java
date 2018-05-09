package com.example.demo.controller;

import com.example.demo.domain.Enrollee;
import com.example.demo.service.interfaces.IEnrolleeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/enrollee")
public class EnrolleeController {

    @Autowired
    private IEnrolleeService enrolleeService;

    @GetMapping
    public String init(Model model) {
        model.addAttribute("enrollee", new Enrollee());
        return "enrollee";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable("id") String id, Model model) {
        Enrollee enrollee = enrolleeService.get(id);
        model.addAttribute("enrollee", new Enrollee());
        return "enrollee";
    }

    @PostMapping
    public String save(Enrollee enrollee, Model model) {
        enrolleeService.save(enrollee);
        model.addAttribute("message", "Enrollee has been created successfully.");
        return "enrollee";
    }

    @PutMapping
    public String update(@RequestBody Enrollee enrollee, Model model) {
        enrolleeService.save(enrollee);
        model.addAttribute("message", "Enrollee has been updated successfully.");
        return "enrollee";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String id, Model model) {
        enrolleeService.delete(id);
        model.addAttribute("message", "Enrollee has been deleted successfully.");
        return "enrollee";
    }
}
