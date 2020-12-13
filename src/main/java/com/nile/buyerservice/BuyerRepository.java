package com.nile.buyerservice;

import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BuyerRepository extends CouchbaseRepository<Buyer, UUID> {
    //    @Query("#{#n1ql.selectEntity} WHERE userName = $1")
    Optional<Buyer> findByUserName(String userName);
}
