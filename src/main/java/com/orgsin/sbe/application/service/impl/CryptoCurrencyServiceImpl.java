package com.orgsin.sbe.application.service.impl;

import com.google.common.collect.Lists;
import com.orgsin.sbe.application.service.CryptoCurrencyService;
import com.orgsin.sbe.domain.model.CryptoCurrency;
import com.orgsin.sbe.domain.model.CryptoCurrencyRepository;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ogasawara.shinnosuke on 2017/06/21.
 */
@Service
public class CryptoCurrencyServiceImpl implements CryptoCurrencyService {
    private static final Logger LOG = LoggerFactory.getLogger(CryptoCurrencyServiceImpl.class);

    @Autowired
    protected CryptoCurrencyRepository repository;

    @Override
    public List<CryptoCurrency> findByModel(CryptoCurrency cryptoCurrency) {
        LOG.info(cryptoCurrency.toString());
        if (StringUtils.isNotBlank(cryptoCurrency.getName())) return repository.findByName(cryptoCurrency.getName());
        if (StringUtils.isNotBlank(cryptoCurrency.getMarketCapitalization())) return repository.findByMarketCapitalization(cryptoCurrency.getMarketCapitalization());
        if (StringUtils.isNotBlank(cryptoCurrency.getDetail())) return repository.findByDetail(cryptoCurrency.getDetail());
        if (cryptoCurrency.getKeywords() != null && !cryptoCurrency.getKeywords().isEmpty()) return repository.findByKeywords(cryptoCurrency.getKeywords());
        return Lists.newArrayList(repository.findAll());
    }

    @Override
    public CryptoCurrency findById(String id) {
        LOG.info("CryptoCurrency ID={}", id);
        return repository.findById(id).orElse(new CryptoCurrency());
    }

    @Override
    public CryptoCurrency save(CryptoCurrency cryptoCurrency) {
        LOG.info(cryptoCurrency.toString());
        return repository.save(cryptoCurrency);
    }

    @Override
    public void delete(String id) {
        LOG.info("CryptoCurrency ID={}", id);
        repository.deleteById(id);
    }
}
