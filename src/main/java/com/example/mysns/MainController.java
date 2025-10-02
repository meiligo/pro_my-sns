package com.example.mysns;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class MainController {

    @Autowired
    private FeedService feedService;

    @GetMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("feeds", feedService.getAllFeeds());
        return "feed.html";
    }

    @PostMapping("/feeds")
    public String createFeed(FeedVO feed,
                 @RequestParam("images")  MultipartFile[] images) throws IOException {

        feedService.createFeed(feed, images);

        return "redirect:/";
    }

    @PostMapping("/feeds/delete")
    public String deleteFeed(@RequestParam("no") int no) {

        // 삭제하기
        feedService.deleteFeed(no);

        return "redirect:/";
    }
}
