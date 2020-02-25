package com.webcrawler.crawler.service;

import com.webcrawler.crawler.model.CrawlResult;
import com.webcrawler.crawler.model.WebPage;
import com.webcrawler.crawler.persistance.CrawlResultRepository;
import com.webcrawler.crawler.persistance.WebPageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@EnableScheduling
public class ScheduleService {
    private final Logger logger = LoggerFactory.getLogger(ScheduleService.class);
    private final WebPageRepository webPageRepository;
    private final CrawlResultRepository repository;
    private final TransformationService transformationService;
    private final TraversalService traversalService;

    @Autowired
    public ScheduleService(WebPageRepository webPageRepository, CrawlResultRepository repository,
                           TransformationService transformationService, TraversalService traversalService) {
        this.webPageRepository = webPageRepository;
        this.repository = repository;
        this.transformationService = transformationService;
        this.traversalService = traversalService;
    }

    @Async
    @Scheduled(initialDelay = 1000, fixedRate = 10000)
    public void processRequest()  {
        logger.info("Invoked processRequest");
        List<WebPage> webPages = webPageRepository.findAll();
        if(returnIfEmpty(webPages)) return;
        traverseTransformAndSetProcessingStatusForAllToken(webPages);
    }

    private boolean returnIfEmpty(List<WebPage> webPages) {
        return webPages.isEmpty();
    }

    private void traverseTransformAndSetProcessingStatusForAllToken(List<WebPage> webPages) {
        for(WebPage webPage: webPages){
            traverseTransformAndSetProcessingStatus(webPage);
        }
    }

    private void traverseTransformAndSetProcessingStatus(WebPage webPage) {
        Queue<WebPage> pages;
        if(isSubmitted(webPage)){
            webPage.setStatus(WebPage.INPROCESS);
            pages = traversalService.traverseWebPage(webPage);
            CrawlResult result = transformationService.transformWebPage(pages);
            webPage.setStatus(WebPage.PROCESSED);
            repository.saveAndFlush(result);
        }
    }
    private boolean isSubmitted(WebPage webPage) {
        return webPage.getStatus().equals(WebPage.SUBMITTED);
    }
}
