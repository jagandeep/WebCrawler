package com.webcrawler.crawler.service;

import com.webcrawler.crawler.model.CrawlResult;
import com.webcrawler.crawler.model.Token;
import com.webcrawler.crawler.model.WebPage;
import com.webcrawler.crawler.persistance.TokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CrawlerService {
    private final Logger logger = LoggerFactory.getLogger(CrawlerService.class);
    private final TraversalService service;
    private final TokenRepository tokenRepository;

    @Autowired
    public CrawlerService(TraversalService service, TokenRepository tokenRepository ){
        this.service = service;
        this.tokenRepository =  tokenRepository;
    }

    public Long submitRequest(WebPage page){
        Token token = service.submitRequest(page);
        token.setWebPage(page);
        tokenRepository.save(token);
        tokenRepository.flush();
        return token.getTokenId();
    }

    public String getResult(Long tokenId)  {
        Optional<Token> token = tokenRepository.findById(tokenId);
        return token.get().getStatus();
    }
}

