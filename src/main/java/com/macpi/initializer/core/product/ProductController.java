package com.macpi.initializer.core.product;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.notFound;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
class ProductController {

    private final ProductDao productDao;

    @GetMapping(value = "/products/{articleId}", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Product> findProduct(@PathVariable final int articleId) {
        log.info("Someone is looking for details about product: {}", articleId);
        Optional<Product> maybeProduct = productDao.findProductByArticleId(articleId);

        return maybeProduct.map(ResponseEntity::ok)
                           .orElse(notFound().build());

    }
}
