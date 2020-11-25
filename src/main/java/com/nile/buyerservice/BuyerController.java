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
    public Iterable<Buyer> getAllBuyers() {
        return buyerService.getAllBuyers();
    }

    @RequestMapping("/buyers/{id}")
    public Optional<Buyer> getBuyer(@PathVariable("id") String id) {
        return buyerService.getBuyer(UUID.fromString(id));
    }

    @PostMapping("/buyers")
    public void addBuyer(@RequestBody Buyer buyer) {
        buyerService.addBuyer(buyer);
    }

    @PutMapping("/buyers")
    public void updateBuyer(@RequestBody Buyer buyer) {
        buyerService.updateBuyer(buyer);
    }

    @DeleteMapping("/buyers/{id}")
    public void deleteBuyer(@PathVariable("id") String id) {
        buyerService.deleteBuyer(UUID.fromString(id));
    }
}
