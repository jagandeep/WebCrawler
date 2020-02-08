package com.webcrawler.crawler;

import com.webcrawler.crawler.controller.CrawlController;
import com.webcrawler.crawler.model.Detail;
import com.webcrawler.crawler.model.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CrawlerService {
    private Logger logger = LoggerFactory.getLogger(CrawlerService.class);
    private String url;
    private Integer depth;
    private Queue<WebPage> webPages;

    public CrawlerService(String url, Integer depth){
        this.url = url;
        this.depth = depth;
    }

    public Example crawl() throws IOException {
        webPages = new LinkedList<WebPage>();
        Queue<WebPage> processedWebPages = new LinkedList<WebPage>();
        WebPage rootPage = new WebPage(url);
        rootPage.setDepth(0);
        webPages.add(rootPage);
        WebPage page = webPages.poll();
        processedWebPages.add(page);
        while(page != null && page.getDepth() < this.depth) {
            for(WebPage p: page.findChildLinks()){
                p.setDepth(page.getDepth()+1);
                webPages.add(p);
            }
            page = webPages.poll();
            processedWebPages.add(page);
        }
        return transformPage(processedWebPages);
    }

    private Example transformPage(Queue<WebPage> pages)  {
        logger.info(String.valueOf(pages));
        Example example = new Example();
        List<Detail> details = new ArrayList<>();
        Detail detail = null;
        for(WebPage p : pages){
            try {
                detail = new Detail(p.getTitle(),p.getUrl(),p.imageCount());
            } catch (IOException e) {
                e.printStackTrace();
            }
            details.add(detail);
        }
        example.setDetails(details);
        return example;
    }

}

