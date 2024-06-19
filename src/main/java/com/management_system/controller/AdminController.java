package com.management_system.controller;

import com.management_system.model.Admin;
import com.management_system.service.AdminService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String displayLogInView(Model model, HttpSession session) {
        if (adminLoggedIn(session)) {
            return "redirect:/studentsystem";
        }
        model.addAttribute("admin", new Admin());
        return "admin_login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("admin") Admin admin, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            model.addAttribute("admin", admin);
            return "admin_login";
        }

        if (!adminService.authenticateAdmin(admin.getUsername(), admin.getPassword())) {
            model.addAttribute("error", "Invalid username or password");
            return "admin_login";
        }

        session.setAttribute("username", admin.getUsername());
        return "redirect:/studentsystem";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.setAttribute("username", null);
        session.invalidate();
        return "redirect:/login";
    }

    private Boolean adminLoggedIn(HttpSession session) {
        return session.getAttribute("username") != null;
    }

}
