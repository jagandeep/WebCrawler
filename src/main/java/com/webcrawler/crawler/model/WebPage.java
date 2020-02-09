package com.webcrawler.crawler.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
public class WebPage implements java.io.Serializable{
    @Setter  @Getter
    private String url;
    @Setter @Getter
    private Integer depth;

    public  WebPage(String url){
        this(url, Integer.valueOf(0));
    }

    public  WebPage(String url,Integer depth){
        this.url =url;
        this.depth = depth;
    }
}
