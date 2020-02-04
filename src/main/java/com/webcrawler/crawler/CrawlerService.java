package com.webcrawler.crawler;

import com.webcrawler.crawler.model.Detail;
import com.webcrawler.crawler.model.Example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CrawlerService {
    private String url;
    private Integer depth;
    private Queue<WebPage> pages;

    public CrawlerService(String url, Integer depth){
        this.url = url;
        this.depth = depth;
    }

    public Example crawl() throws IOException {
        pages = new LinkedList<WebPage>();
        pages.add(new WebPage(url));
        WebPage page = pages.poll();
        while(page != null){
            for(WebPage p: page.findChildLinks()){
                pages.add(p);
            }
            page = pages.poll();
        }
        return transformPage(pages);
    }

    private Example transformPage(Queue<WebPage> pages) throws IOException {
        Example example = new Example();
        List<Detail> details = new ArrayList<>();
        Detail detail = null;
        for(WebPage p : pages){
            detail = new Detail(p.getTitle(),p.getUrl(),p.imageCount());
            details.add(detail);
        }
        example.setDetails(details);
        return example;
    }

}

