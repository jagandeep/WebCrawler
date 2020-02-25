package com.webcrawler.crawler.service;

import com.webcrawler.crawler.model.WebPage;
import com.webcrawler.crawler.persistance.WebPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TokenService {

    private final WebPageRepository webPageRepository;

    @Autowired
    public TokenService(WebPageRepository webPageRepository ){
        this.webPageRepository =  webPageRepository;
    }

    public Long submitRequest(WebPage page){
        page.setStatus(WebPage.SUBMITTED);
        webPageRepository.save(page);
        webPageRepository.flush();
        return page.getId();
    }

    public String getResult(Long tokenId)  {
        Optional<WebPage> webPage = webPageRepository.findById(tokenId);
        return webPage.get().getStatus();
    }
}

