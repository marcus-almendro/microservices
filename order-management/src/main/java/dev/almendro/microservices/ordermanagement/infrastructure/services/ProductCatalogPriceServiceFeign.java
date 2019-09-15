package dev.almendro.microservices.ordermanagement.infrastructure.services;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-catalog")
@RibbonClient(name = "product-catalog")
public interface ProductCatalogPriceServiceFeign {

    @GetMapping("/products/{productId}")
    PriceDTO getPrice(@PathVariable int productId);
}
