package com.webcrawler.crawler;

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
    public void testFindAllLinks() throws IOException {
        String url1 = "https://www.york.ac.uk/teaching/cws/wws/webpage1.html";
        Parse parse = Mockito.mock(ParseWebPage.class);
        List<WebPage> pages = new ArrayList<WebPage>(1);
        pages.add(new WebPage("www.abc.com"));
        when(parse.getUrls(url1)).thenReturn(pages);
        WebPage page1 = new WebPage(url1);
        page1.setParse(parse);
        Assert.assertEquals(pages,page1.findChildLinks());
        verify(parse).getUrls(ArgumentMatchers.eq(url1));
    }
    @Test(expected  = IOException.class)
    public void testFindAllLinksException() throws IOException {
        String url1 = "https://www.york.ac.uk/teaching/cws/wws/webpage1.html";
        Parse parse = Mockito.mock(ParseWebPage.class);
        when(parse.getUrls(url1)).thenThrow(new IOException());
        WebPage page1 = new WebPage(url1);
        page1.setParse(parse);
        page1.findChildLinks();
    }

    @Test
    public void testEquals(){
        String url1 = "https://www.york.ac.uk/teaching/cws/wws/webpage1.html";
        WebPage page1 = new WebPage(url1);
        Assert.assertTrue(page1.equals(page1));
        WebPage page2 = new WebPage((url1));
        Assert.assertTrue(page2.equals(page1));
        String url2 = "www.google.com";
        WebPage page3 = new WebPage(url2);
        Assert.assertFalse(page3.equals(page2));
        ParseWebPage parseWebPage = new ParseWebPage();
        Assert.assertFalse(page3.equals(parseWebPage));
        Assert.assertFalse(page3.equals(null));

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