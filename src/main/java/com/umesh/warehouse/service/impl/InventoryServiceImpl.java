package com.umesh.warehouse.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.umesh.warehouse.exception.ProductNotFoundException;
import com.umesh.warehouse.loader.DataHolder;
import com.umesh.warehouse.model.ContainArticle;
import com.umesh.warehouse.model.Inventory;
import com.umesh.warehouse.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author ukushwaha
 */
@Service
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private DataHolder dataHolder;

    @Autowired
    private ObjectMapper objectMapper;

    public void adjustStock(String productName) {
        List<ContainArticle> containArticles = dataHolder.getProductsMap().get(productName);
        if(CollectionUtils.isEmpty(containArticles)){
            throw new ProductNotFoundException("Product does not exist in our inventory");
        }

        for(int i=0; i < containArticles.size(); i++){
            ContainArticle containArticle = containArticles.get(i);
            int articleAmount = Integer.parseInt(containArticle.getAmountOf());
            Inventory inventory = dataHolder.getInventoryMap().get(containArticle.getArtId());
            int remainingStock = Integer.parseInt(inventory.getStock()) - articleAmount;
            inventory.setStock(String.valueOf(remainingStock));
            dataHolder.getInventoryMap().put(containArticle.getArtId(), inventory);
        }
        log.info("Product {} inventory adjusted successfully ", productName);
        try {
            log.info("Inventory after adjustment: {}", objectMapper.writeValueAsString(dataHolder.getInventoryMap()));
        } catch (JsonProcessingException jsonProcessingException) {
            log.error("Failed convert map to string, {}", jsonProcessingException.getMessage());
        }
    }
}
