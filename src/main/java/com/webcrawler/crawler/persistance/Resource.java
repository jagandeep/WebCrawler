package com.webcrawler.crawler.persistance;

import com.webcrawler.crawler.model.WebPage;

import java.io.IOException;
import java.util.List;

public interface Resource {

    String getTitle(WebPage webPage) throws IOException;

    Integer getImageCount(WebPage webPage) throws IOException;

    public List<String> getUrls(WebPage webPage) throws IOException;
}
