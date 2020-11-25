package com.nile.buyerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BuyerService {

    @Autowired
    private BuyerRepository buyerRepository;

    public List<Buyer> getAllBuyers() {
        List<Buyer> buyers = buyerRepository.findAll();
        return buyers;
    }

    public Optional<Buyer> getBuyer(UUID id) {
        return buyerRepository.findById(id);
    }

    public void addBuyer(Buyer buyer) {
        buyerRepository.save(buyer);
    }

    public void updateBuyer(Buyer buyer) {
        buyerRepository.save(buyer);
    }

    public void deleteBuyer(UUID id) {
        buyerRepository.deleteById(id);
    }
}
