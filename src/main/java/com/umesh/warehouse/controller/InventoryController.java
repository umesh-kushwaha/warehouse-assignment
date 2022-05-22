package com.umesh.warehouse.controller;

import com.umesh.warehouse.exception.OurOfStockException;
import com.umesh.warehouse.exception.ProductNotFoundException;
import com.umesh.warehouse.model.response.ProductListResponse;
import com.umesh.warehouse.service.impl.InventoryServiceImpl;
import com.umesh.warehouse.service.impl.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author ukushwaha
 */
@RestController
@RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class InventoryController {

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private InventoryServiceImpl inventoryService;

    @GetMapping(value = "/products")
    public ResponseEntity<ProductListResponse> getProducts(){

        return new ResponseEntity<>(productService.getProductList(), HttpStatus.OK);
    }

    @PutMapping(value = "/sell")
    public ResponseEntity sellProduct(@RequestParam("name") String productName){
        log.info("Product Name : {}", productName);
        inventoryService.adjustStock(productName);

        return new ResponseEntity(HttpStatus.OK);
    }
}
