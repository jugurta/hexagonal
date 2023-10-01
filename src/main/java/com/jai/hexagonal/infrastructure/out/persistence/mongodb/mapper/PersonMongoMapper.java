package com.jai.hexagonal.infrastructure.out.persistence.mongodb.mapper;

import com.jai.hexagonal.domain.model.Person;
import com.jai.hexagonal.infrastructure.out.persistence.mongodb.entity.PersonEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PersonMongoMapper {
    Person toDomain(PersonEntity personEntity);

    PersonEntity toEntity(Person person);
}