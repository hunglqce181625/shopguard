package com.vnpt.shopguard.controller;

import com.vnpt.shopguard.entity.Category;
import com.vnpt.shopguard.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public String list(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Category> data = (keyword != null && !keyword.isBlank())
                ? categoryService.searchByKeyword(keyword)
                : categoryService.findAll();
        model.addAttribute("data", data);
        model.addAttribute("keyword", keyword);
        return "admin/category/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("category", new Category());
        return "admin/category/form";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model, RedirectAttributes redirectAttr) {
        Category category = categoryService.findById(id);
        if (category == null) {
            redirectAttr.addFlashAttribute("error", "Danh mục không tồn tại.");
            return "redirect:/admin/category";
        }
        model.addAttribute("category", category);
        return "admin/category/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("category") Category category,
                       BindingResult result,
                       RedirectAttributes redirectAttr) {
        if (result.hasErrors()) {
            return "admin/category/form";
        }
        categoryService.save(category);
        redirectAttr.addFlashAttribute("success", "Lưu danh mục thành công.");
        return "redirect:/admin/category";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttr) {
        if (categoryService.deleteById(id)) {
            redirectAttr.addFlashAttribute("success", "Đã xóa danh mục.");
        } else {
            redirectAttr.addFlashAttribute("error", "Danh mục không tồn tại.");
        }
        return "redirect:/admin/category";
    }
}
