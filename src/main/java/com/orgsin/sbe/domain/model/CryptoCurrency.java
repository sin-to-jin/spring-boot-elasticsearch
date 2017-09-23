package com.orgsin.sbe.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

/**
 * Created by ogasawara.shinnosuke on 2017/06/21.
 */
@Getter
@ToString
@NoArgsConstructor
@Document(indexName = "crypto-currencies", type = "crypto-currencies")
public class CryptoCurrency {

    @Id
    private String id;

    private String name;

    @Field(type = FieldType.Double)
    private String marketCapitalization;

    @Field(type = FieldType.text)
    private String detail;

    private List<String> keywords;

}
