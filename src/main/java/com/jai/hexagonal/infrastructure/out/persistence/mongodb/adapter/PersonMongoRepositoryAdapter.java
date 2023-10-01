package com.jai.hexagonal.infrastructure.out.persistence.mongodb.adapter;

import com.jai.hexagonal.domain.model.Person;
import com.jai.hexagonal.domain.repository.PersonRepository;
import com.jai.hexagonal.infrastructure.out.persistence.mongodb.mapper.PersonMongoMapper;
import com.jai.hexagonal.infrastructure.out.persistence.mongodb.repository.ReactivePersonMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PersonMongoRepositoryAdapter implements PersonRepository {

    private final ReactivePersonMongoRepository reactivePersonMongoRepository;
    private final PersonMongoMapper personMongoMapper;

    @Override
    public Mono<Person> save(Person person) {
        return reactivePersonMongoRepository.save(personMongoMapper.toEntity(person)).map(personMongoMapper::toDomain);
    }

    @Override
    public Mono<Person> findById(Long id) {
        return reactivePersonMongoRepository.findById(id).map(personMongoMapper::toDomain);
    }
}
