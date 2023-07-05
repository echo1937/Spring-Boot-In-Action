package com.example.property;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootPropertiesApplicationTests {


    @Autowired
    private DatabaseProperties databaseProperties;

    @Autowired
    private PersonProperties personProperties;

    @Test
    void whenSimplePropertyQueriedThenReturnsPropertyValue() throws Exception {
        Assertions.assertEquals("baeldung", databaseProperties.getUsername(), "Incorrectly bound Username property");
        Assertions.assertEquals("password", databaseProperties.getPassword(), "Incorrectly bound Password property");
    }

    @Test
    void whenNestedPropertyQueriedThenReturnsPropertyValue() throws Exception {
        Assertions.assertEquals("127.0.0.1", databaseProperties.getServer().getIp(), "Incorrectly bound Server IP nested property");
        Assertions.assertEquals(3306, databaseProperties.getServer().getPort(), "Incorrectly bound Server Port nested property");
    }

    @Test
    void whenSimplePropertyQueriedThenReturnsPropertyValueUseRecord() throws Exception {
        Assertions.assertEquals("abc", personProperties.name());
        Assertions.assertEquals("abc@example.com", personProperties.email());
    }

}
