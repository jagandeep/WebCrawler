package com.webcrawler.crawler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class WebPage implements java.io.Serializable{
    private String url;
    private Logger logger = LoggerFactory.getLogger(WebPage.class);


    private Parse parse;

    public WebPage(String url){
        this.url = url;
        parse = new ParseWebPage();
    }

    public void setParse(Parse parse) {
        this.parse = parse;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<WebPage> findChildLinks() throws IOException {
        List<WebPage> webPages = parse.getUrls(this.url);
        return webPages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WebPage webPage = (WebPage) o;
        return url.equals(webPage.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }

    @Override
    public String toString() {
        return "{" +
                "url='" + url +
                '}';
    }
}
