package com.webcrawler.crawler;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class CrawlerServiceTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testChildLink() throws IOException {
        String url = "https://www.york.ac.uk/teaching/cws/wws/webpage1.html";
        CrawlerService crawler = new CrawlerService(url,1);
        crawler.crawl();
    }
}