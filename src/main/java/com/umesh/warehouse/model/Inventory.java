package com.umesh.warehouse.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author ukushwaha
 */
@Getter
@Setter
@NoArgsConstructor
public class Inventory {

    @JsonProperty("art_id")
    private String artId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("stock")
    private String stock;

}
