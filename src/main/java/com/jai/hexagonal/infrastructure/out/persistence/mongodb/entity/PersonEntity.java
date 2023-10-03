package com.jai.hexagonal.infrastructure.out.persistence.mongodb.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDate;

@Document(collection = "person")
@Data
@AllArgsConstructor
@Builder(toBuilder = true)
@NoArgsConstructor

public class PersonEntity implements Serializable {

    @Id
    private Long id;
    @Field("first_name")
    private String firstName;
    @Field("last_name")
    private String lastName;
    @Field("birth_date")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birthDate;
}