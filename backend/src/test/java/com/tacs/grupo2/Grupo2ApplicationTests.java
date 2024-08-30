package com.tacs.grupo2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class Grupo2ApplicationTests {

    @Test
    void contextLoads() {
    }

}
