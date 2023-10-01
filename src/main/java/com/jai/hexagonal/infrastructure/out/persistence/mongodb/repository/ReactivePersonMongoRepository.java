package com.jai.hexagonal.infrastructure.out.persistence.mongodb.repository;

import com.jai.hexagonal.infrastructure.out.persistence.mongodb.entity.PersonEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ReactivePersonMongoRepository extends ReactiveMongoRepository<PersonEntity, Long> {
}