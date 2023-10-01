package com.jai.hexagonal.usecase.out;

import com.jai.hexagonal.domain.model.Person;
import com.jai.hexagonal.domain.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class FetchPersonUseCase {

    private final PersonRepository personRepository;

    public Mono<Person> fetchById(Long id) {
        return personRepository.findById(id);
    }

}