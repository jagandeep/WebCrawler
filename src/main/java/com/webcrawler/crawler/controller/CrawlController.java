package com.webcrawler.crawler.controller;

import com.webcrawler.crawler.service.TokenService;
import com.webcrawler.crawler.model.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class CrawlController {
    @Autowired
    private TokenService crawler;

    @PostMapping(path="/submit")
    public Long makeCrawlRequest(@RequestBody WebPage page)  {
        return crawler.submitRequest(page);
    }

    @GetMapping(path="/fetch")
    public String getCrawlRequestStatus(@RequestParam(name = "id") Long tokenId){
        return crawler.getResult(tokenId);
    }
}
