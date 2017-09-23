package com.orgsin.sbe.ui.controller;

import com.orgsin.sbe.application.service.CryptoCurrencyService;
import com.orgsin.sbe.domain.model.CryptoCurrency;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.*;

/**
 * Created by ogasawara.shinnosuke on 2017/06/21.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/crypto-currencies")
public class CryptoCurrencyController {

    private final CryptoCurrencyService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<CryptoCurrency> findAll(CryptoCurrency cryptoCurrency) {
        return service.findByModel(cryptoCurrency);
    }

    @RequestMapping(value = "/{id}/**", method = RequestMethod.GET)
    public CryptoCurrency findById(@PathVariable("id") String id) {
        return service.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public CryptoCurrency save(@RequestBody CryptoCurrency cryptoCurrency) {
        return service.save(cryptoCurrency);
    }

    @RequestMapping(value = "/{id}/**", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {
        service.delete(id);
    }
}
