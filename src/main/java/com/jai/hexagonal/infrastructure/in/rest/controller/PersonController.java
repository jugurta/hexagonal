package com.jai.hexagonal.infrastructure.in.rest.controller;

import com.jai.hexagonal.infrastructure.in.rest.dto.PersonDTO;
import com.jai.hexagonal.infrastructure.in.rest.mapper.PersonDTOMapper;
import com.jai.hexagonal.usecase.in.CreatePersonUseCase;
import com.jai.hexagonal.usecase.out.FetchPersonUseCase;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final CreatePersonUseCase createPersonUseCase;
    private final FetchPersonUseCase fetchPersonUseCase;
    private final PersonDTOMapper personDTOMapper;

    @PostMapping
    Mono<PersonDTO> createPerson(@RequestBody PersonDTO personDTO) {
        return createPersonUseCase.createPerson(personDTOMapper.toDomain(personDTO)).map(personDTOMapper::toDTO);
    }


    @GetMapping(path = "{id}")
    Mono<PersonDTO> fetchPerson(@PathVariable Long id) {
        return fetchPersonUseCase.fetchById(id).map(personDTOMapper::toDTO);
    }
}