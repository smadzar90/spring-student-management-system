package com.management_system.controller;

import com.management_system.model.Mentor;
import com.management_system.service.MentorService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/studentsystem")
@Controller
@RequiredArgsConstructor
public class MentorController {

    private final MentorService mentorService;

    @GetMapping("/mentors")
    public String displayMentorsView(Model model, HttpSession session) {
        if (adminNotLoggedIn(session)) {
            return "redirect:/login";
        }

        List<Mentor> mentors = mentorService.getAllMentors();
        setModelAttributes(model, mentors);
        return "/mentor/mentors";
    }

    private Boolean adminNotLoggedIn(HttpSession session) {
        return session.getAttribute("username") == null;
    }

    private void setModelAttributes(Model model, List<Mentor> mentors) {
        model.addAttribute("title", "Mentors");
        model.addAttribute("mentorsActive", true);
        model.addAttribute("mentors", mentors);
    }
}
