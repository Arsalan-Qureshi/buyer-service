package com.nile.buyerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BuyerService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BuyerRepository buyerRepository;

    public List<Buyer> getAllBuyers(String token) {
        if (authenticateAdmin(token).matches("OK")) {
            List<Buyer> buyers = buyerRepository.findAll();
            return buyers;
        } else {
            return null;
        }
    }

    public Optional<Buyer> getBuyer(String token, UUID id) {
        if (getBuyerId(token).matches(id.toString())) {
            return buyerRepository.findById(id);
        } else {
            return null;
        }
    }

    public void addBuyer(Buyer buyer) {
        buyerRepository.save(buyer);
    }

    public void updateBuyer(String token, Buyer buyer) {
        if (authenticateBuyer(token).matches("OK")) {
            buyerRepository.save(buyer);
        }
    }

    public void deleteBuyer(String token, UUID id) {
        if (authenticateBuyer(token).matches("OK")) {
            buyerRepository.deleteById(id);
        }
    }

    public Optional<Buyer> getBuyerByUsername(String token, String userName) {
        if (authenticateBuyer(token).matches("OK")) {
            return buyerRepository.findByUserName(userName);
        } else {
            return null;
        }
    }

    public String authenticateBuyer(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://buyer-authentication-service/buyer/authenticate", HttpMethod.GET, entity, String.class);
        return responseEntity.getBody();
    }

    public String authenticateAdmin(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://admin-authentication-service/admin/authenticate", HttpMethod.GET, entity, String.class);
        return responseEntity.getBody();
    }

    public String getBuyerId(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://buyer-authentication-service/buyer/userName", HttpMethod.GET, entity, String.class);
        String userName = responseEntity.getBody();
        if (userName != null) {
            ResponseEntity<Object[]> responseEntityObject = restTemplate.exchange("http://buyer-service/buyers/search/" + userName, HttpMethod.GET, entity, Object[].class);
            Object[] objects = responseEntityObject.getBody();
            return objects[0].toString();
        } else {
            return null;
        }
    }

}
