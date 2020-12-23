package com.nile.buyerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class BuyerController {
    @Autowired
    private BuyerService buyerService;

    @RequestMapping("/buyers")
    public Iterable<Buyer> getAllBuyers(@RequestHeader("Authorization") String token) {
        return buyerService.getAllBuyers(token);
    }

    @RequestMapping("/buyers/{id}")
    public Optional<Buyer> getBuyer(@RequestHeader("Authorization") String token, @PathVariable("id") String id) {
        return buyerService.getBuyer(token, UUID.fromString(id));
    }

    @RequestMapping("/buyers/search/{userName}")
    public Optional<Buyer> getBuyerByUsername(@RequestHeader("Authorization") String token, @PathVariable("userName") String userName) {
        return buyerService.getBuyerByUsername(token, userName);
    }

    @PostMapping("/buyers")
    public void addBuyer(@RequestBody Buyer buyer) {
        buyerService.addBuyer(buyer);
    }

    @PutMapping("/buyers")
    public void updateBuyer(@RequestHeader("Authorization") String token, @RequestBody Buyer buyer) {
        buyerService.updateBuyer(token, buyer);
    }

    @DeleteMapping("/buyers/{id}")
    public void deleteBuyer(@RequestHeader("Authorization") String token, @PathVariable("id") String id) {
        buyerService.deleteBuyer(token, UUID.fromString(id));
    }
}
