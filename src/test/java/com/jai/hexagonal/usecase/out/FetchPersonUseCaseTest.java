package com.jai.hexagonal.usecase.out;


import com.jai.hexagonal.domain.model.Person;
import com.jai.hexagonal.domain.repository.PersonRepository;
import com.jai.hexagonal.providers.PersonProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class FetchPersonUseCaseTest {
    PersonRepository personRepository;
    FetchPersonUseCase fetchPersonUseCase;

    @BeforeEach
    void setUp() {
        personRepository = mock(PersonRepository.class);
        fetchPersonUseCase = new FetchPersonUseCase(personRepository);
    }

    @ParameterizedTest
    @ArgumentsSource(PersonProvider.class)
    void givenFetchPersonByIdShouldReturnPerson(Person person) {
        //GIVEN
        when(personRepository.findById(anyLong())).thenAnswer(invocationOnMock -> Mono.just(person));
        //WHEN
        Mono<Person> result = fetchPersonUseCase.fetchById(anyLong());
        //THEN
        StepVerifier
                .create(result)
                .expectNext(person)
                .verifyComplete();
        verify(personRepository, times(1)).findById(anyLong());
    }
}
