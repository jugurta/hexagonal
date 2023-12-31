package com.jai.hexagonal.infrastructure.out.persistence.mongodb.adapter;

import com.jai.hexagonal.domain.model.Person;
import com.jai.hexagonal.infrastructure.out.persistence.mongodb.entity.PersonEntity;
import com.jai.hexagonal.infrastructure.out.persistence.mongodb.mapper.PersonMongoMapper;
import com.jai.hexagonal.infrastructure.out.persistence.mongodb.mapper.PersonMongoMapperImpl;
import com.jai.hexagonal.infrastructure.out.persistence.mongodb.repository.ReactivePersonMongoRepository;
import com.jai.hexagonal.providers.PersonProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.data.redis.core.ReactiveHashOperations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PersonMongoRepositoryAdapterTest {
    ReactivePersonMongoRepository reactiveMongoPersonRepository;
    PersonMongoMapper mongoPersonMapper;
    ReactiveHashOperations<String, Long, PersonEntity> reactiveHashOperations;
    PersonMongoRepositoryAdapter personRepositoryAdapter;


    @BeforeEach
    void setUp() {
        mongoPersonMapper = new PersonMongoMapperImpl();
        reactiveMongoPersonRepository = mock(ReactivePersonMongoRepository.class);
        reactiveHashOperations = mock(ReactiveHashOperations.class);
        personRepositoryAdapter = new PersonMongoRepositoryAdapter(reactiveMongoPersonRepository, mongoPersonMapper, reactiveHashOperations);
    }

    @ParameterizedTest
    @ArgumentsSource(PersonProvider.class)
    void givenPersonModelShouldSaveAndReturnPersonModel(Person person) {
        //GIVEN
        when(reactiveMongoPersonRepository.save(any(PersonEntity.class))).thenAnswer(invocationOnMock -> Mono.just(invocationOnMock.getArguments()[0]));
        when(reactiveHashOperations.put(any(), anyLong(), any(PersonEntity.class))).thenReturn(Mono.just(Boolean.TRUE));
        //WHEN
        Mono<Person> result = personRepositoryAdapter.save(person);
        //THEN
        StepVerifier
                .create(result)
                .expectNext(person)
                .verifyComplete();
    }

    @ParameterizedTest
    @MethodSource("com.jai.hexagonal.providers.PersonProviders#provideModelAndEntity")
    void givenIdentifierShouldReturnPersonModel(Person person, PersonEntity personEntity) {
        //GIVEN
        when(reactiveMongoPersonRepository.findById(anyLong())).thenAnswer(invocationOnMock -> Mono.just(personEntity));
        when(reactiveHashOperations.get(any(), any())).thenAnswer(invocationOnMock -> Mono.empty());
        when(reactiveHashOperations.put(any(), anyLong(), any(PersonEntity.class))).thenReturn(Mono.just(Boolean.TRUE));
        //WHEN
        Mono<Person> result = personRepositoryAdapter.findById(1L);
        //THEN
        StepVerifier
                .create(result)
                .expectNext(person)
                .verifyComplete();
    }
}
