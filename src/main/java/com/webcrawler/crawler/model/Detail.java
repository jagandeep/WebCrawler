package com.webcrawler.crawler.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "page_title",
        "page_link",
        "image_count"
})
@Entity
public class Detail {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @JsonProperty("page_title")
    private String pageTitle;
    @JsonProperty("page_link")
    private String pageLink;
    @JsonProperty("image_count")
    private Integer imageCount;

    public Detail(String pageTitle, String pageLink, Integer imageCount){
        this.pageTitle = pageTitle;
        this.pageLink = pageLink;
        this.imageCount = imageCount;
    }

}