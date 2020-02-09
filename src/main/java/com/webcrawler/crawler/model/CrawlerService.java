package com.webcrawler.crawler.model;

import com.webcrawler.crawler.persistance.CrawlResultRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class CrawlerService {
    private final Logger logger = LoggerFactory.getLogger(CrawlerService.class);
    private TraversalService service;
    private CrawlResultRepository repository;

    @Autowired
    public CrawlerService(TraversalService service,CrawlResultRepository repository ){
        this.service = service;
        this.repository = repository;
    }

    public CrawlResult crawl(WebPage page) throws IOException {
        CrawlResult crawlResult = service.transformPage(service.traverse(page,page.getDepth()));
        repository.save(crawlResult);
        return crawlResult;
    }
}

