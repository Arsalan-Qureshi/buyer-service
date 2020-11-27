package com.nile.buyerservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BuyerTests {

    @Autowired
    private BuyerService service;

    @MockBean
    private BuyerRepository repository;

    @Test
    public void getAllBuyersTest() {
        when(repository.findAll()).thenReturn(Stream.of(
                new Buyer(UUID.fromString("fff2f54e-357a-4fc4-bfb5-a8f2ff0d67e6"),
                        "Mike Tyson", "CA, 123", "041-2456081",
                        "mike_tyson@gmail.com", "USA", "5500 0000 0000 0004"))
                .collect(Collectors.toList()));
        assertEquals(1, service.getAllBuyers().size());
    }

    @Test
    public void getBuyerTest() {
        UUID id = UUID.fromString("fff2f54e-357a-4fc4-bfb5-a8f2ff0d67e6");
        when(repository.findById(id))
                .thenReturn(java.util.Optional.of(
                        new Buyer(id, "Mike Tyson", "CA, 123", "041-2456081",
                                "mike_tyson@gmail.com", "USA", "5500 0000 0000 0004")));
        assertEquals("Mike Tyson", service.getBuyer(id).get().getName());
        assertEquals("CA, 123", service.getBuyer(id).get().getAddress());
        assertEquals("041-2456081", service.getBuyer(id).get().getContactInformation());
        assertEquals("mike_tyson@gmail.com", service.getBuyer(id).get().getEmail());
        assertEquals("USA", service.getBuyer(id).get().getCountry());
        assertEquals("5500 0000 0000 0004", service.getBuyer(id).get().getCreditCard());
    }

    @Test
    public void addBuyerTest() {
        UUID id = UUID.fromString("fff2f54e-357a-4fc4-bfb5-a8f2ff0d67e6");
        Buyer buyer = new Buyer(id, "Mike Tyson", "CA, 123", "041-2456081",
                "mike_tyson@gmail.com", "USA", "5500 0000 0000 0004");
        service.addBuyer(buyer);
        verify(repository, times(1)).save(buyer);
    }

    @Test
    public void deleteBuyerTest() {
        UUID id = UUID.fromString("fff2f54e-357a-4fc4-bfb5-a8f2ff0d67e6");
        Buyer buyer = new Buyer(id, "Mike Tyson", "CA, 123", "041-2456081",
                "mike_tyson@gmail.com", "USA", "5500 0000 0000 0004");
        service.deleteBuyer(buyer.getId());
        verify(repository, times(1)).deleteById(buyer.getId());
    }
}