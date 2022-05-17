package com.umesh.warehouse.service.impl;

import com.umesh.warehouse.loader.DataHolder;
import com.umesh.warehouse.model.ContainArticle;
import com.umesh.warehouse.model.response.ProductListResponse;
import com.umesh.warehouse.model.response.ProductResponse;
import com.umesh.warehouse.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ukushwaha
 */

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private DataHolder dataHolder;

    public ProductListResponse getProductList(){
        ProductListResponse productListResponse = new ProductListResponse();
        List<ProductResponse> productResponses = new ArrayList<>();
        for(Map.Entry<String,List<ContainArticle>> entry : dataHolder.getProductsMap().entrySet()){
            ProductResponse response = new ProductResponse();
            response.setName(entry.getKey());
            response.setQuantity(getProductQuantity(entry.getValue()));
            productResponses.add(response);
        }

        productListResponse.setProductResponseList(productResponses);

        return productListResponse;
    }


    private int getProductQuantity(List<ContainArticle> articleList){
        Map<String,String> availableInventoryMap = getAvailableInventoryStockMap(articleList);
        int min = Integer.parseInt(availableInventoryMap.get(articleList.get(0).getArtId())) / Integer.parseInt(articleList.get(0).getAmountOf());
        for(int i=0 ; i< articleList.size(); i++){
            min = Math.min(min,
                    Integer.parseInt(availableInventoryMap.get(articleList.get(i).getArtId())) / Integer.parseInt(articleList.get(i).getAmountOf()));
        }
        log.info(""+availableInventoryMap);
        return min;
    }

    private Map<String,String> getAvailableInventoryStockMap(List<ContainArticle> articleList){
        Map<String, String> inventoryMap = new HashMap<>();
        articleList.forEach(article -> {
            inventoryMap.put(article.getArtId(),dataHolder.getInventoryMap().get(article.getArtId()).getStock());
        });
        return inventoryMap;
    }

}
