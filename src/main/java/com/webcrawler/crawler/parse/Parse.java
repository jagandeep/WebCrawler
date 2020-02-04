package com.webcrawler.crawler.parse;

import com.webcrawler.crawler.WebPage;

import java.io.IOException;
import java.util.List;

public interface Parse {

    String getTitle(String url) throws IOException;

    Integer getImageCount(String url) throws IOException;

    public List<WebPage> getUrls(String url) throws IOException;
}
