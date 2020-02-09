package com.webcrawler.crawler.service;

import com.webcrawler.crawler.model.CrawlResult;
import com.webcrawler.crawler.model.Detail;
import com.webcrawler.crawler.model.Token;
import com.webcrawler.crawler.model.WebPage;
import com.webcrawler.crawler.persistance.CrawlResultRepository;
import com.webcrawler.crawler.persistance.Resource;
import com.webcrawler.crawler.persistance.TokenRepository;
import com.webcrawler.crawler.persistance.WebPageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
@EnableScheduling
public class ScheduleService {
    private final Logger logger = LoggerFactory.getLogger(ScheduleService.class);
    private final TraversalService service;
    private final CrawlResultRepository repository;
    private final Resource resource;
    private final WebPageRepository webPageRepository;
    @Autowired
    public ScheduleService(TraversalService service, CrawlResultRepository repository, Resource resource,
                           WebPageRepository webPageRepository) {
        this.service = service;
        this.repository = repository;
        this.resource = resource;
        this.webPageRepository= webPageRepository;
    }

    @Async
    @Scheduled(initialDelay = 1000, fixedRate = 10000)
    public void processRequest()  {
        // lets read from the database
        WebPage page = service.getRequest();
        Queue<WebPage> pages = null;
        page.getToken().setStatus(Token.INPROCESS);
        try {
            pages = traverse(page,page.getDepth());
        } catch (IOException e) {
            page.getToken().setStatus(Token.FAILED);
            e.printStackTrace();
        }
        CrawlResult result = transformPage(pages);
        Optional<WebPage>  webPage = webPageRepository.findById(page.getId());
        result.setToken(webPage.get().getToken());
        page.getToken().setStatus(Token.PROCESSED);
        repository.save(result);
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


    private CrawlResult transformPage(Queue<WebPage> pages)  {
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
