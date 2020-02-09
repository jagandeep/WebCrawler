package com.webcrawler.crawler.controller;

import com.webcrawler.crawler.model.CrawlerService;
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
    public CrawlResult crawlWebPage(@RequestBody WebPage page) throws IOException {
        return crawler.crawl(page);

    }

    public CrawlResult getCrawlResult(@RequestParam String token){
        return null;
    }
}
