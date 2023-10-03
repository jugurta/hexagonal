package com.jai.hexagonal.infrastructure.out.persistence.mongodb.adapter;

import com.jai.hexagonal.domain.model.Person;
import com.jai.hexagonal.domain.repository.PersonRepository;
import com.jai.hexagonal.infrastructure.out.persistence.mongodb.entity.PersonEntity;
import com.jai.hexagonal.infrastructure.out.persistence.mongodb.mapper.PersonMongoMapper;
import com.jai.hexagonal.infrastructure.out.persistence.mongodb.repository.ReactivePersonMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PersonMongoRepositoryAdapter implements PersonRepository {

    private final ReactivePersonMongoRepository reactivePersonMongoRepository;
    private final PersonMongoMapper personMongoMapper;
    private final ReactiveHashOperations<String, Long, PersonEntity> hashOperations;
    private static final String KEY = "person";

    private Mono<PersonEntity> updateRedisCache(PersonEntity personEntity) {
        return hashOperations.put(KEY, personEntity.getId(), personEntity)
                .thenReturn(personEntity);
    }

    @Override
    public Mono<Person> save(Person person) {
        return reactivePersonMongoRepository.save(personMongoMapper.toEntity(person))
                .flatMap(this::updateRedisCache)
                .map(personMongoMapper::toDomain);
    }

    @Override
    public Mono<Person> findById(Long id) {
        return hashOperations.get(KEY, id)
                .switchIfEmpty(reactivePersonMongoRepository.findById(id))
                .flatMap(this::updateRedisCache)
                .map(personMongoMapper::toDomain);
    }
}
