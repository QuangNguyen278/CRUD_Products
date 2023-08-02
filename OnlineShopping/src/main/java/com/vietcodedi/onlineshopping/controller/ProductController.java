package com.vietcodedi.onlineshopping.controller;

import com.vietcodedi.onlineshopping.model.Product;
import com.vietcodedi.onlineshopping.service.ProductService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping(value = {"", "/"})
    public String index(Model model){
        //Khởi tạo và Lấy data từ ProductService
//        service.initialList();
        List<Product> products = service.getProducts();
        //Truyền dữ liệu sang cho Index
        model.addAttribute("products",products);
        return "admin/product/index";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("product", new Product());
        return "admin/product/add";
    }

    @PostMapping(value = {"", "/"})
    public String store(@ModelAttribute("product") Product product){
        try {
            System.out.println(service.saveProduct(product));
//            System.out.println(service.getProducts());
        } catch (Exception e) {
            return null;

        }
        return "redirect:/products/";
    }

        @GetMapping("/{id}/delete")
        public String deleteProduct(@PathVariable int id) {
            try {
                service.deleteProduct(id);
//            System.out.println(service.getProducts());
            } catch (Exception e) {
                return null;
            }
            return "redirect:/products/";
        }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        Product product = service.getProductById(id);
        model.addAttribute("product", product);
        return "admin/product/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Product product) {
        service.updateProduct(product);
        return "redirect:/products/";
    }
}
