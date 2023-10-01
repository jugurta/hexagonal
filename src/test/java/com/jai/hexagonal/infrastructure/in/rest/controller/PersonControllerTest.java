package com.jai.hexagonal.infrastructure.in.rest.controller;

import com.jai.hexagonal.domain.model.Person;
import com.jai.hexagonal.infrastructure.in.rest.dto.PersonDTO;
import com.jai.hexagonal.infrastructure.in.rest.mapper.PersonDTOMapper;
import com.jai.hexagonal.infrastructure.in.rest.mapper.PersonDTOMapperImpl;
import com.jai.hexagonal.providers.PersonDTOProvider;
import com.jai.hexagonal.usecase.in.CreatePersonUseCase;
import com.jai.hexagonal.usecase.out.FetchPersonUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PersonControllerTest {


    CreatePersonUseCase createPersonUseCase;
    FetchPersonUseCase fetchPersonUseCase;
    PersonDTOMapper personDTOMapper;
    PersonController personControllerApi;

    @BeforeEach
    void setUp() {
        personDTOMapper = new PersonDTOMapperImpl();
        createPersonUseCase = mock(CreatePersonUseCase.class);
        fetchPersonUseCase = mock(FetchPersonUseCase.class);
        personControllerApi = new PersonController(createPersonUseCase, fetchPersonUseCase, personDTOMapper);
    }

    @ParameterizedTest
    @ArgumentsSource(PersonDTOProvider.class)
    void givenPersonDTOShouldCreatePerson(PersonDTO personDTO) {
        //GIVE
        when(createPersonUseCase.createPerson(any(Person.class))).thenAnswer(invocationOnMock -> Mono.just(invocationOnMock.getArguments()[0]));
        //WHEN
        Mono<PersonDTO> result = personControllerApi.createPerson(personDTO);
        //THEN
        StepVerifier
                .create(result)
                .expectNext(personDTO)
                .verifyComplete();
        verify(createPersonUseCase, times(1)).createPerson(any(Person.class));
    }

    @ParameterizedTest
    @MethodSource("com.jai.hexagonal.providers.PersonProviders#provideModelAndDTO")
    void whenFindByIdShouldReturnPersonDTO(Person person, PersonDTO personDTO) {
        //GIVEN
        when(fetchPersonUseCase.fetchById(anyLong())).thenAnswer(invocationOnMock -> Mono.just(person));
        //WHEN
        Mono<PersonDTO> result = personControllerApi.fetchPerson(1L);
        //THEN
        StepVerifier.create(result)
                .expectNext(personDTO)
                .verifyComplete();
    }
}
