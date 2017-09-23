package com.orgsin.sbe.application.service.impl;

import com.google.common.collect.Lists;
import com.orgsin.sbe.application.service.CryptoCurrencyService;
import com.orgsin.sbe.domain.model.CryptoCurrency;
import com.orgsin.sbe.domain.model.CryptoCurrencyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ogasawara.shinnosuke on 2017/06/21.
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class CryptoCurrencyServiceImpl implements CryptoCurrencyService {
    protected final CryptoCurrencyRepository repository;

    @Override
    public List<CryptoCurrency> findByModel(CryptoCurrency cryptoCurrency) {
        log.info(cryptoCurrency.toString());
        if (StringUtils.isNotBlank(cryptoCurrency.getName())) return repository.findByName(cryptoCurrency.getName());
        if (StringUtils.isNotBlank(cryptoCurrency.getMarketCapitalization())) return repository.findByMarketCapitalization(cryptoCurrency.getMarketCapitalization());
        if (StringUtils.isNotBlank(cryptoCurrency.getDetail())) return repository.findByDetail(cryptoCurrency.getDetail());
        if (cryptoCurrency.getKeywords() != null && !cryptoCurrency.getKeywords().isEmpty()) return repository.findByKeywords(cryptoCurrency.getKeywords());
        return Lists.newArrayList(repository.findAll());
    }

    @Override
    public CryptoCurrency findById(String id) {
        log.info("CryptoCurrency ID={}", id);
        return repository.findById(id).orElse(new CryptoCurrency());
    }

    @Override
    public CryptoCurrency save(CryptoCurrency cryptoCurrency) {
        log.info(cryptoCurrency.toString());
        return repository.save(cryptoCurrency);
    }

    @Override
    public void delete(String id) {
        log.info("CryptoCurrency ID={}", id);
        repository.deleteById(id);
    }
}
