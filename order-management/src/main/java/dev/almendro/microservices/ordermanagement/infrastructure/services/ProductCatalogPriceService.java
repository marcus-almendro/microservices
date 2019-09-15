package dev.almendro.microservices.ordermanagement.infrastructure.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import dev.almendro.microservices.ordermanagement.domain.services.PriceService;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@Component
public class ProductCatalogPriceService implements PriceService {

    Cache<Integer, Double> priceCache;

    public ProductCatalogPriceService() {
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .withCache("preConfigured",
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, Double.class,
                                ResourcePoolsBuilder.heap(100))
                                .build())
                .build(true);

        Cache<Integer, Double> preConfigured = cacheManager.getCache("preConfigured", Integer.class, Double.class);

        priceCache = cacheManager.createCache("myCache",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, Double.class,
                        ResourcePoolsBuilder.heap(100)).build());
    }

    @Autowired
    private ProductCatalogPriceServiceFeign proxy;

    @Override
    @HystrixCommand(fallbackMethod = "cachedProductPrice")
    public double productPrice(int productId) {
        double currentPrice = proxy.getPrice(productId).getCurrentPrice();
        priceCache.put(productId, currentPrice);
        return currentPrice;
    }

    public double cachedProductPrice(int productId) {
        return priceCache.get(productId);
    }
}
