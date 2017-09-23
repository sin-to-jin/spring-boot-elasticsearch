package com.orgsin.sbe.application.service.impl

import com.orgsin.sbe.domain.model.CryptoCurrency
import com.orgsin.sbe.domain.model.CryptoCurrencyRepository
import spock.lang.Specification

/**
 * {@see CryptoCurrencyServiceImpl}
 * Created by sinnosuke on 2017/08/15.
 */
class CryptoCurrencyServiceImplTest extends Specification {
    CryptoCurrencyRepository repository = Mock()
    CryptoCurrencyServiceImpl service = new CryptoCurrencyServiceImpl(repository)

    def "find by `name`"() {
        given:
        def model = new CryptoCurrency(name: "BTC")
        1 * repository.findByName(model.name) >> [model]
        when:
        def response = service.findByModel(model)
        then:
        response.get(0).name == "BTC"
    }

    def "find by `marketCapitalization`"() {
        given:
        def model = new CryptoCurrency(marketCapitalization: "470000")
        1 * repository.findByMarketCapitalization(model.marketCapitalization) >> [model]
        when:
        def response = service.findByModel(model)
        then:
        response.get(0).marketCapitalization == "470000"
    }

    def "find by `detail`"() {
        given:
        def model = new CryptoCurrency(detail: "the most super crypto")
        1 * repository.findByDetail(model.detail) >> [model]
        when:
        def response = service.findByModel(model)
        then:
        response.get(0).detail == "the most super crypto"
    }

    def "find by `keywords`"() {
        given:
        def model = new CryptoCurrency(keywords: ["BTC", "ETH"])
        1 * repository.findByKeywords(model.keywords) >> [model]
        when:
        def response = service.findByModel(model)
        then:
        response.get(0).keywords == ["BTC", "ETH"]
    }

    def "find by `id`"() {
        given:
        def model = new CryptoCurrency(id: "1")
        1 * repository.findById(model.id) >> Optional.of(model)
        when:
        def response = service.findById(model.id)
        then:
        response.id == "1"
    }

    def "find by not found `id`"() {
        given:
        def model = new CryptoCurrency(id: "2")
        1 * repository.findById(model.id) >> Optional.empty()
        when:
        def response = service.findById(model.id)
        then:
        response.id == null
    }
}
