package com.umesh.warehouse.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ukushwaha
 */
@Getter
@Setter
public class ContainArticle {

    @JsonProperty("art_id")
    private String artId;
    @JsonProperty("amount_of")
    private String amountOf;

}
