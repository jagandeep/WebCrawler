package com.webcrawler.crawler;

import com.webcrawler.crawler.parse.Parse;
import com.webcrawler.crawler.parse.ParseWebPage;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;


@ToString
public class WebPage implements java.io.Serializable{
    @Setter  @Getter
    private String url;
    private final Logger logger = LoggerFactory.getLogger(WebPage.class);
    @Setter  @Getter
    private Parse parse;

    public WebPage(String url){
        this.url = url;
        parse = new ParseWebPage();
    }

    public List<WebPage> findChildLinks() throws IOException {
        List<WebPage> webPages = parse.getUrls(this.url);
        return webPages;
    }
}
