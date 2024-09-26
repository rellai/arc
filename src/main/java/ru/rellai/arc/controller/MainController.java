
package ru.rellai.arc.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/")
    public String list(HttpServletRequest request, Model model) {
        model.addAttribute("title", "Architecturizer");
        return "index";
    }

    @GetMapping("/diagrams")
    public String diagrams(HttpServletRequest request, Model model) {
        model.addAttribute("title", "Diagrams");
        return "diagrams";
    }

}