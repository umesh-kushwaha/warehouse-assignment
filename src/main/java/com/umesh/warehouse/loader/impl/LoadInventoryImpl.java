package com.umesh.warehouse.loader.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umesh.warehouse.loader.DataHolder;
import com.umesh.warehouse.loader.LoadInventory;
import com.umesh.warehouse.model.Inventory;
import com.umesh.warehouse.model.InventoryDataHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
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
public class LoadInventoryImpl implements LoadInventory {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DataHolder dataHolder;

    @Value("${file.inventory.json.path}")
    private Resource inventoryJsonResource;

    @Override
    public void load()  {
        try {
            InventoryDataHolder inventoryDataHolder = objectMapper.readValue(
                    ResourceUtils.getFile(inventoryJsonResource.getURI()), InventoryDataHolder.class);

            Map<String, Inventory> inventoryMap = inventoryDataHolder.getInventoryList().stream()
                    .collect(Collectors.toMap(Inventory::getArtId, inventory->inventory));
            dataHolder.setInventoryMap(inventoryMap);
            log.info("Inventory details loaded successfully. {}", inventoryDataHolder);

        }catch (IOException e) {
            log.error("Failed to load inventory JSON file : {}", e.getMessage());
        }

    }
}
