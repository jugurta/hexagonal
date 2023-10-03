package com.jai.hexagonal.integration;


import com.jai.hexagonal.infrastructure.in.rest.dto.PersonDTO;
import com.jai.hexagonal.providers.PersonDTOProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
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
class PersonOperationsIT {


    @LocalServerPort
    private int port;
    WebClient webClient;

    @BeforeEach
    public void setUp() {
        webClient = WebClient.builder().baseUrl("http://localhost:".concat(String.valueOf(port))).build();
    }

    @ParameterizedTest
    @ArgumentsSource(PersonDTOProvider.class)
    @Order(1)
    void given_personDto_post_should_return_theSame(PersonDTO personDTO) {
        // GIVEN
        Mono<PersonDTO> postResult = webClient.post().uri("/person").contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(personDTO)).retrieve().bodyToMono(PersonDTO.class);
        // THEN
        StepVerifier.create(postResult)
                .expectNext(personDTO)
                .verifyComplete();
    }

    @ParameterizedTest
    @ArgumentsSource(PersonDTOProvider.class)
    @Order(2)
    @DisplayName("Given a personDto, when get, then return previously created")
    void given_personDto_get_should_return_previously_created(PersonDTO personDTO) {
        // WHEN
        Mono<PersonDTO> result = webClient.get()
                .uri("/person/{id}", 1L)
                .retrieve().bodyToMono(PersonDTO.class);
        //THEN
        StepVerifier.create(result).expectNext(personDTO).verifyComplete();
    }
}
