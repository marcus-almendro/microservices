package dev.almendro.microservices.productcatalog.interfaces.rest;

import dev.almendro.microservices.productcatalog.application.ProductService;
import dev.almendro.microservices.productcatalog.domain.model.product.Product;
import dev.almendro.microservices.productcatalog.interfaces.rest.dto.ProductDTO;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductsController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    private Logger logger = LoggerFactory.getLogger(ProductsController.class);

    @GetMapping("/products")
    public List<ProductDTO> retrieveProducts() {
        List<Product> products = productService.retrieveProducts();
        return Arrays.stream(products.toArray(new Product[0]))
                .map(p -> modelMapper.map(p, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/products/{sku}")
    public ProductDTO retrieveProduct(@PathVariable long sku) {
        Product product = productService.retrieveProduct(sku);
        return modelMapper.map(product, ProductDTO.class);
    }

    @PostMapping("/products")
    public ResponseEntity<Object> createProduct(@RequestBody ProductDTO productDTO) {
        logger.info(productDTO.getName() + "");
        productService.createProduct(productDTO.getSku(), productDTO.getName(), productDTO.getBasePrice(), productDTO.getCurrentPrice());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productDTO.getSku())
                .toUri();

        return ResponseEntity.created(location).build();
    }

}