package com.webcrawler.crawler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CrawlController {

    private Logger logger = LoggerFactory.getLogger(CrawlController.class);

    @GetMapping(path="/submit",produces = "application/json")
    public String crawlWebPage(@RequestParam String url,
                                  @RequestParam String depth) throws IOException {
        logger.info("url :" + url);
        logger.info("depth :" + depth);
        WebCrawlerService crawler = new WebCrawlerService(url.trim(),Integer.parseInt(depth));
        crawler.getChildLink();
        return crawler.toString();
    }
}
