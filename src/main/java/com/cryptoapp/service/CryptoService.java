
package com.cryptoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import java.util.Map;

@Service
public class CryptoService {

    private static final String API_URL = "https://api.coingecko.com/api/v3/simple/price";

    @Autowired
    private RestTemplate restTemplate;

    public Map<String, Map<String, Object>> getCryptoPrice(String id) {
        String url = UriComponentsBuilder.fromHttpUrl(API_URL)
                .queryParam("ids", id)
                .queryParam("vs_currencies", "usd")
                .toUriString();
        // returns a JSON object parsed to Map with proper generic type
        ParameterizedTypeReference<Map<String, Map<String, Object>>> typeRef =
                new ParameterizedTypeReference<Map<String, Map<String, Object>>>() {};
        ResponseEntity<Map<String, Map<String, Object>>> response =
                restTemplate.exchange(url, HttpMethod.GET, null, typeRef);
        return response.getBody();
    }
}
