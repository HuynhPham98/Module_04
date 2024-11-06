package com.ra.demo.controller;

import com.ra.demo.model.Product;
import com.ra.demo.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping
    public String index(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "/product/index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("product", new Product());
        return "/product/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("product") Product product) {
        if(productService.create(product)) {
            return "redirect:/product";
        }
        return "redirect:/product/add";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "/product/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute("product") Product product, Model model) {
        if(productService.update(product,id)) {
            return "redirect:/product";
        }
        model.addAttribute("product", productService.findById(id));
        return "/product/edit";
    }

    //Xóa sản phẩm
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        productService.delete(id);
        return "redirect:/product";
    }
}
