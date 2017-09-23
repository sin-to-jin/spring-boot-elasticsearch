package com.orgsin.sbe.application.service.impl

import com.orgsin.sbe.domain.model.CryptoCurrency
import com.orgsin.sbe.domain.model.CryptoCurrencyRepository
import spock.lang.Specification
import spock.lang.Unroll

/**
 * {@see CryptoCurrencyServiceImpl}
 * Created by sinnosuke on 2017/08/15.
 */
class CryptoCurrencyServiceImplTest extends Specification {
    CryptoCurrencyRepository repository = Mock()
    CryptoCurrencyServiceImpl service = new CryptoCurrencyServiceImpl(repository)

    CryptoCurrency cryptoCurrency

    def setup() {
        cryptoCurrency = new CryptoCurrency(id: "1")
    }

    private static CryptoCurrency f(response) { response.get(0) }

    @Unroll
    def "find by `#reqestModel`"() {
        given:
        def model = reqestModel
        nCnt * repository.findByName(model.name) >> [model]
        mCnt * repository.findByMarketCapitalization(model.marketCapitalization) >> [model]
        dCnt * repository.findByDetail(model.detail) >> [model]
        kCnt * repository.findByKeywords(model.keywords) >> [model]
        aCnt * repository.findAll() >> [model]
        when:
        def response = service.findByModel(model)
        then:
        f(response).name == model.name
        f(response).marketCapitalization == model.marketCapitalization
        f(response).detail == model.detail
        f(response).keywords == model.keywords
        where:
        reqestModel                                         | nCnt | mCnt | dCnt | kCnt | aCnt
        new CryptoCurrency(name: "BTC")                     | 1    | 0    | 0    | 0    | 0
        new CryptoCurrency(marketCapitalization: "470000")  | 0    | 1    | 0    | 0    | 0
        new CryptoCurrency(detail: "the most super crypto") | 0    | 0    | 1    | 0    | 0
        new CryptoCurrency(keywords: ["BTC", "ETH"])        | 0    | 0    | 0    | 1    | 0
        new CryptoCurrency(keywords: [])                    | 0    | 0    | 0    | 0    | 1
        new CryptoCurrency()                                | 0    | 0    | 0    | 0    | 1
    }

    @Unroll
    def "find by #model"() {
        given:
        1 * repository.findById(model.id) >> responseModel
        when:
        def response = service.findById(model.id)
        then:
        response.id == responseId
        where:
        model                       | responseModel      || responseId
        new CryptoCurrency(id: "1") | Optional.of(model) || "1"
        new CryptoCurrency(id: "2") | Optional.empty()   || null
    }

    def "save"() {
        given:
        1 * repository.save(cryptoCurrency) >> cryptoCurrency
        when:
        def response = service.save(cryptoCurrency)
        then:
        response.id == "1"
    }

    def "delete"() {
        given:
        1 * repository.deleteById(cryptoCurrency.id)
        when:
        def response = service.delete(cryptoCurrency.id)
        then:
        response == null
    }
}
