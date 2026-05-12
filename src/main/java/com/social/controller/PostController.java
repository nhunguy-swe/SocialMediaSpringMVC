package com.social.controller;

import com.social.dao.PostDAO;
import com.social.entity.Post;
import com.social.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PostController {

    private PostDAO postDAO = new PostDAO();

    @GetMapping("/home")
    public String showHome(HttpSession session, Model model) {
        User user = (User) session.getAttribute("userAccount");
        List<Post> list;

        if (user != null) {
            list = postDAO.getNewsFeed(user.getId());
            if (list == null || list.isEmpty()) {
                list = postDAO.getAllPosts();
            }
        } else {
            list = postDAO.getAllPosts();
        }

        model.addAttribute("listP", list);
        return "home";
    }

    @GetMapping("/search")
    public String search(@RequestParam("txt") String txtSearch, Model model) {
        List<Post> list = postDAO.searchPosts(txtSearch);
        model.addAttribute("listP", list);
        model.addAttribute("searchValue", txtSearch);
        return "home";
    }

    @PostMapping("/createPost")
    public String createPost(@RequestParam("title") String title,
                             @RequestParam("body") String body,
                             HttpSession session) {
        User user = (User) session.getAttribute("userAccount");
        if (user != null) {
            postDAO.createPost(title, body, user.getId(), "active");
        }
        return "redirect:/home";
    }
}