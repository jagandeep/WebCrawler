package com.webcrawler.crawler.application;

import com.webcrawler.crawler.controller.CrawlController;
import com.webcrawler.crawler.model.CrawlerService;
import com.webcrawler.crawler.persistance.Resource;
import com.webcrawler.crawler.utility.Validate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = {CrawlController.class, CrawlerService.class, Resource.class, Validate.class})
public class WebCrawlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebCrawlerApplication.class, args);
    }

}
