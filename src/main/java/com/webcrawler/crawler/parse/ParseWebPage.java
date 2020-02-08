package com.webcrawler.crawler.parse;

import com.webcrawler.crawler.WebPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ParseWebPage implements Parse {
    private Logger logger = LoggerFactory.getLogger(ParseWebPage.class);

    private final Validate validate;

    @Autowired
    public ParseWebPage(Validate validate) {
        this.validate = validate;
    }

    @Override
    public String getTitle(String url) throws IOException {
        Document doc = Jsoup.parse(new URL(url).openStream(), "ISO-8859-1", url);
        String title = doc.title();
        return title;
    }


    @Override
    public Integer getImageCount(String url) throws IOException {
        Document doc = Jsoup.parse(new URL(url).openStream(), "ISO-8859-1", url);
        Elements images =
                doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
        return images.size();
    }


    @Override
    public List<WebPage> getUrls(String url) throws IOException {
        Document doc = Jsoup.parse(new URL(url).openStream(), "ISO-8859-1", url);
        Elements links = doc.select("a");
        List<WebPage> webPages = new ArrayList<WebPage>(links.size());
        for (Element link : links) {
            String absHref = link.attr("abs:href");
            logger.info(absHref);
            if(validate.validateUrl(absHref))
                 webPages.add(new WebPage(absHref));
        }
        return webPages;
    }




}
