package com.webcrawler.crawler.service;

import com.webcrawler.crawler.model.CrawlResult;
import com.webcrawler.crawler.model.Detail;
import com.webcrawler.crawler.model.Token;
import com.webcrawler.crawler.model.WebPage;
import com.webcrawler.crawler.persistance.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Service
public class TraversalService {
    private final Logger logger = LoggerFactory.getLogger(TraversalService.class);
    private final Resource resource;
    private Queue<WebPage> requests;

    @Autowired
    public TraversalService(Resource resource) {
        this.resource = resource;
        this.requests = new LinkedBlockingQueue<WebPage>() ;
    }

    public WebPage getRequest(){
        return this.requests.poll();
    }

    public Token submitRequest(WebPage page){
        requests.add(page);
        Token token = new Token();
        token.setStatus(Token.SUBMITTED);
        return token;
    }


}
