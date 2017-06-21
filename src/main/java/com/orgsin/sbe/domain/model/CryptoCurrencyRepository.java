package com.orgsin.sbe.domain.model;

import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ogasawara.shinnosuke on 2017/06/21.
 */
@Repository
public interface CryptoCurrencyRepository extends ElasticsearchCrudRepository<CryptoCurrency, String> {
    List<CryptoCurrency> findByName(String name);

    List<CryptoCurrency> findByMarketCapitalization(String marketCapitalization);

    List<CryptoCurrency> findByDetail(String detail);

    List<CryptoCurrency> findByKeywords(List<String> keywords);
}
