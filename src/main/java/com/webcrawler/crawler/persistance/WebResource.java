package com.webcrawler.crawler.persistance;

import com.webcrawler.crawler.model.WebPage;
import com.webcrawler.crawler.utility.Validate;
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


@Component
public class WebResource implements Resource {
    public static final String CHARSET_NAME = "ISO-8859-1";
    public static final String IMAGETYPE = "img[src~=(?i)\\.(png|jpe?g|gif)]";
    private Logger logger = LoggerFactory.getLogger(WebResource.class);

    private final Validate validate;

    @Autowired
    public WebResource(Validate validate) {
        this.validate = validate;
    }

    @Override
    public String getTitle(WebPage webPage) throws IOException {
        Document doc = Jsoup.parse(new URL(webPage.getUrl()).openStream(), CHARSET_NAME, webPage.getUrl());
        return doc.title();
    }


    @Override
    public Integer getImageCount(WebPage webPage) throws IOException {
        Document doc = Jsoup.parse(new URL(webPage.getUrl()).openStream(), "ISO-8859-1", webPage.getUrl());
        Elements images =
                doc.select(IMAGETYPE );
        return images.size();
    }


    @Override
    public List<String> getUrls(WebPage webPage) throws IOException {
        logger.info("getUrls webPage " +webPage);
        Document doc = Jsoup.parse(new URL(webPage.getUrl()).openStream(), "ISO-8859-1", webPage.getUrl());
        Elements links = doc.select("a");
        List<String> webURL = new ArrayList<>(links.size());
        for (Element link : links) {
            String absHref = link.attr("abs:href");
            if(validate.validateUrl(absHref))
                webURL.add(absHref);
        }
        logger.info("webURL "+webURL);
        return webURL;
    }
}
