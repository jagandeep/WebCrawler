package com.webcrawler.crawler.parse;

import com.webcrawler.crawler.WebPage;

import java.io.IOException;
import java.util.List;

public interface Parse {
    public List<WebPage> getUrls(String url) throws IOException;
}
