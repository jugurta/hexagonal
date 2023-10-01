package com.jai.hexagonal.domain.repository;

import com.jai.hexagonal.domain.model.Person;
import reactor.core.publisher.Mono;

public interface PersonRepository {

    Mono<Person> save(Person person);

    Mono<Person> findById(Long id);

}
