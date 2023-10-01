package com.jai.hexagonal.integration;


import com.jai.hexagonal.infrastructure.in.rest.dto.PersonDTO;
import com.jai.hexagonal.providers.PersonDTOProvider;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonOperationsTest {


    @LocalServerPort
    private int port;
    WebClient webClient;

    @BeforeEach
    public void setUp() {
        webClient = WebClient.builder().baseUrl("http://localhost:".concat(String.valueOf(port))).build();
    }

    @ParameterizedTest
    @ArgumentsSource(PersonDTOProvider.class)
    void given_personDto_post_shouldReturntheSame(PersonDTO personDTO) {
        // GIVEN
        webClient.post().uri("/person").contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(personDTO)).retrieve();
        // WHEN
        Mono<PersonDTO> result = webClient.get()
                .uri("/person/{id}", 1L)
                .retrieve().bodyToMono(PersonDTO.class);
        //THEN
        StepVerifier.create(result).expectNext(personDTO).verifyComplete();

    }
}
