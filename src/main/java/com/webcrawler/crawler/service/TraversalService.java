package com.webcrawler.crawler.service;

import com.webcrawler.crawler.model.Token;
import com.webcrawler.crawler.model.WebPage;
import com.webcrawler.crawler.persistance.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

@Service
public class TraversalService {
    private final Logger logger = LoggerFactory.getLogger(TransformationService.class);
    private final Resource resource;

    @Autowired
    public TraversalService(Resource resource) {
        this.resource = resource;
    }

    public Queue<WebPage> traverseWebPage(WebPage page, Token token) {
        Queue<WebPage>  pages = null;
        try {
            pages = traverse(page,page.getDepth());
        } catch (IOException e) {
            token.setStatus(Token.FAILED);
            e.printStackTrace();
        }
        return pages;
    }

    private Queue<WebPage> traverse(WebPage rootPage, Integer depth) throws IOException {
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
}
