package com.webcrawler.crawler.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@ToString
@Entity
public class WebPage implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter @Setter
    private Long id;
    @Setter  @Getter
    private String url;
    @Setter @Getter
    private Integer depth;
    @OneToOne @Getter @Setter
    @JoinColumn(name = "id", nullable = false)
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
