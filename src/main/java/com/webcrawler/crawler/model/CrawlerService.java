package com.webcrawler.crawler.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Service
public class CrawlerService {
    private final Logger logger = LoggerFactory.getLogger(CrawlerService.class);
    private TraversalService service;

    @Autowired
    public CrawlerService(TraversalService service ){
        this.service = service;
    }

    public Example crawl(WebPage page) throws IOException {
        return service.transformPage(service.traverse(page,page.getDepth()));

    }



}

