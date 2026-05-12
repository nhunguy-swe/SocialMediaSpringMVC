package com.social.controller;

import com.social.dao.UserDAO;
import com.social.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private UserDAO userDAO = new UserDAO();

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("user") String username,
                        @RequestParam("pass") String password,
                        HttpSession session,
                        Model model) {
        User user = userDAO.login(username, password);
        if (user != null) {
            session.setAttribute("userAccount", user);
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Sai tài khoản hoặc mật khẩu!");
            return "login";
        }
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam("user") String username,
                           @RequestParam("pass") String password,
                           @RequestParam("repass") String repass,
                           Model model) {

        // 1. Kiểm tra mật khẩu nhập lại
        if (!password.equals(repass)) {
            model.addAttribute("error", "Mật khẩu nhập lại không khớp!");
            return "register";
        }

        // 2. Kiểm tra user đã tồn tại chưa
        User existing = userDAO.checkUserExist(username);
        if (existing != null) {
            model.addAttribute("error", "Tên đăng nhập đã tồn tại!");
            return "register";
        }

        userDAO.register(username, password, "user"); // (Mặc định role là user)
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/home";
    }
}