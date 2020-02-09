package com.webcrawler.crawler;

import com.webcrawler.crawler.model.WebPage;
import com.webcrawler.crawler.persistance.Resource;
import com.webcrawler.crawler.persistance.WebResource;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentMatchers;

import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class WebPageTest {




    @Test
    public void testEquals(){
        String url1 = "https://www.york.ac.uk/teaching/cws/wws/webpage1.html";
        WebPage page1 = new WebPage(url1);
        Assert.assertTrue(page1.equals(page1));
        WebPage page2 = new WebPage((url1));
        Assert.assertTrue(page2.equals(page1));
        String url2 = "www.google.com";

    }

    @Test
    public void testGetUrl(){
        String url1 = "https://www.york.ac.uk/teaching/cws/wws/webpage1.html";
        WebPage page1 = new WebPage(url1);
        Assert.assertEquals(url1,page1.getUrl());
    }

    @Test
    public void testToString(){
        String url1 = "https://www.york.ac.uk/teaching/cws/wws/webpage1.html";
        WebPage page1 = new WebPage(url1);
        String expected = "WebPage{url='https://www.york.ac.uk/teaching/cws/wws/webpage1.html'}";
        Assert.assertEquals(expected,page1.toString());
    }

    @Test
    public void testHashCode(){
        String url1 = "https://www.york.ac.uk/teaching/cws/wws/webpage1.html";
        WebPage page1 = new WebPage(url1);
        Assert.assertEquals(Objects.hash(url1),page1.hashCode());
    }
}