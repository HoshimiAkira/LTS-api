package com.ha.ltschat.config;

import com.ha.ltschat.service.AzureBlobStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureBlobStorageConfig {
    @Value("${azure.storage.connectionString}")
    private String connectionString;

    @Value("${azure.storage.containerName}")
    private String containerName;

    @Bean
    public AzureBlobStorageService azureBlobStorageService() {
        return new AzureBlobStorageService(connectionString, containerName);
    }
}