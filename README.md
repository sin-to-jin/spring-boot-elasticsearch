# spring-boot-elasticsearch
To support elasticsearch v5.4 with spring-boot v2.0.0.M2.
* Elasticsearch v5.4
* Spring Boot v2.0.0.M2

## Request examples

### Insert data

``` sh
curl -XPUT http://localhost:8080/crypto-currencies -d '{"name":"bitcoin","marketCapitalization":"46219389522.4","keywords": ["bitcoin"]}'
curl -XPUT http://localhost:8080/crypto-currencies -d '{"name":"Ethereum","marketCapitalization":"31278008829.5","keywords": ["Ethereum"]}'
```

### Operation data
``` sh
# want to find list
curl -XGET http://localhost:8080/crypto-currencies

# want to find docID (`AVzJU4hHoL2ZelEv7YH5` is uniq docID)
curl -XGET http://localhost:8080/crypto-currencies/AVzJU4hHoL2ZelEv7YH5

# want to delete docID (`AVzJU4hHoL2ZelEv7YH5` is uniq docID)
curl -XDELETE http://localhost:8080/crypto-currencies/AVzJU4hHoL2ZelEv7YH5
```
