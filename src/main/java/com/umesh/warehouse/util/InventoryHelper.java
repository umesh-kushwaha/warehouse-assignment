package com.umesh.warehouse.util;

import com.umesh.warehouse.exception.OurOfStockException;
import com.umesh.warehouse.loader.DataHolder;
import com.umesh.warehouse.model.ContainArticle;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ukushwaha
 */
@Slf4j
public class InventoryHelper {


    public static int getProductQuantity(DataHolder dataHolder, List<ContainArticle> articleList){
        Map<String,String> availableInventoryMap = getAvailableInventoryStockMap(dataHolder, articleList);
        int min = Integer.parseInt(availableInventoryMap.get(articleList.get(0).getArtId())) / Integer.parseInt(articleList.get(0).getAmountOf());
        for(int i=0 ; i< articleList.size(); i++){
            min = Math.min(min,
                    Integer.parseInt(availableInventoryMap.get(articleList.get(i).getArtId())) / Integer.parseInt(articleList.get(i).getAmountOf()));
        }
        log.info(""+availableInventoryMap);
        return min;
    }

    public static Map<String,String> getAvailableInventoryStockMap(DataHolder dataHolder, List<ContainArticle> articleList){
        Map<String, String> inventoryMap = new HashMap<>();
        articleList.forEach(article -> {
            inventoryMap.put(article.getArtId(),dataHolder.getInventoryMap().get(article.getArtId()).getStock());
        });
        return inventoryMap;
    }

    public static void isProductStockAvailable(DataHolder dataHolder, String productName, List<ContainArticle> articleList){

        if(getProductQuantity(dataHolder, articleList) <= 0){
             throw new OurOfStockException(productName + " is out of stock");
        }
    }
}
