package com.webcrawler.crawler.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "total_links",
        "total_images",
        "details"
})
@Entity(name="result")
public class CrawlResult {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @JsonIgnore
    private Long id;
    @JsonProperty("total_links")
    private Integer totalLinks;
    @JsonProperty("total_images")
    private Integer totalImages;
    @JsonProperty("details")
    @OneToMany
    private List<Detail> details = null;

    public void totalLinks(){
        this.totalLinks = 0;
        for(Detail d : details){
            this.totalLinks+=1;
        }
    }

    public void totalImages(){
        this.totalImages = 0;
        for(Detail d : details){
            this.totalImages+=d.getImageCount();
        }
    }
}