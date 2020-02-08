package com.webcrawler.crawler.controller;

import com.webcrawler.crawler.CrawlerService;
import com.webcrawler.crawler.model.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
public class CrawlController {

    private Logger logger = LoggerFactory.getLogger(CrawlController.class);

    @GetMapping(path="/submit")
    public Example crawlWebPage(@RequestParam(name = "url")  String url,
                                @RequestParam String depth) throws IOException {
        logger.info("url :" + url);
        logger.info("depth :" + depth);
        CrawlerService crawler = new CrawlerService(url.trim(),Integer.parseInt(depth));
        return crawler.crawl();

    }
}
