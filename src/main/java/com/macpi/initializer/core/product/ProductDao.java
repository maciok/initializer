package com.macpi.initializer.core.product;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
class ProductDao {
    private static final String SELECT_WITH_ARTICLE_ID = "SELECT * FROM product WHERE article_id=?";

    private final JdbcTemplate jdbc;

    Optional<Product> findProductByArticleId(final int articleId) {
        final var nullableProduct = jdbc.queryForObject(
                SELECT_WITH_ARTICLE_ID,
                new Object[]{articleId},
                new ProductMapper()
        );
        return Optional.ofNullable(nullableProduct);
    }
}
