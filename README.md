# spring-boot-elasticsearch
To support elasticsearch v5.4 with spring-boot v2.0.0.M2 and Kibana v5.4, Fluentd v2.3.
* Java v1.8
* Gradle v4.0
* Spring Boot v2.0.0.M2
* Elasticsearch v5.4.2
* Kibana v5.4.2
* Fluentd v2.3

## How to Use
*only Mac OS*

### Install Java, Elasticsearch, Kibana

``` sh
brew cask install java
brew install elasticsearch kibana
ln -sfv /usr/local/opt/elasticsearch/*.plist ~/Library/LaunchAgents
launchctl load ~/Library/LaunchAgents/homebrew.mxcl.elasticsearch.plist
git clone git@github.com:ogasawaraShinnosuke/spring-boot-elasticsearch.git
cd spring-boot-elasticsearch
./gradlew build
./gradlew bootRun
```

### Install Fluentd
`https://td-agent-package-browser.herokuapp.com/2/macosx`

## Request examples

### Insert data

``` sh
curl -XPOST http://localhost:8080/crypto-currencies -d '{"name":"bitcoin","marketCapitalization":46219389522.4,"keywords": ["bitcoin","BTC"]}'
curl -XPOST http://localhost:8080/crypto-currencies -d '{"name":"Ethereum","marketCapitalization":31278008829.5,"keywords": ["Ethereum","ETH"]}'
curl -XPOST http://localhost:8080/crypto-currencies -d '{"name":"Ripple","marketCapitalization":12034938611.6,"keywords":["Ripple","XRP"]}'
```
#### Check this data

``` sh
curl http://localhost:9200/crypto-currencies/_search\?pretty
```

### Operation data

``` sh
# want to find list
curl -XGET http://localhost:8080/crypto-currencies

# want to find query (include `i` for name parameter)
curl -XGET http://localhost:8080/crypto-currencies?name=*i*

# want to find docID (`AVzJU4hHoL2ZelEv7YH5` is uniq docID)
curl -XGET http://localhost:8080/crypto-currencies/AVzJU4hHoL2ZelEv7YH5

# want to delete docID (`AVzJU4hHoL2ZelEv7YH5` is uniq docID)
curl -XDELETE http://localhost:8080/crypto-currencies/AVzJU4hHoL2ZelEv7YH5
```

*Search by numerical value, List and BetWeen are not implemented yet*
