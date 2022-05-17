package com.umesh.warehouse.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author ukushwaha
 */
@NoArgsConstructor
@Getter
@Setter
public class ProductListResponse {
    @JsonProperty("products")
    private List<ProductResponse> productResponseList;
}
