package com.webcrawler.crawler.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@ToString
@Entity
@EqualsAndHashCode
public class WebPage {
    public  static  final String SUBMITTED ="Submitted";
    public  static  final String INPROCESS ="In-Process";
    public  static  final String PROCESSED  ="Processed ";
    public  static  final String FAILED  ="Failed ";

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter @Setter
    private Long id;
    @Setter  @Getter
    private String url;
    @Setter @Getter
    private Integer depth;
    @Setter @Getter
    private String status;


    public WebPage(){}
    public  WebPage(String url){
        this(url, Integer.valueOf(0));
    }

    public  WebPage(String url,Integer depth){
        this.url =url;
        this.depth = depth;
    }
}
