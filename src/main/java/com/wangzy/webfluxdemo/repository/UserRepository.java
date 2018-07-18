package com.wangzy.webfluxdemo.repository;

import com.wangzy.webfluxdemo.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Mono<User> findByUsername(String username);

    Flux<User> findByUsernameLike(String username);

    Mono<Long> deleteByUsername(String username);
}
