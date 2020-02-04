package com.webcrawler.crawler;

import com.webcrawler.crawler.parse.Parse;
import com.webcrawler.crawler.parse.ParseWebPage;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParseWebPageTest {

    @Test
    public void getUrls() throws IOException {
        WebPage page1 = new WebPage("https://www.york.ac.uk/teaching/cws/wws/webpage2.html");
        WebPage page2 = new WebPage("https://www.york.ac.uk/teaching/cws/wws/col3.html");
        List<WebPage> pages = new ArrayList<>();
        pages.add(page1);
        pages.add(page2);
        String url1 = "https://www.york.ac.uk/teaching/cws/wws/webpage1.html";
        Parse parse = new ParseWebPage();
        Assert.assertEquals( pages ,parse.getUrls(url1));
    }
}