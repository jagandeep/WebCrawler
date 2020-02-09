package com.webcrawler.crawler.controller;

import com.webcrawler.crawler.service.CrawlerService;
import com.webcrawler.crawler.model.WebPage;
import com.webcrawler.crawler.model.CrawlResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class CrawlController {
    @Autowired
    private  CrawlerService crawler;

    @PostMapping(path="/submit")
    public Long makeCrawlRequest(@RequestBody WebPage page) throws IOException {
        return crawler.submitRequest(page);
    }

    @GetMapping(path="/fetch")
    public String getCrawlRequestStatus(@RequestParam(name = "id") Long tokenId){
        return crawler.getResult(tokenId);
    }
}
