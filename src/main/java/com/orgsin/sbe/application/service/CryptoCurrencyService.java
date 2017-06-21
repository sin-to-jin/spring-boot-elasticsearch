package com.orgsin.sbe.application.service;

import com.orgsin.sbe.domain.model.CryptoCurrency;

import java.util.List;

/**
 * Created by ogasawara.shinnosuke on 2017/06/21.
 */
public interface CryptoCurrencyService {
    List<CryptoCurrency> findByModel(CryptoCurrency cryptoCurrency);

    CryptoCurrency findById(String id);

    CryptoCurrency save(CryptoCurrency cryptoCurrency);

    void delete(String id);
}
