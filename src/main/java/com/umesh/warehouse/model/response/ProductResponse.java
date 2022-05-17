package com.umesh.warehouse.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author ukushwaha
 */
@NoArgsConstructor
@Getter
@Setter
public class ProductResponse {
    @JsonProperty("name")
    private String name;
    @JsonProperty("quantity")
    private int quantity;
}
