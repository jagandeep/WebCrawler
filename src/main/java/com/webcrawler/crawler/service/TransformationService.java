package com.webcrawler.crawler.service;

import com.webcrawler.crawler.model.CrawlResult;
import com.webcrawler.crawler.model.Detail;
import com.webcrawler.crawler.model.WebPage;
import com.webcrawler.crawler.persistance.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Service
public class TransformationService {
    private final Logger logger = LoggerFactory.getLogger(TransformationService.class);
    private final Resource resource;

    @Autowired
    public TransformationService(Resource resource) {
        this.resource = resource;
    }

    public CrawlResult transformWebPage(Queue<WebPage> pages)  {
        if(logger.isInfoEnabled())
            logger.info(String.valueOf(pages));
        CrawlResult crawlResult = new CrawlResult();
        List<Detail> details = new ArrayList<>();
        Detail detail = null;
        for(WebPage p : pages){
            try {
                detail = new Detail(resource.getTitle(p),p.getUrl(),resource.getImageCount(p));
            } catch (IOException e) {
                e.printStackTrace();
            }
            details.add(detail);
        }
        crawlResult.setDetails(details);
        crawlResult.totalImages();
        crawlResult.totalLinks();
        return crawlResult;
    }
}
