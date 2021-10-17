package com.xdsoft.app.controller;

import com.xdsoft.app.domain.RatingDb;
import com.xdsoft.app.services.GetRating;
import com.xdsoft.app.services.SearchLeader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.xdsoft.app.repos.RatingRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.IOException;
import java.text.*;
import java.util.Map;

@Controller
public class MainController {

    private final RatingRepo ratingRepo;

    public MainController(RatingRepo ratingRepo) {
        this.ratingRepo = ratingRepo;
    }


    @GetMapping("/")
    public String mainPage(Map<String, Object> model) throws ParseException {
        Iterable<RatingDb> ratingall = ratingRepo.findAll();
        model.put("messages", ratingall);
        return "main.html";
    }


    @GetMapping("/collect")
    public String collectData(@RequestHeader(value = "Chrome.333") Map<String, Object> model) throws IOException {
        GetRating getRating = new GetRating(ratingRepo);
        Thread t1 = new Thread(getRating);
        t1.start();
        return "redirect:/";
    }


    @PostMapping("/")
    public String getHistory(@RequestParam String fromdate,
                             @RequestParam String todate,
                             Map<String, Object> model) {
        SearchLeader searchLeader = new SearchLeader();
        searchLeader.search(ratingRepo, fromdate, todate, model);
        return "main";
    }
}
