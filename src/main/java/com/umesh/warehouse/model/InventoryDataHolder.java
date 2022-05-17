package com.umesh.warehouse.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class InventoryDataHolder {

    @JsonProperty("inventory")
    private List<Inventory> inventoryList;
}
