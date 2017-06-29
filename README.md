# spring-boot-elasticsearch
[![CircleCI](https://circleci.com/gh/ogasawaraShinnosuke/spring-boot-elasticsearch.svg?style=svg)](https://circleci.com/gh/ogasawaraShinnosuke/spring-boot-elasticsearch)
[![codebeat badge](https://codebeat.co/badges/4b9f1d23-487a-4d17-812a-168a81c5d3bd)](https://codebeat.co/projects/github-com-ogasawarashinnosuke-spring-boot-elasticsearch-master)
[![Official HP](https://img.shields.io/badge/official-homepage-green.svg)](https://ogasawarashinnosuke.github.io/spring-boot-elasticsearch/)
[![MIT License](http://img.shields.io/badge/license-MIT-blue.svg?style=flat)](LICENSE)

To support elasticsearch v5.4 with spring-boot v2.0.0.M2 and Kibana v5.4, Fluentd v2.3.
* Java v1.8
* Gradle v4.0
* Spring Boot v2.0.0.M2
* Tomcat v8
* Elasticsearch v5.4.2
* Kibana v5.4.2
* Fluentd v2.3

## How to Use
*only Mac OS*

### Install Java

``` sh
brew cask install java
```

### Install Elasticsearch

``` sh
brew install elasticsearch
elasticsearch # Start Elasticsearch.
```

### Install Kibana

``` sh
brew install kibana
kibana # Start Kibana.
```

### Install this project

``` sh
git clone git@github.com:ogasawaraShinnosuke/spring-boot-elasticsearch.git
cd spring-boot-elasticsearch
./gradlew build
./gradlew bootRun
```

### Install Fluentd

``` sh
wget http://packages.treasuredata.com.s3.amazonaws.com/2/macosx/td-agent-2.3.0-0.dmg # Execute this dmg-file. 
sudo /opt/td-agent/embedded/bin/fluent-gem install fluent-plugin-elasticsearch # Install gem for Fluentd.
sudo launchctl unload /Library/LaunchDaemons/td-agent.plist # Invalidate settings.
sudo launchctl load /Library/LaunchDaemons/td-agent.plist # Activate settings.
```

- Settinds [td-agent.conf](settings/etc/td-agent/td-agent.conf)

## Request examples

### Check kibana 

- Access to `http://localhost:5601`

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

### Summary Matrix System

- Fluentd is to build Tomcat's unified logging layer with Elasticsearch, Kibana.

```
tomcat - fluentd
  |         |
 elasticsearch
      |
    kibana
```

Server|Port|Built In|Homebrew|dmg|Version
:--:|:--:|:--:|:--:|:--:|:--:
Tomcat|8080|:heavy_check_mark:|||v2.0.0.M2
Fluentd|8888,24230|||:heavy_check_mark:|v2.3
Elasticsearch|9200,9300|:heavy_check_mark:|:heavy_check_mark:||v5.4.2
Kibana|5601||:heavy_check_mark:||v5.4.2
