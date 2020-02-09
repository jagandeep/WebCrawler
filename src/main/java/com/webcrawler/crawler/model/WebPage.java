package com.webcrawler.crawler.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@ToString
@Entity
public class WebPage {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter @Setter
    private Long id;
    @Setter  @Getter
    private String url;
    @Setter @Getter
    private Integer depth;
     @Getter @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Token token;

    public WebPage(){}
    public  WebPage(String url){
        this(url, Integer.valueOf(0));
    }

    public  WebPage(String url,Integer depth){
        this.url =url;
        this.depth = depth;
    }
}
