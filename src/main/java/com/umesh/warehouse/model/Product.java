package com.umesh.warehouse.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author ukushwaha
 */
@Getter
@Setter
public class Product {

    @JsonProperty("name")
    private String name;
    @JsonProperty("contain_articles")
    private List<ContainArticle> containArticles = null;

}
