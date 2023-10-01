package com.jai.hexagonal.infrastructure.in.rest.mapper;

import com.jai.hexagonal.domain.model.Person;
import com.jai.hexagonal.infrastructure.in.rest.dto.PersonDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PersonDTOMapper {
    Person toDomain(PersonDTO personDTO);

    PersonDTO toDTO(Person person);
}