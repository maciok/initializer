package com.macpi.initializer.core.product;

import static java.time.Clock.systemUTC;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.macpi.initializer.tags.Unit;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.UUID;

@Unit
class ProductMapperTest {

    private ProductMapper testee;

    @Test
    void buildProductFromResultSet() throws SQLException {
        // given
        final var expectedProduct = new Product(UUID.randomUUID(), "Ferrari", 1, OffsetDateTime.now(systemUTC()));
        final var mockedResultSet = mockResultSet(expectedProduct);
        
        testee = new ProductMapper();

        // when
        Product product = testee.mapRow(mockedResultSet, 0);

        // then
        assertThat(product).isEqualTo(expectedProduct);
    }

    private ResultSet mockResultSet(final Product expectedProduct) throws SQLException {
        final var mock = mock(ResultSet.class);
        when(mock.getString(eq("uuid"))).thenReturn(expectedProduct.getId().toString());
        when(mock.getString(eq("name"))).thenReturn(expectedProduct.getName());
        when(mock.getInt(eq("article_id"))).thenReturn(expectedProduct.getArticleId());
        final var lastUpdatedTimestamp = Timestamp.valueOf(expectedProduct.getLastUpdated().toLocalDateTime());
        when(mock.getTimestamp(eq("last_updated"))).thenReturn(lastUpdatedTimestamp);
        
        return mock;
    }
}