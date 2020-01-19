package com.webcrawler.crawler;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class WebCrawlerServiceTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testChildLink() throws IOException {
        String url = "https://www.york.ac.uk/teaching/cws/wws/webpage1.html";
        WebCrawlerService crawler = new WebCrawlerService(url,1);
        crawler.getChildLink();
        Assert.assertEquals(1,crawler.getRoot().getLevel());
    }
}