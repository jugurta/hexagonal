package com.jai.hexagonal.usecase.in;

import com.jai.hexagonal.domain.model.Person;
import com.jai.hexagonal.domain.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


@Component
@RequiredArgsConstructor
public class CreatePersonUseCase {
    private final PersonRepository personRepository;

    public Mono<Person> createPerson(Person person) {
        return personRepository.save(person);
    }
}