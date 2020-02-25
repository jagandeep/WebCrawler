package com.webcrawler.crawler.service;

import com.webcrawler.crawler.model.CrawlResult;
import com.webcrawler.crawler.model.Token;
import com.webcrawler.crawler.model.WebPage;
import com.webcrawler.crawler.persistance.CrawlResultRepository;
import com.webcrawler.crawler.persistance.TokenRepository;
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
    private final TokenRepository tokenRepository;
    private final CrawlResultRepository repository;
    private final TransformationService transformationService;
    private final TraversalService traversalService;

    @Autowired
    public ScheduleService(TokenRepository tokenRepository, CrawlResultRepository repository,
                           TransformationService transformationService, TraversalService traversalService) {
        this.tokenRepository = tokenRepository;
        this.repository = repository;
        this.transformationService = transformationService;
        this.traversalService = traversalService;
    }

    @Async
    @Scheduled(initialDelay = 1000, fixedRate = 10000)
    public void processRequest()  {
        logger.info("Invoked processRequest");
        List<Token> tokens = tokenRepository.findAll();
        returnIfEmpty(tokens);
        traverseTransformAndSetProcessingStatusForAllToken(tokens);
    }

    private boolean returnIfEmpty(List<Token> tokens) {
        if(tokens.isEmpty()) return true;
        return false;
    }

    private void traverseTransformAndSetProcessingStatusForAllToken(List<Token> tokens) {
        for(Token token: tokens){
            traverseTransformAndSetProcessingStatus(token);
        }
    }

    private void traverseTransformAndSetProcessingStatus(Token token) {
        WebPage page;
        Queue<WebPage> pages;
        if(isSubmitted(token)){
            page = token.getWebPage();
            token.setStatus(Token.INPROCESS);
            pages = this.traversalService.traverseWebPage(page, token);
            CrawlResult result = this.transformationService.transformWebPage(pages);
            result.setToken(token);
            token.setStatus(Token.PROCESSED);
            repository.saveAndFlush(result);
        }
    }
    private boolean isSubmitted(Token token) {
        return token.getStatus() == Token.SUBMITTED;
    }
}
