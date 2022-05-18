package com.umesh.warehouse.service.impl;

import com.umesh.warehouse.loader.DataHolder;
import com.umesh.warehouse.model.ContainArticle;
import com.umesh.warehouse.model.response.ProductListResponse;
import com.umesh.warehouse.model.response.ProductResponse;
import com.umesh.warehouse.service.ProductService;
import com.umesh.warehouse.util.InventoryHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
            response.setQuantity(InventoryHelper.getProductQuantity(dataHolder,entry.getValue()));
            productResponses.add(response);
        }

        productListResponse.setProductResponseList(productResponses);

        return productListResponse;
    }


}
