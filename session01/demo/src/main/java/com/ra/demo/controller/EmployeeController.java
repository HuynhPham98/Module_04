package com.ra.demo.controller;

import com.ra.demo.model.Employee;
import com.ra.demo.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String index(Model model) {
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "/employee/index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("employee", new Employee());
        return "/employee/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("employee") Employee employee) {
        if(employeeService.create(employee)) {
            return "redirect:/employee";
        }
        return "redirect:/employee/add";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee", employee);
        return "/employee/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, @ModelAttribute("employee") Employee employee,Model model) {
        if(employeeService.update(employee, id)) {
            return "redirect:/employee";
        }
        model.addAttribute("employee", employeeService.findById(id));
        return "/employee/edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        employeeService.delete(id);
        return "redirect:/employee";
    }

    @GetMapping("/search")
    public String search(@ModelAttribute("name") String name, Model model) {
        List<Employee> employees = employeeService.findEmployeeByName(name);
        model.addAttribute("employees", employees);
        return "/employee/index";
    }
}
