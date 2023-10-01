package com.jai.hexagonal.infrastructure.in.rest.mapper;

import com.jai.hexagonal.domain.model.Person;
import com.jai.hexagonal.infrastructure.in.rest.dto.PersonDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class PersonDTOMapperTest {
    PersonDTOMapper personDTOMapper;

    @BeforeEach
    void setUp() {
        personDTOMapper = new PersonDTOMapperImpl();
    }

    @ParameterizedTest
    @MethodSource("com.jai.hexagonal.providers.PersonProviders#provideModelAndDTO")
    void givenPersonDTOShouldMapPersonModel(Person person, PersonDTO personDTO) {
        Assertions.assertEquals(personDTOMapper.toDomain(personDTO), person);
    }

    @ParameterizedTest
    @MethodSource("com.jai.hexagonal.providers.PersonProviders#provideModelAndDTO")
    void givenPersonModelShouldMapPersonDTO(Person person, PersonDTO personDTO) {
        Assertions.assertEquals(personDTOMapper.toDTO(person), personDTO);
    }
}
