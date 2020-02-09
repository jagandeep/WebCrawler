package com.webcrawler.crawler.model;

import com.webcrawler.crawler.persistance.Resource;
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
public class TraversalService {
    private final Logger logger = LoggerFactory.getLogger(TraversalService.class);
    private final Resource resource;

    @Autowired
    public TraversalService(Resource resource) {
        this.resource = resource;
    }

    public Queue<WebPage> traverse(WebPage rootPage,Integer depth) throws IOException {
        Queue<WebPage> pendingWebPages = new LinkedList<>();
        Queue<WebPage> processedWebPages = new LinkedList<>();
        rootPage.setDepth(0);
        pendingWebPages.add(rootPage);
        processedWebPages.add(rootPage);
        WebPage page = pendingWebPages.poll();
        while(page != null && page.getDepth() < depth){
            for (String p : resource.getUrls(page)) {
                pendingWebPages.add(new WebPage(p,page.getDepth()+1));
            }
            page = pendingWebPages.poll();
            processedWebPages.add(page);
        }
        return processedWebPages;
    }


    public CrawlResult transformPage(Queue<WebPage> pages)  {
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
