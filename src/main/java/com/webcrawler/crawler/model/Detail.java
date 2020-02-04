package com.webcrawler.crawler.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "page_title",
        "page_link",
        "image_count"
})
public class Detail {

    @JsonProperty("page_title")
    private String pageTitle;
    @JsonProperty("page_link")
    private String pageLink;
    @JsonProperty("image_count")
    private Integer imageCount;
}