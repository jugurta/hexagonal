package com.jai.hexagonal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HexagonalApplicationTests {

    @Autowired
    HexagonalApplication hexagonalApplication;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(hexagonalApplication);
    }

}
