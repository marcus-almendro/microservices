package dev.almendro.microservices.productcatalog.interfaces.rest;

import dev.almendro.microservices.productcatalog.application.*;
import dev.almendro.microservices.productcatalog.domain.model.catalog.Catalog;
import dev.almendro.microservices.productcatalog.domain.model.catalog.CatalogId;
import dev.almendro.microservices.productcatalog.domain.model.category.Category;
import dev.almendro.microservices.productcatalog.domain.model.category.CategoryId;
import dev.almendro.microservices.productcatalog.domain.model.product.Product;
import dev.almendro.microservices.productcatalog.interfaces.rest.dto.CatalogDTO;
import dev.almendro.microservices.productcatalog.interfaces.rest.dto.CategoryDTO;
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
public class CatalogsController {

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    private Logger logger = LoggerFactory.getLogger(CatalogsController.class);

    @GetMapping("/catalogs/{catalogId}/categories/{categoryId}/products")
    public List<ProductDTO> retrieveCategoryProducts(@PathVariable int catalogId, @PathVariable int categoryId){
        List<Product> products = productService.retrieveCategoryProducts(categoryId);
        return Arrays.stream(products.toArray(new Product[0]))
                        .map(p -> modelMapper.map(p, ProductDTO.class))
                        .collect(Collectors.toList());
    }

    @GetMapping("/catalogs")
    public List<CatalogDTO> retrieveCatalogs(){
        List<Catalog> catalogs = catalogService.retrieveCatalogs();
        return Arrays.stream(catalogs.toArray(new Catalog[0]))
                .map(p -> modelMapper.map(p, CatalogDTO.class))
                .collect(Collectors.toList());
    }
    
    @PostMapping("/catalogs")
    public ResponseEntity<Object> createCatalog(){
        CatalogId catalogId = catalogService.createCatalog();
        return createdResponse(catalogId.getCatalogId());
    }

    @PostMapping("/catalogs/{catalogId}/categories")
    public ResponseEntity<Object> createCategory(@PathVariable int catalogId, @RequestBody CategoryDTO categoryDTO){
        CategoryId categoryId = catalogService.startCategoryCreation(catalogId, categoryDTO.getName());
        return createdResponse(categoryId.getCategoryId());
    }

    @GetMapping("/catalogs/{catalogId}/categories")
    public List<CategoryDTO> getCategories(@PathVariable int catalogId){
        return Arrays.stream(categoryService.getCategoriesFromCatalog(catalogId).toArray(new Category[0]))
                .map(p -> modelMapper.map(p, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/catalogs/{catalogId}/categories/{categoryId}/products")
    public ResponseEntity<Object> linkProduct(@PathVariable int catalogId, @PathVariable int categoryId, @RequestBody ProductDTO productDTO){
        categoryService.linkProductToCategory(categoryId, productDTO.getSku());
        return createdResponse(productDTO.getSku());
    }

    private ResponseEntity<Object> createdResponse(Object obj){
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj)
                .toUri();

        return ResponseEntity.created(location).build();
    }
}