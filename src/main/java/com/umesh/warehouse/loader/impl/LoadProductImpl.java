package com.umesh.warehouse.loader.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umesh.warehouse.loader.DataHolder;
import com.umesh.warehouse.loader.LoadProduct;
import com.umesh.warehouse.model.ContainArticle;
import com.umesh.warehouse.model.Inventory;
import com.umesh.warehouse.model.Product;
import com.umesh.warehouse.model.ProductDataHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ukushwaha
 */
@Component
@Slf4j
public class LoadProductImpl implements LoadProduct {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DataHolder dataHolder;

    @Value("${file.product.json.path}")
    private Resource inventoryJsonResource;

    @Override
    public void load() {

        try {
            ProductDataHolder productDataHolder = objectMapper.readValue(
                    inventoryJsonResource.getFile(), ProductDataHolder.class);
            Map<String,List<ContainArticle>> productMap = productDataHolder.getProductList().stream()
                    .collect(Collectors.toMap(Product::getName, Product::getContainArticles));
            dataHolder.setProductsMap(productMap);
            log.info("Product details loaded successfully.");
        } catch (IOException e) {
            log.error("Failed to load inventory JSON file : {}", e.getMessage());
        }

    }
}
