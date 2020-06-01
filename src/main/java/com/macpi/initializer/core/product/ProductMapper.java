package com.macpi.initializer.core.product;

import static java.time.ZoneOffset.UTC;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

class ProductMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        final var id = rs.getString("uuid");
        final var name = rs.getString("name");
        final var articleId = rs.getInt("article_id");
        final var lastUpdatedDateTime = rs.getTimestamp("last_updated").toLocalDateTime();
        return new Product(UUID.fromString(id), name, articleId, lastUpdatedDateTime.atOffset(UTC));
    }
}
