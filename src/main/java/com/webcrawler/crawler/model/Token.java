package com.webcrawler.crawler.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class Token {
    public  static  final String SUBMITTED ="Submitted";
    public  static  final String INPROCESS ="In-Process";
    public  static  final String PROCESSED  ="Processed ";
    public  static  final String FAILED  ="Failed ";

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long tokenId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private WebPage webPage;
    private String status;
}
