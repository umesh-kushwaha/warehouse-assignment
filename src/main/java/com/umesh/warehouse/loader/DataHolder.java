package com.umesh.warehouse.loader;

import com.umesh.warehouse.model.ContainArticle;
import com.umesh.warehouse.model.Inventory;
import com.umesh.warehouse.model.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author ukushwaha
 */
@Component
@Getter
@Setter
public class DataHolder {

    private Map<String,Inventory> inventoryMap;
    private Map<String,List<ContainArticle>> productsMap;
}
