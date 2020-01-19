package com.webcrawler.crawler;

import java.io.IOException;
import java.util.List;

public interface Parse {
    public List<WebPage> getUrls(String url) throws IOException;
}
