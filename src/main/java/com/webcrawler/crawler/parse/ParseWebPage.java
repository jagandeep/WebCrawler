package com.webcrawler.crawler.parse;

import com.webcrawler.crawler.WebPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ParseWebPage implements Parse {
    private Logger logger = LoggerFactory.getLogger(ParseWebPage.class);

    @Override
    public String getTitle(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        String title = doc.title();
        return title;
    }


    @Override
    public Integer getImageCount(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements images =
                doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");

        return images.size();
    }


    @Override
    public List<WebPage> getUrls(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a");
        List<WebPage> webPages = new ArrayList<WebPage>(links.size());
        for (Element link : links) {
            String absHref = link.attr("abs:href");
            logger.info(absHref);
            webPages.add(new WebPage(absHref));
        }
        return webPages;
    }

}
