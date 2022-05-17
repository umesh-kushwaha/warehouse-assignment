package com.umesh.warehouse.event;

import com.umesh.warehouse.loader.impl.LoadInventoryImpl;
import com.umesh.warehouse.loader.impl.LoadProductImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author ukushwaha
 */
@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private LoadInventoryImpl loadInventory;

    @Autowired
    private LoadProductImpl loadProduct;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        //load eagerly all file data
        loadInventory.load();
        loadProduct.load();
    }
}
