package com.webcrawler.crawler.application;

import com.webcrawler.crawler.controller.CrawlController;
import com.webcrawler.crawler.service.CrawlerService;
import com.webcrawler.crawler.persistance.CrawlResultRepository;
import com.webcrawler.crawler.persistance.Resource;
import com.webcrawler.crawler.utility.Validate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackageClasses = {CrawlController.class, CrawlerService.class, Resource.class, Validate.class,
                CrawlResultRepository.class})
@EnableJpaRepositories("com.webcrawler.crawler.persistance")
@EntityScan("com.webcrawler.crawler.model")
public class WebCrawlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebCrawlerApplication.class, args);
    }

}
