package com.wangzy.webfluxdemo.repository;

import com.wangzy.webfluxdemo.model.Company;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface CompanyRepository extends ReactiveMongoRepository<Company,String> {
    Flux<Company> findByName(String name);
}
